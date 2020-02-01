package com.zaju.datamanager.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zaju.datamanager.domain.LearningProcess;
import com.zaju.datamanager.domain.NeuralNetwork;
import com.zaju.datamanager.domain.TestingProcess;
import com.zaju.datamanager.dto.LearningProcessDTO;
import com.zaju.datamanager.dto.NeuralNetworkDTO;
import com.zaju.datamanager.dto.TestingProcessDTO;
import com.zaju.datamanager.service.LearningProcessService;
import com.zaju.datamanager.service.NeuralNetworkService;
import com.zaju.datamanager.service.TestingProcessService;

@RestController
@RequestMapping("/api/neural-network")
public class NeuralNetworkContoller {

	private NeuralNetworkService neuralNetworkService;
	private LearningProcessService learningProcessService;
	private TestingProcessService testingProcessService;
	private ModelMapper modelMapper;

	public NeuralNetworkContoller(NeuralNetworkService neuralNetworkService, LearningProcessService learningProcessService, TestingProcessService testingProcessService, ModelMapper modelMapper) {
		this.neuralNetworkService = neuralNetworkService;
		this.learningProcessService = learningProcessService;
		this.testingProcessService = testingProcessService;
		this.modelMapper = modelMapper;
	}

	@GetMapping
	public ResponseEntity<List<NeuralNetworkDTO>> getAll(@RequestParam int page, @RequestParam int size) {
		List<NeuralNetwork> neuralNetworks = neuralNetworkService.findAll(page, size);

		List<NeuralNetworkDTO> neuralNetowrksResult = neuralNetworks.stream()
				.map(nn -> modelMapper.map(nn, NeuralNetworkDTO.class)).collect(Collectors.toList());
		
		return ResponseEntity.ok(neuralNetowrksResult);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<NeuralNetworkDTO>> getAllByUserId(@PathVariable("userId") Long userId, @RequestParam int page, @RequestParam int size){
		List<NeuralNetwork> neuralNetworks = neuralNetworkService.findAllByUserId(userId, page, size);

		List<NeuralNetworkDTO> neuralNetowrksResult = neuralNetworks.stream()
				.map(nn -> modelMapper.map(nn, NeuralNetworkDTO.class)).collect(Collectors.toList());
		
		return ResponseEntity.ok(neuralNetowrksResult);
	}
	
	@GetMapping("/{neuralNetworkId}/learning-process")
	public ResponseEntity<List<LearningProcessDTO>> geatAllLearningProcesses(@PathVariable("neuralNetworkId") Long neuralNetworkId, @RequestParam int page, @RequestParam int size){
		List<LearningProcess> learningProcesses = learningProcessService.findAllByNeuralNetworkId(neuralNetworkId, page, size);
		
		List<LearningProcessDTO> learningProcessResult =  learningProcesses.stream()
				.map(lp -> learningProcessToDTO(lp)).collect(Collectors.toList());
		
		return ResponseEntity.ok(learningProcessResult);
	}
	
	@GetMapping("/learning-process/{structureId}/testing-process")
	public ResponseEntity<List<TestingProcessDTO>> getAllTestingProcesses(@PathVariable("structureId") Long structureId, @RequestParam int page, @RequestParam int size){
		List<TestingProcess> testingProcesses = testingProcessService.findAllByStructureId(structureId, page, size);
		
		List<TestingProcessDTO> testingProcessResult = testingProcesses.stream()
				.map(tp -> modelMapper.map(tp, TestingProcessDTO.class)).collect(Collectors.toList());
		
		return ResponseEntity.ok(testingProcessResult);
	}
	
	

	@PostMapping("/create")
	public NeuralNetworkDTO createNeuralNetwork(@RequestBody NeuralNetworkDTO neuralNetworkDTO) {

		NeuralNetwork neuralNetworkEntity = modelMapper.map(neuralNetworkDTO, NeuralNetwork.class);

		NeuralNetwork newNeuralNetwork = neuralNetworkService.save(neuralNetworkEntity);

		return modelMapper.map(newNeuralNetwork, NeuralNetworkDTO.class);
	}
	
	private LearningProcessDTO learningProcessToDTO(LearningProcess learningProcess) {
		LearningProcessDTO learningProcessDTO = modelMapper.map(learningProcess, LearningProcessDTO.class);
		learningProcessDTO.setStructureId(learningProcess.getMultilayerPerceptronDBFile().getId());
		return learningProcessDTO;
	}
}
