package com.yezhihun.demo;

import com.yezhihun.demo.entity.template.MonsterTemplate;
import com.yezhihun.demo.enums.Occupation;
import com.yezhihun.demo.service.MonsterTemplateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class MonsterTemplateTests {

	@Autowired
	private MonsterTemplateService monsterTemplateService;

	@Test
	public void contextLoads() {
		MonsterTemplate monsterTemplate = new MonsterTemplate();
		monsterTemplate.setLevel(1);
		monsterTemplate.setName("史莱姆");
		monsterTemplate.setPower(10);
		monsterTemplate.setMentality(1);
		monsterTemplate.setMagicResist(0);
		monsterTemplate.setAggressivity(10);
		monsterTemplate.setBlood(100);
		monsterTemplate.setOccupation(Occupation.SOLDIER);

		monsterTemplateService.insert(monsterTemplate);
	}

}
