package com.yezhihun.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yezhihun.demo.GameDTO;
import com.yezhihun.demo.entity.Game;
import com.yezhihun.demo.service.GameService;
import com.yezhihun.demo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created by tianye on 2018/11/13.
 * 1,队伍比赛记录查询，条件主队、客队、半场平、加时、胜率
 * 2,队伍概况查询，场均得分、场均失分、主场场均得分、主场场均失分、客场场均得分、客场场均失分、主场胜率、客场胜率
 */
@Controller
public class GameController {
    @Autowired
    private GameService gameService;

    private String url = "http://matchweb.sports.qq.com/kbs/list?from=NBA_PC&columnId=100000&callback=ajaxExec&_=1542076893126";
    /**
     * data.matchInfo.goals.1(2,3,4).awayGoal(homeGoal)为每一节客队（主队）的得分
     * data.matchInfo.leftGoal(rightGoal) 客队，主队的总得分
     */
    //
    private String detailUrl = "http://matchweb.sports.qq.com/kbs/matchDetail?callback=fetchMatchDetailCallback1";

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView md = new ModelAndView("/index");
        md.addObject("key", 123);
        return md;
    }

    /**
     * 同步数据
     *
     * @return
     */
    @RequestMapping("/swoopTeamData")
    @ResponseBody
    public String swoopTeamData() {
        String endTime = DateUtil.formatting(new Date(), DateUtil.FORMATTING_DATE);
        String result = doGet(url + "&startTime=2018-10-17&endTime=" + endTime);
        convertGameBaseInfo(result);
        return "SUCCESS";
    }

    @RequestMapping("/queryGame")
    @ResponseBody
    public JSONObject queryGame(@RequestParam(name = "home", required = false) String home, @RequestParam(name = "guest", required = false) String guest, @RequestParam(name = "overTime", required = false) Boolean overTime, @RequestParam(name = "halfEqual", required = false) Boolean halfEqual) {
        JSONObject jsonObject = new JSONObject();

        List<Game> list = gameService.queryGame(home, guest, overTime, halfEqual);
        jsonObject.put("data", list);
        return jsonObject;
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
                String detail = doGet(detailUrl + "&mid=" + mid);
                detail = detail.substring(detail.indexOf("(") + 1, detail.lastIndexOf(")"));
                GameDTO gameDTO = JSONObject.parseObject(detail, GameDTO.class);

                Game game = convertGame(gameDTO.getData().getMatchInfo(), key);
                game.setMid(mid);

                gameService.insert(game);
            }
        }
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
