package com.yezhihun.demo.template;

import com.yezhihun.demo.entity.Biology;
import com.yezhihun.demo.enums.MonsterPotential;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 怪物模板
 *
 * Created by tianye on 2019/5/27.
 */
@Entity
@Table(name = "monster_template")
public class MonsterTemplate extends Biology{

    private MonsterPotential monsterPotential;

    public MonsterPotential getMonsterPotential() {
        return monsterPotential;
    }

    public void setMonsterPotential(MonsterPotential monsterPotential) {
        this.monsterPotential = monsterPotential;
    }
}
