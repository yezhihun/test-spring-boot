package com.yezhihun.demo.util;

import com.yezhihun.demo.entity.Equip;
import com.yezhihun.demo.entity.Hero;
import com.yezhihun.demo.entity.checkpoint.CheckPoint;
import com.yezhihun.demo.entity.monster.Monster;
import com.yezhihun.demo.entity.template.EquipTemplate;
import com.yezhihun.demo.entity.template.HeroTemplate;
import com.yezhihun.demo.entity.template.MonsterTemplate;
import com.yezhihun.demo.enums.EquipPotenial;
import com.yezhihun.demo.enums.MonsterPotential;
import com.yezhihun.demo.enums.Occupation;
import com.yezhihun.demo.enums.Potential;

import java.util.List;

/**
 * Created by tianye on 2019/5/20.
 */
public class RandomUtil {

    /**
     * 按照概率随机生成职业
     * @return
     */
    public static Occupation getRandomOccupation(){
        int random = (int)(Math.random()*100);
        Occupation oo = Occupation.MASTER;
        for (Occupation o : Occupation.values()){
            if(o.getValue() >= 100 - random){
                if (o.getValue() < oo.getValue()){
                    oo = o;
                } else if (o.getValue() == oo.getValue()){
                    int x = (int)(Math.random()*100);
                    if (x>50){
                        oo = o;
                    }
                }
            }
        }
        return oo;
    }

    /**
     * 按照概率随机生成潜力值
     * @return
     */
    public static Potential getRandomPotential(){
        int random = (int)(Math.random()*100);
        Potential pp = Potential.C;
        for (Potential p : Potential.values()){
            if(p.getValue() >= 100 - random){
                if (p.getValue() < pp.getValue()){
                    pp = p;
                } else if (p.getValue() == pp.getValue()){
                    int x = (int)(Math.random()*100);
                    if (x>50){
                        pp = p;
                    }
                }
            }
        }
//        System.out.println("potential random:" + random + ",value is " + pp.getDesc());
        return pp;
    }

    /**
     * 按照概率随机生成怪物潜力值
     * @return
     */
    public static MonsterPotential getRandomMonsterPotential(){
        int random = (int)(Math.random()*100);
        MonsterPotential pp = MonsterPotential.C;
        for (MonsterPotential p : MonsterPotential.values()){
            if(p.getValue() >= 100 - random){
                if (p.getValue() < pp.getValue()){
                    pp = p;
                } else if (p.getValue() == pp.getValue()){
                    int x = (int)(Math.random()*100);
                    if (x>50){
                        pp = p;
                    }
                }
            }
        }
//        System.out.println("potential random:" + random + ",value is " + pp.getDesc());
        return pp;
    }

    /**
     * 按照概率随机生成装备潜力值
     * @return
     */
    public static EquipPotenial getRandomEquipPotential(){
        int random = (int)(Math.random()*100);
        EquipPotenial pp = EquipPotenial.C;
        for (EquipPotenial p : EquipPotenial.values()){
            if(p.getValue() >= 100 - random){
                if (p.getValue() < pp.getValue()){
                    pp = p;
                } else if (p.getValue() == pp.getValue()){
                    int x = (int)(Math.random()*100);
                    if (x>50){
                        pp = p;
                    }
                }
            }
        }
        return pp;
    }

    /**
     * 随机生成英雄
     * @param hero
     * @param potential
     * @return
     */
    public static Hero getRandomHero(Hero hero, Potential potential){
        double max = potential.getMax();
        double min = potential.getMin();
        hero.setPotential(potential);
        hero.setMentality(getRealValueBetween(min, max, hero.getMentality()));
        hero.setPower(getRealValueBetween(min, max, hero.getPower()));
        hero.setAgile(getRealValueBetween(min, max, hero.getAgile()));
        hero.setAggressivity(getRealValueBetween(min, max, hero.getAggressivity()));
        return hero;
    }

