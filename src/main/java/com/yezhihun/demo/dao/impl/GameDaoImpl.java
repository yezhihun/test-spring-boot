package com.yezhihun.demo.dao.impl;

import com.yezhihun.demo.dao.BaseDaoImpl;
import com.yezhihun.demo.entity.Game;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by tianye on 2018/11/13.
 */
public class GameDaoImpl extends BaseDaoImpl{

    public List<Game> queryGame(String home, String guest, Boolean overTime, Boolean halfEqual){
        StringBuilder sql = new StringBuilder("select g.* from game g where 1=1");
        if (home != null){
            sql.append(" AND g.homeTeamId=" + home);
        }
        if (guest != null){
            sql.append(" AND g.guestTeamId=" + guest);
        }
        if (overTime != null){
            sql.append(" AND g.overTime =" + overTime);
        }
        if (halfEqual != null){
            sql.append(" AND g.halfEqual=" + halfEqual);
        }
        Query q = entityManager.createNativeQuery(sql.toString());
        List<Game> list = (List<Game>)q.getResultList();
        return list;
    }
}
