package com.yezhihun.demo.companion;

/**
 * 陪诊师状态（无签约、无培训逻辑版本）
 */
public enum CompanionStatus {
    /**
     * 已提交注册信息，待审核
     */
    SUBMITTED,
    /**
     * 审核中
     */
    UNDER_REVIEW,
    /**
     * 审核驳回（可重新提交）
     */
    REJECTED,
    /**
     * 审核通过（具备开通接单资格）
     */
    APPROVED,
    /**
     * 已开通接单，正常服务中
     */
    ACTIVE,
    /**
     * 暂停接单（可恢复）
     */
    SUSPENDED,
    /**
     * 冻结（严重问题，需解冻后再处理）
     */
    FROZEN,
    /**
     * 已注销/退出（终态，不可逆）
     */
    DEACTIVATED
}

