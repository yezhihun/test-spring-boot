package com.yezhihun.demo.dao.impl;

import com.yezhihun.demo.dao.BaseDaoImpl;
import com.yezhihun.demo.entity.Game;
import com.yezhihun.demo.util.PageModel;

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

    public List<Game> queryGameList(String team, Boolean isHome, String team2, PageModel page){
        StringBuilder baseSql = new StringBuilder("select g from Game g where 1=1");
        String sql = createGameListSql(team, isHome, team2, baseSql);
        Query q = entityManager.createQuery(sql);
        q.setParameter("team", team);
        if (team2 != null && !"".equals(team2)){
            q.setParameter("team2", team2);
        }
        q.setFirstResult(page.getStartIndex());
        q.setMaxResults(page.getPageSize());
        List<Game> list = (List<Game>)q.getResultList();
        return list;
    }

    public long countGameList(String team, Boolean isHome, String team2){
        StringBuilder baseSql = new StringBuilder("select count(g) from Game g where 1=1");
        String sql = createGameListSql(team, isHome, team2, baseSql);
        Query q = entityManager.createQuery(sql);
        q.setParameter("team", team);
        if (team2 != null && !"".equals(team2)){
            q.setParameter("team2", team2);
        }

        Long count = (Long)q.getSingleResult();
        return count;
    }

    private String createGameListSql(String team, Boolean isHome, String team2, StringBuilder baseSql){
        if (isHome != null){
            if (isHome){
                baseSql.append(" AND g.homeTeamId=:team");
            }else {
                baseSql.append(" AND g.guestTeamId=:team");
            }
        } else {
            baseSql.append(" AND (g.homeTeamId=:team").append(" OR g.guestTeamId=:team)");
        }

        if (team2 != null && !"".equals(team2)){
            baseSql.append(" AND (g.homeTeamId=:team2").append(" OR g.guestTeamId=:team2)");
        }

        return baseSql.toString();
    }
}
