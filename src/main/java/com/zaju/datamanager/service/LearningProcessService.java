package com.zaju.datamanager.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.zaju.datamanager.domain.LearningProcess;
import com.zaju.datamanager.domain.NeuralNetwork;
import com.zaju.datamanager.repository.LearningProcessRepository;

@Service
public class LearningProcessService {

	private LearningProcessRepository learningProcessRepository;

	public LearningProcessService(LearningProcessRepository learningProcessRepository) {
		this.learningProcessRepository = learningProcessRepository;
	}
	
	public LearningProcess save(LearningProcess learningProcess) {
		return learningProcessRepository.save(learningProcess);
	}
	
	public List<LearningProcess> findAllByNeuralNetworkId(Long neuralNetworkId, int page, int size){
		NeuralNetwork neuralNetwork = new NeuralNetwork();
		neuralNetwork.setId(neuralNetworkId);

		Page<LearningProcess> learningProcessPage = learningProcessRepository.findAllByNeuralNetwork(neuralNetwork, PageRequest.of(page, size));
		
		if (learningProcessPage.isEmpty())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		
		return learningProcessPage.getContent();
	}
}
