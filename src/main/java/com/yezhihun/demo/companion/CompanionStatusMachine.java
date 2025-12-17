package com.yezhihun.demo.companion;

import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 陪诊师状态机（JDK 21）
 *
 * <p>核心原则：
 * <ul>
 *   <li>显式事件驱动：只能通过事件迁移</li>
 *   <li>非法迁移直接失败：抛出 IllegalStateException，便于业务层兜底</li>
 *   <li>冻结解冻策略：解冻回到 SUSPENDED，需显式 RESUME 才能回 ACTIVE（更安全）</li>
 *   <li>注销为终态：DEACTIVATED 不允许任何迁移</li>
 * </ul>
 */
public final class CompanionStatusMachine {
    private static final EnumMap<CompanionStatus, EnumMap<CompanionEvent, CompanionStatus>> TRANSITIONS =
            new EnumMap<>(CompanionStatus.class);

    static {
        // SUBMITTED -> UNDER_REVIEW
        add(CompanionStatus.SUBMITTED, CompanionEvent.START_REVIEW, CompanionStatus.UNDER_REVIEW);

        // UNDER_REVIEW -> APPROVED / REJECTED
        add(CompanionStatus.UNDER_REVIEW, CompanionEvent.APPROVE, CompanionStatus.APPROVED);
        add(CompanionStatus.UNDER_REVIEW, CompanionEvent.REJECT, CompanionStatus.REJECTED);

        // REJECTED -> SUBMITTED（重新提交后再走审核）
        add(CompanionStatus.REJECTED, CompanionEvent.RESUBMIT, CompanionStatus.SUBMITTED);

        // APPROVED -> ACTIVE（开通接单）
        add(CompanionStatus.APPROVED, CompanionEvent.ACTIVATE, CompanionStatus.ACTIVE);

        // ACTIVE <-> SUSPENDED
        add(CompanionStatus.ACTIVE, CompanionEvent.SUSPEND, CompanionStatus.SUSPENDED);
        add(CompanionStatus.SUSPENDED, CompanionEvent.RESUME, CompanionStatus.ACTIVE);

        // FREEZE：除终态外均可冻结
        for (var s : EnumSet.complementOf(EnumSet.of(CompanionStatus.DEACTIVATED, CompanionStatus.FROZEN))) {
            add(s, CompanionEvent.FREEZE, CompanionStatus.FROZEN);
        }

        // FROZEN -> SUSPENDED（解冻后默认暂停接单）
        add(CompanionStatus.FROZEN, CompanionEvent.UNFREEZE, CompanionStatus.SUSPENDED);

        // DEACTIVATE：除终态外均可注销
        for (var s : EnumSet.complementOf(EnumSet.of(CompanionStatus.DEACTIVATED))) {
            add(s, CompanionEvent.DEACTIVATE, CompanionStatus.DEACTIVATED);
        }

        // 使内部结构不可变（防止运行时被误改）
        for (var entry : TRANSITIONS.entrySet()) {
            entry.setValue(new EnumMap<>(entry.getValue()));
        }
    }

    private CompanionStatusMachine() {
    }

    public static boolean canTransition(CompanionStatus from, CompanionEvent event) {
        Objects.requireNonNull(from, "from must not be null");
        Objects.requireNonNull(event, "event must not be null");
        return TRANSITIONS.getOrDefault(from, new EnumMap<>(CompanionEvent.class)).containsKey(event);
    }

    public static CompanionStatus next(CompanionStatus from, CompanionEvent event) {
        Objects.requireNonNull(from, "from must not be null");
        Objects.requireNonNull(event, "event must not be null");

        var byEvent = TRANSITIONS.get(from);
        if (byEvent == null || !byEvent.containsKey(event)) {
            throw new IllegalStateException("Illegal transition: " + from + " --(" + event + ")--> ?");
        }
        return byEvent.get(event);
    }

    public static Set<CompanionEvent> allowedEvents(CompanionStatus from) {
        Objects.requireNonNull(from, "from must not be null");
        var byEvent = TRANSITIONS.get(from);
        if (byEvent == null || byEvent.isEmpty()) {
            return Set.of();
        }
        return Collections.unmodifiableSet(byEvent.keySet());
    }

    public static Map<CompanionEvent, CompanionStatus> allowedTransitions(CompanionStatus from) {
        Objects.requireNonNull(from, "from must not be null");
        var byEvent = TRANSITIONS.get(from);
        if (byEvent == null || byEvent.isEmpty()) {
            return Map.of();
        }
        return Collections.unmodifiableMap(byEvent);
    }

    private static void add(CompanionStatus from, CompanionEvent event, CompanionStatus to) {
        Objects.requireNonNull(from, "from must not be null");
        Objects.requireNonNull(event, "event must not be null");
        Objects.requireNonNull(to, "to must not be null");

        var byEvent = TRANSITIONS.computeIfAbsent(from, __ -> new EnumMap<>(CompanionEvent.class));
        var existed = byEvent.putIfAbsent(event, to);
        if (existed != null && existed != to) {
            throw new IllegalStateException("Duplicate transition: " + from + " + " + event + " -> " + existed + "/" + to);
        }
    }
}

