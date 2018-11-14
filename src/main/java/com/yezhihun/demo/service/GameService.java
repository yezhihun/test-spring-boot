package com.yezhihun.demo.service;

import com.yezhihun.demo.entity.Game;

import java.util.List;

/**
 * Created by tianye on 2018/11/13.
 */
public interface GameService extends BaseService<Game>{

    List<Game> queryGame(String home, String guest, Boolean overTime, Boolean halfEqual);
}
