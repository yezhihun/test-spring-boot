package com.yezhihun.demo.controller;

import com.yezhihun.demo.service.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tianye on 2019/5/25.
 */
@RestController
public class CheckPointController {
    @Autowired
    private MonsterService monsterService;


}
