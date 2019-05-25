package com.yezhihun.demo.entity;

import com.yezhihun.demo.enums.Occupation;

import java.io.Serializable;

/**
 * Created by tianye on 2019/5/24.
 */
public class Biology extends BaseTable implements Serializable, Cloneable{

    /**
     * 怪物名称
     */
    protected String name;
    /**
     * 智力值
     */
    protected Integer mentality;
    /**
     * 力量值
     */
    protected Integer power;
    /**
     * 敏捷值
     */
    protected Integer agile;
    /**
     * 护甲
     */
    protected Integer armor;
    /**
     * 魔抗
     */
    protected Integer magicResist;
    /**
     * 血量
     */
    protected Integer blood;
    /**
     * 魔法值
     */
    protected Integer magic;
    /**
     * 攻击力
     */
    protected Integer aggressivity;
    /**
     * 等级
     */
    protected Integer level;
    /**
     * 职业
     */
    protected Occupation occupation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getAgile() {
        return agile;
    }

    public void setAgile(Integer agile) {
        this.agile = agile;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public Integer getMagicResist() {
        return magicResist;
    }

    public void setMagicResist(Integer magicResist) {
        this.magicResist = magicResist;
    }

    public Integer getBlood() {
        return blood;
    }

    public void setBlood(Integer blood) {
        this.blood = blood;
    }

    public Integer getMagic() {
        return magic;
    }

    public void setMagic(Integer magic) {
        this.magic = magic;
    }

    public Integer getAggressivity() {
        return aggressivity;
    }

    public void setAggressivity(Integer aggressivity) {
        this.aggressivity = aggressivity;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }
}
