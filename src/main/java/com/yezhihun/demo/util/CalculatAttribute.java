package com.yezhihun.demo.util;

import com.yezhihun.demo.entity.Biology;
import com.yezhihun.demo.entity.Equip;
import com.yezhihun.demo.entity.Hero;
import com.yezhihun.demo.enums.Occupation;

/**
 * 属性计算
 */
public class CalculatAttribute {

    /**
     * 计算穿戴装备后的各项属性值
     * @param hero
     * @return
     * @throws CloneNotSupportedException
     */
    public static Hero calculatHeroAttr(final Hero hero) throws CloneNotSupportedException {
        Hero newHero = hero.clone();
        return calculatByEquip(newHero, hero.getE1(), hero.getE2(), hero.getE3(), hero.getE4(), hero.getE5(), hero.getE6());
    }

    /**
     * 根据装备计算属性值
     * @param hero
     * @param equips
     * @return
     */
    private static Hero calculatByEquip(Hero hero, Equip ...equips){
        int blood = hero.getBlood();
        int power = hero.getPower();
        int armor = hero.getArmor();
        int aggressivity = hero.getAggressivity();
        int agile = hero.getAgile();
        int magic = hero.getMagic();
        int magicResist = hero.getMagicResist();
        int mentality = hero.getMentality();
        for(Equip e : equips){
            if (e == null){
                continue;
            }
            blood += e.getBlood();
            power += e.getPower();
            armor += e.getArmor();
            aggressivity += e.getAggressivity();
            agile += e.getAgile();
            magic += e.getMagic();
            magicResist += e.getMagicResist();
            mentality += e.getMentality();
        }
        // 一点力量=19血量
        blood += (power * 19);
        // 一点智力=19魔法值
        magic += (mentality * 19);
        switch (hero.getOccupation()){
            case AGILE:
                aggressivity += agile;
                break;
            case SOLDIER:
                aggressivity += power;
                break;
            case MASTER:
                aggressivity += mentality;
        }
        hero.setBlood(blood);
        hero.setPower(power);
        hero.setArmor(armor);
        hero.setAggressivity(aggressivity);
        hero.setAgile(agile);
        hero.setMagic(magic);
        hero.setMagicResist(magicResist);
        hero.setMentality(mentality);
        return hero;
    }

    /**
     * 战斗
     * @param left
     * @param right
     * @return
     */
    public static Biology battle(Biology left, Biology right){
        System.out.println(left.getName() +"[攻击力:"+left.getAggressivity()+",血量:"+left.getBlood()+"]");
        System.out.println("---------VS----------");
        System.out.println(right.getName() +"[攻击力:"+right.getAggressivity()+",血量:"+right.getBlood()+"]");
        int i = 0;
        while (left.getBlood()>0 && right.getBlood()>0){
            i++;
            System.out.println("第 " + i + " 回合");
            attack(left, right);
            attack(right, left);
        }
        return left.getBlood()>=0?left:right;
    }

    private static void attack(Biology attacker, Biology attacked){
        /**
         * TODO 增加伤害上下限，
         * 增加护甲魔抗伤害减免
         * 增加暴击伤害
         */
        if (attacker.getBlood()<=0 || attacked.getBlood()<=0){
            return;
        }
        int agg = attacker.getAggressivity();
        int hurt = agg;
        switch (attacker.getOccupation()){
            case MASTER:
                double magicResist = attacked.getMagicResist();
                hurt = (int)(agg * (1 - magicResist/100));
                break;
            case SOLDIER:
            case AGILE:
                double armor = attacked.getArmor();
                hurt = (int)(agg * (1 - armor/100));
                break;
        }
        int blood = attacked.getBlood();
        blood = (blood - hurt)>=0 ? blood - hurt : 0;
        System.out.println(attacker.getName() + " 对 " + attacked.getName() + " 造成 " + hurt + " 点"+
                (attacker.getOccupation().equals(Occupation.MASTER)?"魔法":"物理")+"伤害！剩余血量：" + blood);
        if (blood == 0){
            System.out.println(attacked.getName() + " 死亡！");
        }
        attacked.setBlood(blood);
    }


}
