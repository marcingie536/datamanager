package com.zaju.datamanager.repository;

import org.springframework.data.repository.CrudRepository;

import com.zaju.datamanager.domain.OperationHistory;

public interface OperationHistoryRepository extends CrudRepository<OperationHistory, Long>{

}
