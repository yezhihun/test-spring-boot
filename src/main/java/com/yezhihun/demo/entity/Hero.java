package com.yezhihun.demo.entity;

import com.yezhihun.demo.enums.Occupation;
import com.yezhihun.demo.enums.Potential;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by tianye on 2019/5/20.
 */
@Entity
@Table(name = "hero")
public class Hero  extends BaseTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 英雄姓名
     */
    private String name;
    /**
     * 职业
     */
    private Occupation occupation;
    /**
     * 智力值
     */
    private Integer mentality;
    /**
     * 力量值
     */
    private Integer power;
    /**
     * 速度
     */
    private Integer speed;
    /**
     * 护甲
     */
    private Integer armor;
    /**
     * 等级
     */
    private Integer level;
    /**
     * 经验值
     */
    private Integer exp;
    /**
     *
     */
    private Potential potential;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public Integer getMentality() {
        return mentality;
    }

    public void setMentality(Integer mentality) {
        this.mentality = mentality;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Potential getPotential() {
        return potential;
    }

    public void setPotential(Potential potential) {
        this.potential = potential;
    }
}
