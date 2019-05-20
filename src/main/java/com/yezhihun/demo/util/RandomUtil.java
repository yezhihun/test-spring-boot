package com.yezhihun.demo.util;

import com.yezhihun.demo.enums.Occupation;

/**
 * Created by tianye on 2019/5/20.
 */
public class RandomUtil {

    public static Occupation getRandomOccupation(){
        int random = (int)(Math.random()*100);
        for (Occupation o : Occupation.values()){
            o.getValue();
        }
        return null;
    }
}
