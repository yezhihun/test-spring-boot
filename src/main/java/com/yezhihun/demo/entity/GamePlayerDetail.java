package com.yezhihun.demo.entity;

import javax.persistence.*;

/**
 * Created by tianye on 2018/11/16.
 */
@Entity
@Table(name = "game_player_detail")
public class GamePlayerDetail extends BaseTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 是否主场
     */
    private boolean home;
    /**
     * 球队ID
     */
    private String teamId;
    /**
     * 球队名称
     */
    private String teamName;
    /**
     * 比赛编号
     */
    private String gameNo;
    /**
     * 球员ID
     */
    private String playerId;
    /**
     * 球员名字
     */
    private String playerName;
    /**
     * 出场时间
     */
    private Integer playTime;
    /**
     * 得分
     */
    private Integer score;

    /**
     * 篮板
     */
    private Integer backboard;
    /**
     * 助攻
     */
    private Integer assist;
    /**
     * 投篮总数
     */
    private Integer totalShoot;
    /**
     * 命中投篮
     */
    private Integer hitShoot;
    /**
     * 三分投篮总数
     */
    private Integer totalThreeShoot;
    /**
     * 命中三分
     */
    private Integer hitThreeShoot;
    /**
     * 总罚球数
     */
    private Integer totalPenalty;
    /**
     * 命中罚球数
     */
    private Integer hitPenalty;
    /**
     * 抢断
     */
    private Integer steal;
    /**
     * 盖帽
     */
    private Integer block;
    /**
     * 失误
     */
    private Integer fault;
    /**
     * 犯规
     */
    private Integer foul;
    /**
     * 效率值
     */
    private Integer efficient;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isHome() {
        return home;
    }

    public void setHome(boolean home) {
        this.home = home;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getGameNo() {
        return gameNo;
    }

    public void setGameNo(String gameNo) {
        this.gameNo = gameNo;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Integer playTime) {
        this.playTime = playTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getBackboard() {
        return backboard;
    }

    public void setBackboard(Integer backboard) {
        this.backboard = backboard;
    }

    public Integer getAssist() {
        return assist;
    }

    public void setAssist(Integer assist) {
        this.assist = assist;
    }

    public Integer getTotalShoot() {
        return totalShoot;
    }

    public void setTotalShoot(Integer totalShoot) {
        this.totalShoot = totalShoot;
    }

    public Integer getHitShoot() {
        return hitShoot;
    }

    public void setHitShoot(Integer hitShoot) {
        this.hitShoot = hitShoot;
    }

    public Integer getTotalThreeShoot() {
        return totalThreeShoot;
    }

    public void setTotalThreeShoot(Integer totalThreeShoot) {
        this.totalThreeShoot = totalThreeShoot;
    }

    public Integer getHitThreeShoot() {
        return hitThreeShoot;
    }

    public void setHitThreeShoot(Integer hitThreeShoot) {
        this.hitThreeShoot = hitThreeShoot;
    }

    public Integer getTotalPenalty() {
        return totalPenalty;
    }

    public void setTotalPenalty(Integer totalPenalty) {
        this.totalPenalty = totalPenalty;
    }

    public Integer getHitPenalty() {
        return hitPenalty;
    }

    public void setHitPenalty(Integer hitPenalty) {
        this.hitPenalty = hitPenalty;
    }

    public Integer getSteal() {
        return steal;
    }

    public void setSteal(Integer steal) {
        this.steal = steal;
    }

    public Integer getBlock() {
        return block;
    }

    public void setBlock(Integer block) {
        this.block = block;
    }

    public Integer getFault() {
        return fault;
    }

    public void setFault(Integer fault) {
        this.fault = fault;
    }

    public Integer getFoul() {
        return foul;
    }

    public void setFoul(Integer foul) {
        this.foul = foul;
    }

    public Integer getEfficient() {
        return efficient;
    }

    public void setEfficient(Integer efficient) {
        this.efficient = efficient;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
