package com.yezhihun.demo.service.impl;

import com.yezhihun.demo.dao.GameDao;
import com.yezhihun.demo.entity.Game;
import com.yezhihun.demo.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by tianye on 2018/11/13.
 */
@Service
public class GameServiceImpl extends AbstractBaseServiceImpl<Game> implements GameService{

    @Autowired
    private GameDao gameDao;

    @PostConstruct
    @Override
    public void init() {
        this.baseDao = gameDao;
    }

    @Override
    public List<Game> queryGame(String home, String guest, Boolean overTime, Boolean halfEqual) {
        return gameDao.queryGame(home, guest, overTime, halfEqual);
    }
}
