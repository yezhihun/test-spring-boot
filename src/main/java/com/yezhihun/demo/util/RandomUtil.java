package com.yezhihun.demo.util;

import com.yezhihun.demo.entity.Equip;
import com.yezhihun.demo.entity.Hero;
import com.yezhihun.demo.enums.Occupation;
import com.yezhihun.demo.enums.Potential;

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
     * 根据最大最小偏差和基准值获得随机值
     * @param min
     * @param max
     * @param base
     * @return
     */
    private static int getRealValueBetween(double min, double max, int base){

        return (int)(((Math.random()*100 * (max - min))/100 + min) * base);
    }

    public static void main(String[] args){
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

        Hero zhangfei = getRandomHero(new Hero(getRandomOccupation()), getRandomPotential());
        zhangfei.setName("张飞");

        Hero lvbu = getRandomHero(new Hero(getRandomOccupation()), getRandomPotential());
        lvbu.setName("吕布");

        Equip e1 = new Equip(10, 12, 299, 12, 32, 121, 34, 12);
        Equip e2 = new Equip(12, 11, 2, 12, 41, 32, 34, 12);
        Equip e3 = new Equip(31, 33, 34, 1, 32, 121, 34, 12);
        Equip e4 = new Equip(2, 44, 23, 41, 32, 121, 21, 12);
        Equip e5 = new Equip(1, 55, 67, 12, 32, 345, 34, 12);
        Equip e6 = new Equip(33, 34, 299, 12, 32, 121, 34, 12);
        zhangfei.setE1(e1);
        zhangfei.setE2(e2);
        zhangfei.setE3(e3);
        zhangfei.setE4(e4);
        zhangfei.setE5(e5);
        zhangfei.setE6(e6);

        Equip ee1 = new Equip(10, 3, 123, 12, 123, 11, 34, 13);
        Equip ee2 = new Equip(3, 12, 56, 23, 32, 32, 154, 12);
        Equip ee3 = new Equip(5, 12, 765, 2, 54, 121, 21, 43);
        Equip ee4 = new Equip(6, 4, 45, 34, 65, 123, 34, 12);
        Equip ee5 = new Equip(3, 7, 44, 11, 23, 65, 12, 34);
        lvbu.setE1(ee1);
        lvbu.setE2(ee2);
        lvbu.setE3(ee3);
        lvbu.setE4(ee4);
        lvbu.setE5(ee5);


        try {
            CalculatAttribute.battle(CalculatAttribute.calculatHeroAttr(zhangfei), CalculatAttribute.calculatHeroAttr(lvbu));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}
