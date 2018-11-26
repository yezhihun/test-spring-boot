package com.yezhihun.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.yezhihun.demo.entity.Game;
import com.yezhihun.demo.job.SwoopDataJob;
import com.yezhihun.demo.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by tianye on 2018/11/13.
 * 1,队伍比赛记录查询，条件主队、客队、半场平、加时、胜率
 * 2,队伍概况查询，场均得分、场均失分、主场场均得分、主场场均失分、客场场均得分、客场场均失分、主场胜率、客场胜率
 * 3,个人数据统计（根据主客场，对战双方）
 */
@Controller
public class GameController {
    @Autowired
    private GameService gameService;
    @Autowired
    private SwoopDataJob swoopDataJob;

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
        swoopDataJob.swoopGameData();
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


}