    /**
     * 根据模板随机生成英雄
     * @param heroTemplate
     * @param potential
     * @return
     */
    public static Hero getRandomHero(HeroTemplate heroTemplate, Potential potential){
        Hero hero = new Hero();
        double max = potential.getMax();
        double min = potential.getMin();
        hero.setPotential(potential);
        hero.setOccupation(heroTemplate.getOccupation());
        hero.setMentality(getRealValueBetween(min, max, heroTemplate.getMentality()));
        hero.setPower(getRealValueBetween(min, max, heroTemplate.getPower()));
        hero.setAgile(getRealValueBetween(min, max, heroTemplate.getAgile()));
        hero.setAggressivity(getRealValueBetween(min, max, heroTemplate.getAggressivity()));
        hero.setBlood(getRealValueBetween(min, max, heroTemplate.getBlood()));
        hero.setArmor(getRealValueBetween(min, max, heroTemplate.getArmor()));
        hero.setMagic(getRealValueBetween(min, max, heroTemplate.getMagic()));
        hero.setMagicResist(getRealValueBetween(min, max, heroTemplate.getMagicResist()));
        return hero;
    }

    /**
     * 随机生成怪物
     * @param monster
     * @return
     */
    public static Monster getRandomMonster(Monster monster, MonsterPotential potential){
        double max = potential.getMax();
        double min = potential.getMin();
        monster.setMonsterPotential(potential);
        monster.setMentality(getRealValueBetween(min, max, monster.getMentality()));
        monster.setPower(getRealValueBetween(min, max, monster.getPower()));
        monster.setAgile(getRealValueBetween(min, max, monster.getAgile()));
        monster.setAggressivity(getRealValueBetween(min, max, monster.getAggressivity()));

        return monster;
    }

    /**
     * 随机生成怪物
     * @param monsterTemplate
     * @return
     */
    public static Monster getRandomMonster(MonsterTemplate monsterTemplate, MonsterPotential potential){
        Monster monster = new Monster();
        double max = potential.getMax();
        double min = potential.getMin();
        monster.setMonsterPotential(potential);
        monster.setName(monsterTemplate.getName());
        monster.setOccupation(monsterTemplate.getOccupation());
        monster.setMentality(getRealValueBetween(min, max, monsterTemplate.getMentality()));
        monster.setPower(getRealValueBetween(min, max, monsterTemplate.getPower()));
        monster.setAgile(getRealValueBetween(min, max, monsterTemplate.getAgile()));
        monster.setAggressivity(getRealValueBetween(min, max, monsterTemplate.getAggressivity()));
        monster.setBlood(getRealValueBetween(min, max, monsterTemplate.getBlood()));
        monster.setArmor(getRealValueBetween(min, max, monsterTemplate.getArmor()));
        monster.setMagic(getRealValueBetween(min, max, monsterTemplate.getMagic()));
        monster.setMagicResist(getRealValueBetween(min, max, monsterTemplate.getMagicResist()));
        return monster;
    }

    /**
     * 根据地图随机生成怪物(地图等级*5且向上浮动5以内)
     * @param checkPoint
     * @param monster
     * @param potential
     * @return
     */
    public static Monster getRandomMonster(CheckPoint checkPoint, Monster monster, MonsterPotential potential){
        int baseLevel = checkPoint.getLevel()*5;
        int random = (int)(Math.random()*100/20);
        monster.setLevel(baseLevel + random);
        return getRandomMonster(monster, potential);
    }

    /**
     * 根据模板生成武器
     * @param equipTemplate
     * @param potenial
     * @return
     */
    public static Equip getRandomEquipByTemplate(EquipTemplate equipTemplate, EquipPotenial potenial){
        //TODO 获得相应等级的装备模板
        /**
         * 根据模板复制基础属性装备
         * 根据装备品级调整属性
         */
        Equip equip = new Equip();
        double min = potenial.getMin();
        double max = potenial.getMax();
        equip.setAggressivity(getRealValueBetween(min, max, equipTemplate.getAggressivity()));
        equip.setAgile(getRealValueBetween(min, max, equipTemplate.getAgile()));
        equip.setArmor(getRealValueBetween(min, max, equipTemplate.getArmor()));
        equip.setBlood(getRealValueBetween(min, max, equipTemplate.getBlood()));
        equip.setMagic(getRealValueBetween(min, max, equipTemplate.getMagic()));
        equip.setMagicResist(getRealValueBetween(min, max, equipTemplate.getMagicResist()));
        equip.setMentality(getRealValueBetween(min, max, equipTemplate.getMentality()));
        equip.setPower(getRealValueBetween(min, max, equipTemplate.getPower()));
        equip.setLevel(equipTemplate.getLevel());

        return equip;
    }

