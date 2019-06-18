package com.jgw.supercodeplatform.mapper;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hunqingplatform.HunQingApplication;
import com.hunqingplatform.hunqing.dao.ext.HqProjectMapperExt;
import com.hunqingplatform.hunqing.dto.MainPageSearchDTO;
import com.hunqingplatform.hunqing.pojo.HqProject;

@RunWith(SpringRunner.class)   
@SpringBootTest(classes={HunQingApplication.class})// 指定启动类
public class HqProjectMapperExtTest {
 @Autowired
 private HqProjectMapperExt dao;
 
	@Test
	public void contextLoads() {
		
//		List<MainPageSearchDTO> list=new ArrayList<MainPageSearchDTO>();
//		MainPageSearchDTO m1=new MainPageSearchDTO();
//		m1.setTagId(1);
//		m1.setTagPropName("中式");
//		
//		MainPageSearchDTO m2=new MainPageSearchDTO();
//		m2.setTagId(2);
//		m2.setTagPropName("红色");
//		
//		list.add(m1);
//		list.add(m2);
//		List<HqProject> projects=dao.mainSearch(list);
//		System.out.println(projects);
	}

}
