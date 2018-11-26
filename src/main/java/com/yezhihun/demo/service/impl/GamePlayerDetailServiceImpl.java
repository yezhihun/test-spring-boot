package com.yezhihun.demo.service.impl;

import com.yezhihun.demo.dao.GamePlayerDetailDao;
import com.yezhihun.demo.entity.GamePlayerDetail;
import com.yezhihun.demo.service.GamePlayerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by tianye on 2018/11/16.
 */
@Service
public class GamePlayerDetailServiceImpl extends AbstractBaseServiceImpl<GamePlayerDetail> implements GamePlayerDetailService{

    @Autowired
    private GamePlayerDetailDao gamePlayerDetailDao;

    @PostConstruct
    @Override
    public void init() {
        this.baseDao = gamePlayerDetailDao;
    }
}
