package com.yezhihun.demo.service;

import com.yezhihun.demo.entity.Hero;
import com.yezhihun.demo.entity.checkpoint.CheckPoint;

import java.util.Map;

/**
 * Created by tianye on 2019/5/25.
 */
public interface CheckPointService extends BaseService<CheckPoint>{

    Map<String, Object> battleMap(Hero hero, CheckPoint checkPoint);
}
