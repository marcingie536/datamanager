package com.zaju.datamanager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zaju.datamanager.neuralnetwork.service.MultilayerPerceptronService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatamanagerApplicationTests {

	@Autowired
	private MultilayerPerceptronService multilayerPerceptronService;
	
	
//	@Test
//	public void learnNN() {
//		multilayerPerceptronService.startLearningProcess();
//		System.out.println();
//	}

}
