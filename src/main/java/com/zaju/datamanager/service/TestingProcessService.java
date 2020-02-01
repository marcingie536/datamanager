package com.zaju.datamanager.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.zaju.datamanager.domain.MultilayerPerceptronDBFile;
import com.zaju.datamanager.domain.TestingProcess;
import com.zaju.datamanager.repository.TestingProcessRepository;

@Service
public class TestingProcessService {
	
	private TestingProcessRepository testingProcessRepository;
	
	public TestingProcessService(TestingProcessRepository testingProcessRepository) {
		this.testingProcessRepository = testingProcessRepository;
	}

	public TestingProcess save(TestingProcess testingProcess) {
		return testingProcessRepository.save(testingProcess);
	}
	
	public List<TestingProcess> findAllByStructureId(Long structureId, int page, int size){
		MultilayerPerceptronDBFile multilayerPerceptronDBFile = new MultilayerPerceptronDBFile();
		multilayerPerceptronDBFile.setId(structureId);
		
		Page<TestingProcess> testingProcessPage = testingProcessRepository.findAllByMultilayerPerceptronDBFile(multilayerPerceptronDBFile, PageRequest.of(page, size));
		
		if (testingProcessPage.isEmpty())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		
		return testingProcessPage.getContent();
	}
}
