package com.yezhihun.demo.entity;

import com.yezhihun.demo.enums.Occupation;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by tianye on 2019/5/24.
 */
@MappedSuperclass
public class Biology extends BaseTable implements Serializable, Cloneable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 名称
     */
    protected String name;
    /**
     * 智力值
     */
    protected int mentality;
    /**
     * 力量值
     */
    protected int power;
    /**
     * 敏捷值
     */
    protected int agile;
    /**
     * 护甲
     */
    protected int armor;
    /**
     * 魔抗
     */
    protected int magicResist;
    /**
     * 血量
     */
    protected int blood;
    /**
     * 魔法值
     */
    protected int magic;
    /**
     * 攻击力
     */
    protected int aggressivity;
    /**
     * 等级
     */
    protected int level = 1;
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

    public int  getMentality() {
        return mentality;
    }

    public void setMentality(int  mentality) {
        this.mentality = mentality;
    }

    public int  getPower() {
        return power;
    }

    public void setPower(int  power) {
        this.power = power;
    }

    public int  getAgile() {
        return agile;
    }

    public void setAgile(int  agile) {
        this.agile = agile;
    }

    public int  getArmor() {
        return armor;
    }

    public void setArmor(int  armor) {
        this.armor = armor;
    }

    public int  getMagicResist() {
        return magicResist;
    }

    public void setMagicResist(int  magicResist) {
        this.magicResist = magicResist;
    }

    public int  getBlood() {
        return blood;
    }

    public void setBlood(int  blood) {
        this.blood = blood;
    }

    public int  getMagic() {
        return magic;
    }

    public void setMagic(int  magic) {
        this.magic = magic;
    }

    public int  getAggressivity() {
        return aggressivity;
    }

    public void setAggressivity(int  aggressivity) {
        this.aggressivity = aggressivity;
    }

    public int  getLevel() {
        return level;
    }

    public void setLevel(int  level) {
        this.level = level;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
