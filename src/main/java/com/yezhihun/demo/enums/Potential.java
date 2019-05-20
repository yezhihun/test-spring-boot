package com.yezhihun.demo.enums;

/**
 * Created by tianye on 2019/5/20.
 * 潜力值
 */
public enum  Potential {
    C(50, 0.8, 1.1, "C"),
    B(40, 1, 1.3, "B"),
    A(9, 1.2, 1.5, "A"),
    S(1, 1.4, 1.7, "S");

    private int value;
    private double min;
    private double max;
    private String desc;

    Potential(int value, double min, double max, String desc){
        this.value = value;
        this.desc = desc;
        this.max = max;
        this.min = min;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}
