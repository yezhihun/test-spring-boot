package com.yezhihun.demo.entity.monster;

import com.yezhihun.demo.enums.Occupation;

/**
 * 史莱姆
 * Created by tianye on 2019/5/23.
 */
public class Slime extends Monster{

    public Slime(){
        this.name = "史莱姆";
        this.occupation = Occupation.SOLDIER;
        this.power = 20;
        this.mentality = 20;
        this.agile = 20;
        this.aggressivity = 2;
        this.magicResist = 2;
        this.armor = 2;
        this.blood = 30;
        this.magic = 30;
        switch (occupation) {
            case MASTER:
                this.mentality = 60;
                break;
            case SOLDIER:
                this.power = 60;
            case AGILE:
                this.agile = 60;
        }
    }
}
