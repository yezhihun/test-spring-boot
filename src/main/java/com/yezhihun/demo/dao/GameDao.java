package com.yezhihun.demo.dao;

import com.yezhihun.demo.entity.Game;
import com.yezhihun.demo.util.PageModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tianye on 2018/11/13.
 */
@Repository
public interface GameDao extends BaseDao<Game> {

    List<Game> queryGame(String home, String guest, Boolean overTime, Boolean halfEqual);

    List<Game> findByHomeTeamIdAndGuestTeamIdAndOverTimeAndHalfEqual(String homeTeamId, String guestTeamId, Boolean overTime, Boolean halfEqual);

    List<Game> queryGameList(String team, Boolean isHome, String team2, PageModel page);

    Long countGameList(String team, Boolean isHome, String team2);

    Game findByMid(String mid);
}
