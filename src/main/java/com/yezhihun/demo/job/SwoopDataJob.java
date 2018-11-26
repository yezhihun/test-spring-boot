package com.yezhihun.demo.job;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yezhihun.demo.GameDTO;
import com.yezhihun.demo.entity.Game;
import com.yezhihun.demo.entity.GamePlayerDetail;
import com.yezhihun.demo.service.GamePlayerDetailService;
import com.yezhihun.demo.service.GameService;
import com.yezhihun.demo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by tianye on 2018/11/16.
 */
@Component
public class SwoopDataJob {
    @Autowired
    private GameService gameService;
    @Autowired
    private GamePlayerDetailService gamePlayerDetailService;

    private String url = "http://matchweb.sports.qq.com/kbs/list?from=NBA_PC&columnId=100000&callback=ajaxExec&_=1542076893126";
    /**
     * data.matchInfo.goals.1(2,3,4).awayGoal(homeGoal)为每一节客队（主队）的得分
     * data.matchInfo.leftGoal(rightGoal) 客队，主队的总得分
     */
    //
    private String detailUrl = "http://matchweb.sports.qq.com/kbs/matchDetail?callback=fetchMatchDetailCallback1";

    private String playerDetailUrl = "http://matchweb.sports.qq.com/kbs/matchStat?callback=matchStatsCallback0";

    public void swoopGameData(){
        String endTime = DateUtil.formatting(new Date(), DateUtil.FORMATTING_DATE);
//        String result = doGet(url + "&startTime=2018-10-17&endTime=" + endTime);
        String result = doGet(url + "&startTime=2018-11-15&endTime=" + endTime);
        convertGameBaseInfo(result);
    }

