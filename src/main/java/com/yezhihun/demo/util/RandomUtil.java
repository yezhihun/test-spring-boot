package com.yezhihun.demo.util;

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
        hero.setArmor(getRealValueBetween(min, max, hero.getArmor()));
        hero.setMentality(getRealValueBetween(min, max, hero.getMentality()));
        hero.setPower(getRealValueBetween(min, max, hero.getPower()));
        hero.setSpeed(getRealValueBetween(min, max, hero.getSpeed()));
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

        return (int)((Math.random()*100 * (max - min)) + (base * min));
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

        for (int i = 0; i< 100; i++) {
            Hero hero = getRandomHero(new Hero(getRandomOccupation()), getRandomPotential());
            System.out.println(hero);
        }

    }
}
