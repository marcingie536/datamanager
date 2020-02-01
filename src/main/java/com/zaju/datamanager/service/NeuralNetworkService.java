package com.zaju.datamanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.zaju.datamanager.domain.NeuralNetwork;
import com.zaju.datamanager.repository.NeuralNetworkRepository;

@Service
public class NeuralNetworkService {

	private NeuralNetworkRepository neuralNetworkRepository;

	public NeuralNetworkService(NeuralNetworkRepository neuralNetworkRepository) {
		this.neuralNetworkRepository = neuralNetworkRepository;
	}

	public NeuralNetwork save(NeuralNetwork neuralNetwork) {
		return neuralNetworkRepository.save(neuralNetwork);
	}

	public Optional<NeuralNetwork> findById(Long neuralNetworkId) {
		return neuralNetworkRepository.findById(neuralNetworkId);
	}

	public List<NeuralNetwork> findAll(int page, int size) {
		Page<NeuralNetwork> neuralNetworkPage = neuralNetworkRepository.findAll(PageRequest.of(page, size));

		if (neuralNetworkPage.isEmpty())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);

		return neuralNetworkPage.getContent();
	}

	public List<NeuralNetwork> findAllByUserId(Long userId, int page, int size) {
		Page<NeuralNetwork> neuralNetworkPage = neuralNetworkRepository.findAllByUserId(userId, PageRequest.of(page, size));

		if (neuralNetworkPage.isEmpty())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);

		return neuralNetworkPage.getContent();
	}
}
