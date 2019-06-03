package com.yezhihun.demo.entity;

import com.yezhihun.demo.enums.Occupation;
import com.yezhihun.demo.enums.Potential;

import javax.persistence.*;

/**
 * Created by tianye on 2019/5/20.
 */
@Entity
@Table(name = "hero")
public class Hero extends Biology {
    /**
     * 英雄姓名
     */
    private String name;
    /**
     * 装备
     */
    @OneToOne
    @JoinColumn(name = "e1")
    private Equip e1;
    @OneToOne
    @JoinColumn(name = "e2")
    private Equip e2;
    @OneToOne
    @JoinColumn(name = "e3")
    private Equip e3;
    @OneToOne
    @JoinColumn(name = "e4")
    private Equip e4;
    @OneToOne
    @JoinColumn(name = "e5")
    private Equip e5;
    @OneToOne
    @JoinColumn(name = "e6")
    private Equip e6;
    /**
     * 经验值
     */
    private long exp;
    /**
     * 潜力值
     */
    private Potential potential;

    public Hero clone() throws CloneNotSupportedException {
        return (Hero) super.clone();
    }

    public Hero(Occupation occupation) {
        this.occupation = occupation;
        this.power = 80;
        this.mentality = 80;
        this.agile = 80;
        this.aggressivity = 10;
        this.magicResist = 5;
        this.armor = 5;
        this.blood = 100;
        this.magic = 100;
        this.level = 1;
        switch (occupation) {
            case MASTER:
                this.mentality = 120;
                break;
            case SOLDIER:
                this.power = 120;
            case AGILE:
                this.agile = 120;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public Potential getPotential() {
        return potential;
    }

    public void setPotential(Potential potential) {
        this.potential = potential;
    }

    public String toString() {
        String desc = String.format("occupation[%s],potential[%s],power[%s],mentality[%s],armor[%s],agile[%s],aggressivity[%s],total[%d]",
                occupation.getDesc(), potential.getDesc(), this.getPower(),
                this.getMentality(), this.getArmor(), this.getAgile(), this.getAggressivity(), power + mentality + agile);
        return desc;
    }

    public Equip getE1() {
        return e1;
    }

    public void setE1(Equip e1) {
        this.e1 = e1;
    }

    public Equip getE2() {
        return e2;
    }

    public void setE2(Equip e2) {
        this.e2 = e2;
    }

    public Equip getE3() {
        return e3;
    }

    public void setE3(Equip e3) {
        this.e3 = e3;
    }

    public Equip getE4() {
        return e4;
    }

    public void setE4(Equip e4) {
        this.e4 = e4;
    }

    public Equip getE5() {
        return e5;
    }

    public void setE5(Equip e5) {
        this.e5 = e5;
    }

    public Equip getE6() {
        return e6;
    }

    public void setE6(Equip e6) {
        this.e6 = e6;
    }
}
