package com.yezhihun.demo.entity.template;

import com.yezhihun.demo.entity.Biology;
import com.yezhihun.demo.enums.MonsterPotential;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
 * 怪物模板
 *
 * Created by tianye on 2019/5/27.
 */
@Entity
@Table(name = "monster_template")
@Proxy(lazy = false)
public class MonsterTemplate extends Biology{

    @Enumerated(EnumType.STRING)
    @Column(name = "monster_potenial", columnDefinition = "varchar(100) COMMENT '怪物品级'")
    private MonsterPotential monsterPotential;

    public MonsterPotential getMonsterPotential() {
        return monsterPotential;
    }

    public void setMonsterPotential(MonsterPotential monsterPotential) {
        this.monsterPotential = monsterPotential;
    }
}
