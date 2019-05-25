package com.yezhihun.demo.util;

import com.yezhihun.demo.entity.Hero;
import com.yezhihun.demo.entity.monster.Monster;
import com.yezhihun.demo.enums.MonsterPotential;

/**
 * 经验值
 * Created by tianye on 2019/5/24.
 */
public class EmpiricalUtil {

    public static Integer MAX_LEVEL = 100;
    public static Integer BASE_EXP = 100;

    /**
     * 获得当前怪物经验
     * @param hero
     * @param monster
     * @return
     */
    public static Hero getEmpByHeroAndMonster(Hero hero, Monster monster){

        Integer level = hero.getLevel();
        if (level == MAX_LEVEL){
            return hero;
        }
        long exp = hero.getExp();
        long currentLevelExp = getLevelUpEmp(level);

        int upExp = getEmp(level, monster.getLevel(), monster.getMonsterPotential());
        System.out.println(hero.getName() + "击败" + monster.getName() +
                "["+monster.getMonsterPotential().getValue()+","+monster.getLevel()+"]"+"获得经验:"+upExp);

        exp += upExp;
        if (exp >= currentLevelExp){
            hero = heroLevelUp(hero, exp, currentLevelExp);
        } else {
            hero.setExp(exp);
        }
        return hero;
    }

    /**
     * 英雄升级
     * @param hero
     * @param exp
     * @param currentLevelExp
     * @return
     */
    public static Hero heroLevelUp(Hero hero, long exp, long currentLevelExp){
        if (exp < currentLevelExp){
            return hero;
        }
        Integer level = hero.getLevel();
        if (level >= MAX_LEVEL){
            return hero;
        }
        hero.setLevel(level+1);
        hero.setExp(level+1 == MAX_LEVEL?0:exp-currentLevelExp);
        System.out.println(String.format("%s升到%s级", hero.getName(), level+1));
        return attributeLeveUp(hero);
    }

    /**
     * 英雄属性升级
     * @param hero
     * @return
     */
    private static Hero attributeLeveUp(Hero hero){
        //TODO 升级属性变化待定
        int power = 8;
        int mentality = 8;
        int agile = 8;
        int aggressivity = 1;
        int magicResist = 1;
        int armor = 1;
        int blood = 10;
        int magic = 10;
        switch (hero.getOccupation()) {
            case MASTER:
                mentality = 12;
                break;
            case SOLDIER:
                power = 12;
            case AGILE:
                agile = 12;
        }

        hero.setPower(hero.getPower()+power);
        hero.setMentality(hero.getMentality()+mentality);
        hero.setAgile(hero.getAgile()+agile);
        hero.setAggressivity(hero.getAggressivity()+aggressivity);
        hero.setMagicResist(hero.getMagicResist()+magicResist);
        hero.setArmor(hero.getArmor()+armor);
        hero.setBlood(hero.getBlood()+blood);
        hero.setMagic(hero.getMagic()+magic);
        return hero;
    }
    

    /**
     * 根据怪物等级和潜力值计算当前等级影响应获得经验值
     * @param heroLevel
     * @param monsterLevel
     * @param monsterPotential
     * @return
     */
    private static Integer getEmp(Integer heroLevel, Integer monsterLevel, MonsterPotential monsterPotential){
        int levelSub = heroLevel - monsterLevel;
        //五级之外不产生经验
        if (levelSub > 5 || levelSub < -5){
            return 0;
        }
        double offset = 0.01;
        double currentLevelExp = getLevelUpEmp(heroLevel);
        switch (monsterPotential){
            case A:
                offset += 0.05;
                break;
            case B:
                offset += 0.03;
                break;
            case C:
                break;
            case S:
                offset += 0.1;
                break;
        }
        return (int)(currentLevelExp * offset);
    }

    /**
     * 获得升级总经验
     * @param heroLevel
     * @return
     */
    private static long getLevelUpEmp(Integer heroLevel){
        long exp = BASE_EXP;
        for (int i=1;i<heroLevel;i++){
            if (i%10 == 9){
                exp *= 2;
            } else {
                exp *= 1.5;
            }
        }
        return exp;
    }

    public static void main(String[] args){
        System.out.println(getLevelUpEmp(90));
    }
}
