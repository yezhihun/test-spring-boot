package com.yezhihun.demo.enums;

/**
 * Created by tianye on 2019/6/3.
 * 装备分类
 */
public enum EquipCategory {
    HEAD(1, "头冠"),
    ARMS(1, "武器"),
    BOOTS(1, "靴子"),
    JACKET(1, "上衣"),
    BOTTLING(1, "下装"),
    ;

    private int val;
    private String desc;
    EquipCategory(int val, String desc){

    }

    public int getVal() {
        return val;
    }

    public String getDesc() {
        return desc;
    }
}
