package com.zaju.datamanager.neuralnetwork.service;

import java.util.Date;
import java.util.Locale;

import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import com.zaju.datamanager.domain.LearningProcess;
import com.zaju.datamanager.domain.MultilayerPerceptronDBFile;
import com.zaju.datamanager.neuralnetwork.consts.NeuralNetworkConsts;
import com.zaju.datamanager.repository.LearningProcessRepository;
import com.zaju.datamanager.repository.MultilayerPerceptronDBFileRepository;

@Service
public class MultilayerPerceptronLearningService {
	
	private MultilayerPerceptronDBFileRepository multilayerPerceptronDBFileRepository;
	private LearningProcessRepository learningProcessRepository;
	
	public MultilayerPerceptronLearningService(MultilayerPerceptronDBFileRepository multilayerPerceptronDBFileRepository, LearningProcessRepository learningProcessRepository) {
		this.multilayerPerceptronDBFileRepository = multilayerPerceptronDBFileRepository;
		this.learningProcessRepository = learningProcessRepository;
	}
	
	@Async
	public void learnInNewThread(LearningProcess learningProcess, MultiLayerPerceptron multilayerPerceptron, BackPropagation learningRule, DataSet learningDataSet) {
		
		learningProcess.setLearningStatus(NeuralNetworkConsts.LEARNING_PROCESS_STATUS_STARTED);
		learningProcess.setStartTime(new Date());
		learningProcessRepository.save(learningProcess);
		
		multilayerPerceptron.learnInNewThread(learningDataSet);
				
		String networkErrorChanges = getNetworkErrorChanges(multilayerPerceptron, learningRule);
		
		learningProcess.setFinishTime(new Date());
		learningProcess.setLearningStatus(NeuralNetworkConsts.LEARNING_PROCESS_STATUS_FINISHED);
		learningProcess.setMaxGainedError(learningRule.getTotalNetworkError());
		learningProcess.setIterationsCount(learningRule.getCurrentIteration());
		learningProcess.setNetworkErrorChanges(networkErrorChanges);
		
		MultilayerPerceptronDBFile multilayerPerceptronDBFile = saveToDatabase(multilayerPerceptron);
		
		learningProcess.setMultilayerPerceptronDBFile(multilayerPerceptronDBFile);
		
		learningProcessRepository.save(learningProcess);
	}
	
	private String getNetworkErrorChanges(MultiLayerPerceptron multilayerPerceptron, BackPropagation learningRule) {
		StringBuilder sb = new StringBuilder();
		
		int currentIteration = 0;
		while (learningRule.isStopped() == false)
		{
			if(currentIteration == learningRule.getCurrentIteration()) continue;
			
			String currentIterationError = String.format(Locale.ROOT, "%.6f", multilayerPerceptron.getLearningRule().getPreviousEpochError());
			
			currentIteration = learningRule.getCurrentIteration();
			
			sb.append(currentIterationError);
			sb.append(";");
			
			System.out.println(multilayerPerceptron.getLearningRule().getCurrentIteration() + " " + multilayerPerceptron.getLearningRule().getPreviousEpochError() );
		}
		String totalNetworkError = String.format(Locale.ROOT, "%.6f", multilayerPerceptron.getLearningRule().getTotalNetworkError());
		
		sb.append(totalNetworkError);
		
		System.out.println(totalNetworkError);
		
		return sb.toString();
	}
	
	private MultilayerPerceptronDBFile saveToDatabase(MultiLayerPerceptron multilayerPerceptron) {
		byte[] data = SerializationUtils.serialize(multilayerPerceptron);
		
		MultilayerPerceptronDBFile multilayerPerceptronDBFile = new MultilayerPerceptronDBFile();
		
		multilayerPerceptronDBFile.setData(data);
		
		return multilayerPerceptronDBFileRepository.save(multilayerPerceptronDBFile);
	}
}
