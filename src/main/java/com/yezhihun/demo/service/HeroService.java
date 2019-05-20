package com.yezhihun.demo.service;

import com.yezhihun.demo.entity.Hero;
import com.yezhihun.demo.enums.Occupation;
import com.yezhihun.demo.enums.Potential;

/**
 * Created by tianye on 2019/5/20.
 */
public interface HeroService extends BaseService<Hero>{

    /**
     * 随机生成一个新英雄
     * @return
     */
    Hero createHero();
    Hero createHero(Potential potential);
    Hero createHero(Occupation occupation);
    Hero createHero(Occupation occupation, Potential potential);
}
