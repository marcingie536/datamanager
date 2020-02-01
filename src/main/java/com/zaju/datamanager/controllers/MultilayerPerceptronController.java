package com.zaju.datamanager.controllers;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zaju.datamanager.domain.LearningProcess;
import com.zaju.datamanager.domain.TestingProcess;
import com.zaju.datamanager.dto.LearningProcessDTO;
import com.zaju.datamanager.dto.TestingProcessDTO;
import com.zaju.datamanager.neuralnetwork.service.MultilayerPerceptronService;

@RestController
@RequestMapping("/api/multilayer-perceptron")
public class MultilayerPerceptronController {

	private MultilayerPerceptronService multilayerPerceptronService;
	private ModelMapper modelMapper;
	
	public MultilayerPerceptronController(MultilayerPerceptronService multilayerPerceptronService, ModelMapper modelMapper) {
		this.multilayerPerceptronService = multilayerPerceptronService;
		this.modelMapper = modelMapper;
	}
	
	@PostMapping("/startLearningProcess/uploadCsvData")
	public ResponseEntity<LearningProcessDTO> startMultilayerPerceptronLearningProcess(@RequestParam MultipartFile file,
			@RequestParam Long neuralNetworkId, @RequestParam int hiddenLayersCount,
			@RequestParam String hiddenLayersNeuronsCount, @RequestParam double learningRate,
			@RequestParam double maxDesiredError, @RequestParam int maxIterationsCount) throws IOException {
		
		LearningProcess newLearningProcess = multilayerPerceptronService.learnMultilayerPerceptron(file, neuralNetworkId, hiddenLayersCount,
				hiddenLayersNeuronsCount, learningRate, maxDesiredError, maxIterationsCount);
		
		return ResponseEntity.ok(modelMapper.map(newLearningProcess, LearningProcessDTO.class));
	}
	
	@PostMapping("/testMultilayerPerceptron/uploadCsvData")
	public ResponseEntity<TestingProcessDTO> testMultilayerPerceptron(@RequestParam MultipartFile file, @RequestParam Long structureId, @RequestParam(required = false) String description) throws IOException {
		
		TestingProcess newTestingProcess = multilayerPerceptronService.testMultilayerPerceptron(file, structureId, description);
		
		return ResponseEntity.ok(modelMapper.map(newTestingProcess, TestingProcessDTO.class));
	}
}
