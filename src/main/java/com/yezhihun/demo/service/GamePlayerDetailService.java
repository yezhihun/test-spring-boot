package com.yezhihun.demo.service;

import com.yezhihun.demo.entity.GamePlayerDetail;

import java.util.List;

/**
 * Created by tianye on 2018/11/16.
 */
public interface GamePlayerDetailService extends BaseService<GamePlayerDetail>{

    List<GamePlayerDetail> selectDetailByMidAndTeamId(String mid, String teamId);
}
