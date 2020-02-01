package com.zaju.datamanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.zaju.datamanager.domain.NeuralNetwork;

public interface NeuralNetworkRepository extends PagingAndSortingRepository<NeuralNetwork, Long> {
	
	Page<NeuralNetwork> findAllByUserId(Long userId, Pageable pageable);
}
