package com.yezhihun.demo.entity.map;

/**
 * Created by tianye on 2019/5/25.
 * 地图
 */
public class Map {

    /**
     * 地图名称
     */
    protected String name;
    /**
     * 地图等级
     */
    protected int level;
    /**
     * 最低怪物等级
     */
    protected int minMonsterLevel;
    /**
     * 最高怪物等级
     */
    protected int maxMonsterLevel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinMonsterLevel() {
        return minMonsterLevel;
    }

    public void setMinMonsterLevel(int minMonsterLevel) {
        this.minMonsterLevel = minMonsterLevel;
    }

    public int getMaxMonsterLevel() {
        return maxMonsterLevel;
    }

    public void setMaxMonsterLevel(int maxMonsterLevel) {
        this.maxMonsterLevel = maxMonsterLevel;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
