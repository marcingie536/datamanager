package com.zaju.datamanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.zaju.datamanager.domain.LearningProcess;
import com.zaju.datamanager.domain.NeuralNetwork;

public interface LearningProcessRepository extends PagingAndSortingRepository<LearningProcess, Long>{
	
	Page<LearningProcess> findAllByNeuralNetwork(NeuralNetwork neuralNetwork, Pageable pageable);
}
