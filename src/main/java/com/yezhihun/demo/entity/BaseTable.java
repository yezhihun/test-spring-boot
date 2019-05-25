package com.yezhihun.demo.entity;

/**
 * Created by tianye on 2018/5/4.
 */
public class BaseTable {

    /**
     * 唯一
     */
    protected boolean unique;

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }
}
