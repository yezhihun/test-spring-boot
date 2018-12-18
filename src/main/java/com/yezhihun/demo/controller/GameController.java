package com.yezhihun.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.yezhihun.demo.entity.Game;
import com.yezhihun.demo.entity.GamePlayerDetail;
import com.yezhihun.demo.job.SwoopDataJob;
import com.yezhihun.demo.service.GamePlayerDetailService;
import com.yezhihun.demo.service.GameService;
import com.yezhihun.demo.util.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by tianye on 2018/11/13.
 * 1,队伍比赛记录查询，条件主队、客队、半场平、加时、胜率
 * 2,队伍概况查询，场均得分、场均失分、主场场均得分、主场场均失分、客场场均得分、客场场均失分、主场胜率、客场胜率
 * 3,个人数据统计（根据主客场，对战双方）
 * 4,比赛胜负、大小分预测，出战主力、主客场、连胜、背靠背、历史记录，
 *
 * 根据对阵双方，选择伤病名单，
 */
@Controller
public class GameController {
    @Autowired
    private GameService gameService;
    @Autowired
    private GamePlayerDetailService gamePlayerDetailService;
    @Autowired
    private SwoopDataJob swoopDataJob;

    @RequestMapping("/index")
    public String index() {
        ModelAndView md = new ModelAndView("index");
        md.addObject("key", 123);
        return "index";
    }

    @RequestMapping("/showGameList")
    public String showGameList(){
        return "app/gameList";
    }

    @RequestMapping("/queryGameList")
    @ResponseBody
    public JSONObject queryGameList(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        PageModel page = new PageModel();

        String team = request.getParameter("team");
        String showDiff = request.getParameter("showDiff");
        String home = request.getParameter("home");
        String team2 = request.getParameter("team2");
        page.setPageNo(Integer.valueOf(request.getParameter("page")));
        page.setPageSize(Integer.valueOf(request.getParameter("rows")));

        page = gameService.queryGameList(team, "true".equals(showDiff)?"1".equals(home):null, "".equals(team2)?null:team2, page);

        jsonObject.put("rows", page.getRows());
        jsonObject.put("total", page.getTotal());
        return jsonObject;
    }

    @RequestMapping("/queryGameDetailForMid")
    @ResponseBody
    public JSONObject queryGameDetailForMid(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String mid = request.getParameter("mid");
        Game game = gameService.selectByMid(mid);

        List<GamePlayerDetail> homeDetail = gamePlayerDetailService.selectDetailByMidAndTeamId(mid, game.getHomeTeamId());
        List<GamePlayerDetail> guestDetail = gamePlayerDetailService.selectDetailByMidAndTeamId(mid, game.getGuestTeamId());


        jsonObject.put("game", game);
        jsonObject.put("homeDetail", homeDetail);
        jsonObject.put("guestDetail", guestDetail);
        return jsonObject;
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
