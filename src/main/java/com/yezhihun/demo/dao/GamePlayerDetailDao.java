package com.yezhihun.demo.dao;

import com.yezhihun.demo.entity.GamePlayerDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tianye on 2018/11/16.
 */
@Repository
public interface GamePlayerDetailDao extends BaseDao<GamePlayerDetail>{

    List<GamePlayerDetail> findByGameNoAndTeamIdOrderByScoreDesc(String gameNo, String teamId);
}
