package com.yezhihun.demo.entity.monster;

import com.yezhihun.demo.enums.MonsterPotential;
import com.yezhihun.demo.enums.Occupation;

/**
 * 怪物
 * Created by tianye on 2019/5/23.
 */
public class Monster {

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
     * 怪物潜力值
     */
    protected MonsterPotential monsterPotential;
    /**
     *
     */
    protected Occupation occupation;

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

    public MonsterPotential getMonsterPotential() {
        return monsterPotential;
    }

    public void setMonsterPotential(MonsterPotential monsterPotential) {
        this.monsterPotential = monsterPotential;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
