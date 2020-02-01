package com.zaju.datamanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.zaju.datamanager.domain.MultilayerPerceptronDBFile;
import com.zaju.datamanager.domain.TestingProcess;

public interface TestingProcessRepository extends PagingAndSortingRepository<TestingProcess, Long> {
	Page<TestingProcess> findAllByMultilayerPerceptronDBFile(MultilayerPerceptronDBFile multilayerPerceptronDBFile, Pageable pageable);
}
