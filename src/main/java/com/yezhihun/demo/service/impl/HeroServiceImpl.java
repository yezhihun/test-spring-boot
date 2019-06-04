package com.yezhihun.demo.service.impl;

import com.yezhihun.demo.dao.HeroDao;
import com.yezhihun.demo.entity.Hero;
import com.yezhihun.demo.entity.template.HeroTemplate;
import com.yezhihun.demo.enums.Occupation;
import com.yezhihun.demo.enums.Potential;
import com.yezhihun.demo.service.HeroService;
import com.yezhihun.demo.service.HeroTemplateService;
import com.yezhihun.demo.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by tianye on 2019/5/20.
 */
@Service
@Transactional
public class HeroServiceImpl extends AbstractBaseServiceImpl<Hero> implements HeroService{

    @Autowired
    private HeroDao heroDao;
    @Autowired
    private HeroTemplateService heroTemplateService;

    @Override
    public void init() {
        this.baseDao = heroDao;
    }

    @Override
    public Hero createHero() {
        return createHero(RandomUtil.getRandomOccupation(), RandomUtil.getRandomPotential());
    }

    @Override
    public Hero createHero(Potential potential) {
        return createHero(RandomUtil.getRandomOccupation(), potential);
    }

    @Override
    public Hero createHero(Occupation occupation) {
        return createHero(occupation, RandomUtil.getRandomPotential());
    }

    @Override
    public Hero createHero(Occupation occupation, Potential potential) {
        if (occupation == null){
            occupation = RandomUtil.getRandomOccupation();
        }
        if (potential == null){
            potential = RandomUtil.getRandomPotential();
        }
        HeroTemplate heroTemplate = heroTemplateService.selectOneByOccupation(occupation);
        Hero hero = RandomUtil.getRandomHero(heroTemplate, potential);
        hero.setName("常胜将军");
        heroDao.save(hero);
        return hero;
    }

}
