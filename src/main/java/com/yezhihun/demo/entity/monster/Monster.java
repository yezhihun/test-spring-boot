package com.yezhihun.demo.entity.monster;

import com.yezhihun.demo.entity.Biology;
import com.yezhihun.demo.enums.MonsterPotential;

/**
 * 怪物
 * Created by tianye on 2019/5/23.
 */
public class Monster extends Biology{

    /**
     * 怪物潜力值
     */
    protected MonsterPotential monsterPotential;



    public MonsterPotential getMonsterPotential() {
        return monsterPotential;
    }

    public void setMonsterPotential(MonsterPotential monsterPotential) {
        this.monsterPotential = monsterPotential;
    }

}