    public static Equip getRandomEquipByTemplate(EquipTemplate equipTemplate){
        return getRandomEquipByTemplate(equipTemplate, getRandomEquipPotential());
    }

    /**
     * 随机取得一个模板
     * @param list
     * @return
     */
    public static Object getRandomObjectForList(List list){
        if (list == null || list.size()==0){
            return null;
        }
        Object result = list.get(0);
        double baseNum = 0;

        for (Object o : list){
            double num = Math.random();
            if (num > baseNum){
                result = o;
                baseNum = num;
            }
        }

        return result;
    }

    /**
     * 根据最大最小偏差和基准值获得随机值
     * @param min
     * @param max
     * @param base
     * @return
     */
    private static int getRealValueBetween(double min, double max, int base){
        return (int)(((Math.random()*100 * (max - min))/100 + min) * base);
    }

    public static void main(String[] args) throws CloneNotSupportedException {
//        int countC = 0;
//        int countB = 0;
//        int countA = 0;
//        int countS = 0;
//        for (int i=0;i<1000;i++){
//            switch (getRandomPotential()){
//                case A:
//                    countA++;
//                    break;
//                case B:
//                    countB++;
//                    break;
//                case C:
//                    countC++;
//                    break;
//                case S:
//                    countS++;
//                    break;
//            }
//        }
//        System.out.println("A:"+countA);
//        System.out.println("C:"+countC);
//        System.out.println("B:"+countB);
//        System.out.println("S:"+countS);

//        for (int i = 0; i< 100; i++) {
////            Hero hero = getRandomHero(new Hero(getRandomOccupation()), getRandomPotential());
////            System.out.println(hero);
////        }

//        Hero zhangfei = getRandomHero(new Hero(getRandomOccupation()), getRandomPotential());
//        zhangfei.setName("张飞");
//
//        Hero lvbu = getRandomHero(new Hero(getRandomOccupation()), getRandomPotential());
//        lvbu.setName("吕布");
//
//        Equip e1 = new Equip(10, 12, 299, 12, 32, 121, 34, 12);
//        Equip e2 = new Equip(12, 11, 2, 12, 41, 32, 34, 12);
//        Equip e3 = new Equip(31, 33, 34, 1, 32, 121, 34, 12);
//        Equip e4 = new Equip(2, 44, 23, 41, 32, 121, 21, 12);
//        Equip e5 = new Equip(1, 55, 67, 12, 32, 345, 34, 12);
//        Equip e6 = new Equip(33, 34, 299, 12, 32, 121, 34, 12);
//        zhangfei.setE1(e1);
//        zhangfei.setE2(e2);
//        zhangfei.setE3(e3);
//        zhangfei.setE4(e4);
//        zhangfei.setE5(e5);
//        zhangfei.setE6(e6);
//
//        Equip ee1 = new Equip(10, 3, 123, 12, 123, 11, 34, 13);
//        Equip ee2 = new Equip(3, 12, 56, 23, 32, 32, 154, 12);
//        Equip ee3 = new Equip(5, 12, 765, 2, 54, 121, 21, 43);
//        Equip ee4 = new Equip(6, 4, 45, 34, 65, 123, 34, 12);
//        Equip ee5 = new Equip(3, 7, 44, 11, 23, 65, 12, 34);
//        lvbu.setE1(ee1);
//        lvbu.setE2(ee2);
//        lvbu.setE3(ee3);
//        lvbu.setE4(ee4);
//        lvbu.setE5(ee5);
//
//
//        for (int i=0;i<100;i++){
//            Monster monster = RandomUtil.getRandomMonster(new Slime(), getRandomMonsterPotential());
//            CalculatAttribute.battle(zhangfei, CalculatAttribute.calculatHeroAttr(zhangfei), monster);
//        }

//        try {
//            CalculatAttribute.battle(CalculatAttribute.calculatHeroAttr(zhangfei), CalculatAttribute.calculatHeroAttr(lvbu));
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }

    }
}
