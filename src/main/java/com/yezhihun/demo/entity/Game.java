package com.yezhihun.demo.entity;

import javax.persistence.*;

/**
 * Created by tianye on 2018/11/13.
 */
@Entity
@Table(name = "game")
public class Game extends BaseTable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "mid", unique = true)
    private String mid;
    /**
     * 加时
     */
    private boolean overTime;
    /**
     * 半场平
     */
    private boolean halfEqual;
    /**
     * 主队
     */
    private String homeTeamId;
    private String homeTeamName;
    /**
     * 客队
     */
    private String guestTeamId;
    private String guestTeamName;
    /**
     * 比赛日期
     */
    private String gameDate;
    /**
     * 主队第一节得分
     */
    private Integer homeScore1;
    /**
     * 主队第二节得分
     */
    private Integer homeScore2;
    /**
     * 主队第三节得分
     */
    private Integer homeScore3;
    /**
     * 主队第四节得分
     */
    private Integer homeScore4;
    /**
     * 主队加时1得分
     */
    private Integer homeOverScore1;
    /**
     * 主队加时2得分
     */
    private Integer homeOverScore2;
    /**
     * 主队加时3得分
     */
    private Integer homeOverScore3;
    /**
     * 主队总得分
     */
    private Integer homeTotalScore;
    /**
     * 客队第一节得分
     */
    private Integer guestScore1;
    /**
     * 客队第二节得分
     */
    private Integer guestScore2;
    /**
     * 客队第三节得分
     */
    private Integer guestScore3;
    /**
     * 客队第四节得分
     */
    private Integer guestScore4;
    /**
     * 客队加时1得分
     */
    private Integer guestOverScore1;
    /**
     * 客队加时2得分
     */
    private Integer guestOverScore2;
    /**
     * 客队加时三得分
     */
    private Integer guestOverScore3;
    /**
     * 客队总得分
     */
    private Integer guestTotalScore;
    /**
     * 篮板
     * 助攻
     * 盖帽
     * 失误
     * 罚球
     * 总罚球
     * 罚球命中率
     * 三分
     * 总三分投篮
     * 三分命中率
     * 总投篮
     * 投篮命中率
     * 犯规
     */

    /**
     * 描述
     */
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public Integer getHomeScore1() {
        return homeScore1;
    }

    public void setHomeScore1(Integer homeScore1) {
        this.homeScore1 = homeScore1;
    }

    public Integer getHomeScore2() {
        return homeScore2;
    }

    public void setHomeScore2(Integer homeScore2) {
        this.homeScore2 = homeScore2;
    }

    public Integer getHomeScore3() {
        return homeScore3;
    }

    public void setHomeScore3(Integer homeScore3) {
        this.homeScore3 = homeScore3;
    }

    public Integer getHomeScore4() {
        return homeScore4;
    }

    public void setHomeScore4(Integer homeScore4) {
        this.homeScore4 = homeScore4;
    }

    public Integer getHomeOverScore1() {
        return homeOverScore1;
    }

    public void setHomeOverScore1(Integer homeOverScore1) {
        this.homeOverScore1 = homeOverScore1;
    }

    public Integer getHomeOverScore2() {
        return homeOverScore2;
    }

    public void setHomeOverScore2(Integer homeOverScore2) {
        this.homeOverScore2 = homeOverScore2;
    }

    public Integer getHomeOverScore3() {
        return homeOverScore3;
    }

    public void setHomeOverScore3(Integer homeOverScore3) {
        this.homeOverScore3 = homeOverScore3;
    }

    public Integer getHomeTotalScore() {
        return homeTotalScore;
    }

    public void setHomeTotalScore(Integer homeTotalScore) {
        this.homeTotalScore = homeTotalScore;
    }

    public Integer getGuestScore1() {
        return guestScore1;
    }

    public void setGuestScore1(Integer guestScore1) {
        this.guestScore1 = guestScore1;
    }

    public Integer getGuestScore2() {
        return guestScore2;
    }

    public void setGuestScore2(Integer guestScore2) {
        this.guestScore2 = guestScore2;
    }

    public Integer getGuestScore3() {
        return guestScore3;
    }

    public void setGuestScore3(Integer guestScore3) {
        this.guestScore3 = guestScore3;
    }

    public Integer getGuestScore4() {
        return guestScore4;
    }

    public void setGuestScore4(Integer guestScore4) {
        this.guestScore4 = guestScore4;
    }

    public Integer getGuestOverScore1() {
        return guestOverScore1;
    }

    public void setGuestOverScore1(Integer guestOverScore1) {
        this.guestOverScore1 = guestOverScore1;
    }

    public Integer getGuestOverScore2() {
        return guestOverScore2;
    }

    public void setGuestOverScore2(Integer guestOverScore2) {
        this.guestOverScore2 = guestOverScore2;
    }

    public Integer getGuestOverScore3() {
        return guestOverScore3;
    }

    public void setGuestOverScore3(Integer guestOverScore3) {
        this.guestOverScore3 = guestOverScore3;
    }

    public Integer getGuestTotalScore() {
        return guestTotalScore;
    }

    public void setGuestTotalScore(Integer guestTotalScore) {
        this.guestTotalScore = guestTotalScore;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getGuestTeamName() {
        return guestTeamName;
    }

    public void setGuestTeamName(String guestTeamName) {
        this.guestTeamName = guestTeamName;
    }

    public String getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(String homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public String getGuestTeamId() {
        return guestTeamId;
    }

    public void setGuestTeamId(String guestTeamId) {
        this.guestTeamId = guestTeamId;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public boolean isOverTime() {
        return overTime;
    }

    public void setOverTime(boolean overTime) {
        this.overTime = overTime;
    }

    public boolean isHalfEqual() {
        return halfEqual;
    }

    public void setHalfEqual(boolean halfEqual) {
        this.halfEqual = halfEqual;
    }
}
