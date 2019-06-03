package com.yezhihun.demo;

import com.yezhihun.demo.entity.checkpoint.CheckPoint;
import com.yezhihun.demo.service.CheckPointService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CheckPointServiceTests {
	@Autowired
	private CheckPointService checkPointService;
	@Test
	public void contextLoads() {
		CheckPoint checkPoint = new CheckPoint();
		checkPoint.setLevel(1);
		checkPoint.setMaxMonsterLevel(5);
		checkPoint.setMinMonsterLevel(1);
		checkPoint.setName("史莱姆");
	}

}
