package com.yezhihun.demo.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * 装备
 */
@Entity
@Table(name = "equip")
public class Equip extends Biology{

    /**
     * 拥有者
     */
    @JoinColumn(name = "equip_owner_fk")
    private Hero owner;

    public Equip(){

    }

    public Equip (int aggressivity, int magic, int blood, int magicResist, int armor, int agile, int power, int mentality){
        this.aggressivity = aggressivity;
        this.magic = magic;
        this.blood = blood;
        this.magicResist = magicResist;
        this.armor = armor;
        this.agile = agile;
        this.power = power;
        this.mentality = mentality;
    }

    public Hero getOwner() {
        return owner;
    }

    public void setOwner(Hero owner) {
        this.owner = owner;
    }
}