    private void convertGameBaseInfo(String result) {
        result = result.substring(result.indexOf("(") + 1, result.lastIndexOf(")"));
        JSONObject json = JSONObject.parseObject(result);
        Map<String, JSONArray> data = (Map<String, JSONArray>) json.get("data");
        Set<String> set = data.keySet();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String key = it.next();
            JSONArray matchInfoList = data.get(key);
            for (int i = 0; i < matchInfoList.size(); i++) {
                JSONObject matchInfo = matchInfoList.getJSONObject(i);
                String mid = matchInfo.getString("mid");
                insertGame(mid, key);
            }
        }
    }

    private void insertGame(String mid, String key){
        String detail = doGet(detailUrl + "&mid=" + mid);
        detail = detail.substring(detail.indexOf("(") + 1, detail.lastIndexOf(")"));
        GameDTO gameDTO = JSONObject.parseObject(detail, GameDTO.class);

        Game game = convertGame(gameDTO.getData().getMatchInfo(), key);
        game.setMid(mid);

        gameService.insert(game);
        insertGamePlayer(game);
    }

    private void insertGamePlayer(Game game){
        String detail = doGet(playerDetailUrl + "&mid=" + game.getMid());
        detail = detail.substring(detail.indexOf("(") + 1, detail.lastIndexOf(")"));

        JSONArray left = JSONObject.parseObject(detail).getJSONObject("data").getJSONObject("playerStats").getJSONArray("left");
        JSONArray right = JSONObject.parseObject(detail).getJSONObject("data").getJSONObject("playerStats").getJSONArray("right");
//        DetailDTO detailDTO = JSONObject.parseObject(detail, DetailDTO.class);
        convertPlayerDetail(left, game.getMid(), game.getGuestTeamId(), game.getGuestTeamName(), false);
        convertPlayerDetail(right, game.getMid(), game.getHomeTeamId(), game.getHomeTeamName(), true);
    }

    private void convertPlayerDetail(JSONArray jsonArray, String gameNo, String teamNo, String teamName, boolean home){

        for(int i=1;i<jsonArray.size();i++){
            GamePlayerDetail detail = new GamePlayerDetail();
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            JSONArray detailArray = jsonObject.getJSONArray("row");
            String playerId = jsonObject.getString("playerId");
            int playTime = 0;
            try {
                playTime = detailArray.getInteger(2);
            }catch (Exception e){

            }

            if (playTime == 0){
                continue;
            }
            String[] penalty = detailArray.getString(8).split("\\/");
            String[] shoot = detailArray.getString(6).split("\\/");
            String[] tree = detailArray.getString(7).split("\\/");
            detail.setAssist(detailArray.getInteger(5));
            detail.setBackboard(detailArray.getInteger(4));
            detail.setBlock(detailArray.getInteger(10));
            detail.setEfficient(detailArray.getInteger(13));
            detail.setFault(detailArray.getInteger(11));
            detail.setFoul(detailArray.getInteger(12));
            detail.setGameNo(gameNo);
            detail.setHitPenalty(Integer.valueOf(penalty[0]));
            detail.setHitShoot(Integer.valueOf(shoot[0]));
            detail.setHitThreeShoot(Integer.valueOf(tree[0]));
            detail.setHome(home);
            detail.setPlayerId(playerId);
            detail.setPlayerName(detailArray.getString(1));
            detail.setPlayTime(playTime);
            detail.setScore(detailArray.getInteger(3));
            detail.setSteal(detailArray.getInteger(9));
            detail.setTeamId(teamNo);
            detail.setTeamName(teamName);
            detail.setTotalPenalty(Integer.valueOf(penalty[1]));
            detail.setTotalShoot(Integer.valueOf(shoot[1]));
            detail.setTotalThreeShoot(Integer.valueOf(tree[1]));

            gamePlayerDetailService.insert(detail);
        }
    }

    private int convertNum(String str){
        if (str.startsWith("-")){
            str = str.substring(1);
            return 0-Integer.valueOf(str);
        }
        return Integer.valueOf(str);
    }

    private Goal getSocre(JSONObject jsonObject, String index) {
        Goal goal = null;
        if (jsonObject.containsKey(index)) {
            goal = new Goal();
            JSONObject detail = (JSONObject) jsonObject.get(index);
            goal.setAwayGoal(detail.getInteger("awayGoal"));
            goal.setHomeGoal(detail.getInteger("homeGoal"));
        }
        return goal;
    }

    private Game convertGame(GameDTO.Data.MatchInfo matchInfo, String gameDate) {
        JSONObject jsonObject = matchInfo.getGoals();
        Goal score1 = getSocre(jsonObject, "1");
        Goal score2 = getSocre(jsonObject, "2");
        Goal score3 = getSocre(jsonObject, "3");
        Goal score4 = getSocre(jsonObject, "4");
        Goal overScore1 = getSocre(jsonObject, "5");
        Goal overScore2 = getSocre(jsonObject, "6");
        Goal overScore3 = getSocre(jsonObject, "7");
        Goal totalScore = getSocre(jsonObject, "10");

        Game game = new Game();
        game.setGameDate(gameDate);
        game.setGuestOverScore1(overScore1 == null ? null : overScore1.getAwayGoal());
        game.setGuestOverScore2(overScore2 == null ? null : overScore2.getAwayGoal());
        game.setGuestOverScore3(overScore3 == null ? null : overScore3.getAwayGoal());
        game.setGuestScore1(score1.getAwayGoal());
        game.setGuestScore2(score2.getAwayGoal());
        game.setGuestScore3(score3.getAwayGoal());
        game.setGuestScore4(score4.getAwayGoal());
        game.setGuestTeamId(matchInfo.getLeftTeamId());
        game.setGuestTeamName(matchInfo.getLeftName());
        game.setGuestTotalScore(totalScore.getAwayGoal());

        game.setHomeOverScore1(overScore1 == null ? null : overScore1.getHomeGoal());
        game.setHomeOverScore2(overScore2 == null ? null : overScore2.getHomeGoal());
        game.setHomeOverScore3(overScore3 == null ? null : overScore3.getHomeGoal());
        game.setHomeScore1(score1.getHomeGoal());
        game.setHomeScore2(score2.getHomeGoal());
        game.setHomeScore3(score3.getHomeGoal());
        game.setHomeScore4(score4.getHomeGoal());
        game.setHomeTeamId(matchInfo.getRightTeamId());
        game.setHomeTeamName(matchInfo.getRightName());
        game.setHomeTotalScore(totalScore.getHomeGoal());

        game.setOverTime(overScore1 != null);
        game.setHalfEqual(score1.getAwayGoal() + score2.getAwayGoal() == score1.getHomeGoal() + score2.getHomeGoal());
        return game;
    }

    class DetailDTO{

        private Data data;

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        class Data{
            private PlayerStats playerStats;

            public PlayerStats getPlayerStats() {
                return playerStats;
            }

            public void setPlayerStats(PlayerStats playerStats) {
                this.playerStats = playerStats;
            }


            class PlayerStats{

                private Team left;
                private Team right;

                public Team getLeft() {
                    return left;
                }

                public void setLeft(Team left) {
                    this.left = left;
                }

                public Team getRight() {
                    return right;
                }

                public void setRight(Team right) {
                    this.right = right;
                }

                class Team{
                    private JSONArray jsonArray;

                    public JSONArray getJsonArray() {
                        return jsonArray;
                    }

                    public void setJsonArray(JSONArray jsonArray) {
                        this.jsonArray = jsonArray;
                    }
                }
            }
        }
    }

    class Goal {
        private Integer awayGoal;
        private Integer homeGoal;

        public Integer getAwayGoal() {
            return awayGoal;
        }

        public void setAwayGoal(Integer awayGoal) {
            this.awayGoal = awayGoal;
        }

        public Integer getHomeGoal() {
            return homeGoal;
        }

        public void setHomeGoal(Integer homeGoal) {
            this.homeGoal = homeGoal;
        }
    }

    public String doGet(String httpurl) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL(httpurl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            connection.disconnect();// 关闭远程连接
        }

        return result;
    }
}
