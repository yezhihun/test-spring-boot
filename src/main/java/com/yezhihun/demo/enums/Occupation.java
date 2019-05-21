package com.yezhihun.demo.enums;

/**
 * Created by tianye on 2019/5/20.
 * 职业
 */
public enum Occupation {
    SOLDIER(33, "战士"),
    MASTER(33, "法师"),
    AGILE(33, "风行者");

    private int value;
    private String desc;

    Occupation(int value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
