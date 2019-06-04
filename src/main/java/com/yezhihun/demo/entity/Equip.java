package com.yezhihun.demo.entity;

import com.yezhihun.demo.enums.EquipCategory;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
 * 装备
 */
@Entity
@Table(name = "equip")
@Proxy(lazy = false)
public class Equip extends Biology{

    /**
     * 拥有者
     */
    @ManyToOne
    @JoinColumn(name = "owner")
    private Hero owner;
    @Enumerated(EnumType.STRING)
    @Column(name = "equip_category", columnDefinition = "varchar(100) COMMENT '部位'")
    private EquipCategory equipCategory;

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

    public EquipCategory getEquipCategory() {
        return equipCategory;
    }

    public void setEquipCategory(EquipCategory equipCategory) {
        this.equipCategory = equipCategory;
    }
}
