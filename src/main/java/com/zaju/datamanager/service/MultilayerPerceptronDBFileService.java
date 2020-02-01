package com.zaju.datamanager.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zaju.datamanager.domain.MultilayerPerceptronDBFile;
import com.zaju.datamanager.repository.MultilayerPerceptronDBFileRepository;

@Service
public class MultilayerPerceptronDBFileService {
	
	private MultilayerPerceptronDBFileRepository multilayerPerceptronDBFileRepository;
	
	public MultilayerPerceptronDBFileService(MultilayerPerceptronDBFileRepository multilayerPerceptronDBFileRepository) {
		this.multilayerPerceptronDBFileRepository = multilayerPerceptronDBFileRepository;
	}

	public Optional<MultilayerPerceptronDBFile> getById(Long id) {
		return multilayerPerceptronDBFileRepository.findById(id);
	}
}
