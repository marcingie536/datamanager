package com.zaju.datamanager.service;

import org.springframework.stereotype.Service;

import com.zaju.datamanager.domain.OperationHistory;
import com.zaju.datamanager.repository.OperationHistoryRepository;

@Service
public class OperationHistoryService {
	
	private OperationHistoryRepository operationHistoryRepository;
	
	public OperationHistoryService(OperationHistoryRepository operationHistoryRepository) {
		this.operationHistoryRepository = operationHistoryRepository;
	}

	public OperationHistory save(OperationHistory operationHistory) {
		return operationHistoryRepository.save(operationHistory);
	}

}
