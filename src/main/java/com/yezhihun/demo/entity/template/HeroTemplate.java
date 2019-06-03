package com.yezhihun.demo.entity.template;

import com.yezhihun.demo.entity.Biology;
import com.yezhihun.demo.enums.Potential;

import javax.persistence.*;

/**
 * Created by tianye on 2019/5/27.
 */
@Entity
@Table(name = "hero_template")
public class HeroTemplate extends Biology{

    @Enumerated(EnumType.STRING)
    @Column(name = "potenial", columnDefinition = "varchar(100) COMMENT '英雄品级'")
    private Potential potential;


    public Potential getPotential() {
        return potential;
    }

    public void setPotential(Potential potential) {
        this.potential = potential;
    }
}
