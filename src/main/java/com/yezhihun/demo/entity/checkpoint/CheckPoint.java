package com.yezhihun.demo.entity.checkpoint;

import com.yezhihun.demo.entity.BaseTable;

import javax.persistence.*;

/**
 * Created by tianye on 2019/5/25.
 * 地图
 */
@Entity
@Table(name = "check_point")
public class CheckPoint extends BaseTable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 地图名称
     */
    @Column(name = "name", nullable = false, columnDefinition = "varchar(100) COMMENT '地图名称'")
    protected String name;
    /**
     * 地图等级
     */
    @Column(name = "level", nullable = false, columnDefinition = "int default 1 COMMENT '地图等级'")
    protected int level;
    /**
     * 最低怪物等级
     */
    @Column(name = "min_monster_level", nullable = false, columnDefinition = "int default 0 COMMENT '最低怪物等级'")
    protected int minMonsterLevel;
    /**
     * 最高怪物等级
     */
    @Column(name = "max_monster_level", nullable = false, columnDefinition = "int default 0 COMMENT '最高怪物等级'")
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
