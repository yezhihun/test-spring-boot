package com.yezhihun.demo.companion;

/**
 * 陪诊师状态机事件（触发器）
 */
public enum CompanionEvent {
    /**
     * 进入审核
     */
    START_REVIEW,
    /**
     * 审核通过
     */
    APPROVE,
    /**
     * 审核驳回
     */
    REJECT,
    /**
     * 驳回后重新提交
     */
    RESUBMIT,
    /**
     * 开通接单
     */
    ACTIVATE,
    /**
     * 暂停接单
     */
    SUSPEND,
    /**
     * 恢复接单
     */
    RESUME,
    /**
     * 冻结（风控/严重投诉等）
     */
    FREEZE,
    /**
     * 解冻（解冻后默认回到“暂停接单”，需显式恢复接单）
     */
    UNFREEZE,
    /**
     * 注销/退出（终态）
     */
    DEACTIVATE
}

