package com.zaju.datamanager.neuralnetwork.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.zaju.datamanager.domain.LearningProcess;
import com.zaju.datamanager.domain.MultilayerPerceptronDBFile;
import com.zaju.datamanager.domain.NeuralNetwork;
import com.zaju.datamanager.domain.TestingProcess;
import com.zaju.datamanager.neuralnetwork.consts.NeuralNetworkConsts;
import com.zaju.datamanager.repository.LearningProcessRepository;
import com.zaju.datamanager.repository.MultilayerPerceptronDBFileRepository;
import com.zaju.datamanager.repository.NeuralNetworkRepository;
import com.zaju.datamanager.repository.TestingProcessRepository;

@Service
public class MultilayerPerceptronService {
	
	private DataConvertService dataConverterService;
	
	private MultilayerPerceptronLearningService multilayerPerceptronLearningService;
	private MultilayerPerceptronTestingService multilayerPerceptronTestingService;
	
	private NeuralNetworkRepository neuralNetworkRepository;
	private LearningProcessRepository learningProcessRepository;
	private MultilayerPerceptronDBFileRepository dbFileRepository;
	private TestingProcessRepository testingProcessRepository;
	
	public MultilayerPerceptronService(DataConvertService dataConverterService, MultilayerPerceptronLearningService multilayerPerceptronLearningService, MultilayerPerceptronTestingService multilayerPerceptronTestingService,
			NeuralNetworkRepository neuralNetworkRepository, LearningProcessRepository learningProcessRepository, MultilayerPerceptronDBFileRepository dbFileRepository,
			TestingProcessRepository testingProcessRepository) {
		this.dataConverterService = dataConverterService;
		this.multilayerPerceptronLearningService = multilayerPerceptronLearningService;
		this.multilayerPerceptronTestingService = multilayerPerceptronTestingService;
		this.neuralNetworkRepository = neuralNetworkRepository;
		this.learningProcessRepository = learningProcessRepository;
		this.dbFileRepository = dbFileRepository;
		this.testingProcessRepository = testingProcessRepository;
	}
	
	public LearningProcess learnMultilayerPerceptron(MultipartFile learningFile, Long neuralNetworkId, int hiddenLayersCount,
			String hiddenLayersNeuronsCount, double learningRate, double maxDesiredError, int maxIterationsCount)
			throws IOException {
		Optional<NeuralNetwork> neuralNetworkOptional = neuralNetworkRepository.findById(neuralNetworkId);
		
		if( !neuralNetworkOptional.isPresent() )
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Neural network not found");
		
		NeuralNetwork selectedNeuralNetwork = neuralNetworkOptional.get();
		
		LearningProcess learningProcess = createLearningProcess(hiddenLayersCount, hiddenLayersNeuronsCount,
				learningRate, maxDesiredError, maxIterationsCount, selectedNeuralNetwork);
		
		learningProcessRepository.save(learningProcess);
				
		int inputSize = selectedNeuralNetwork.getInputSize();
		int outputSize = selectedNeuralNetwork.getOutputSize();
		
    	List<Integer> layers = createNetworkLayers(inputSize, outputSize, learningProcess.getHiddenLayersNeuronsCount());
		
    	//TODO ustawianie funkcji podanej z request
		MultiLayerPerceptron multilayerPerceptron = new MultiLayerPerceptron(layers, TransferFunctionType.SIGMOID);
		
		BackPropagation learningRule = prepareLearningRule(learningProcess, multilayerPerceptron);	
		
		DataSet learningDataSet = dataConverterService.createDataSetFromCsv(learningFile, inputSize, outputSize, " ");

		multilayerPerceptronLearningService.learnInNewThread(learningProcess, multilayerPerceptron, learningRule, learningDataSet);
		
		return learningProcess;
	}
		
	public TestingProcess testMultilayerPerceptron(MultipartFile learningFile, Long structureId, String description) throws IOException {
		
		Optional<MultilayerPerceptronDBFile> multilayerPerceptronDBFileOptional = dbFileRepository.findById(structureId);
		
		if( !multilayerPerceptronDBFileOptional.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Structure not found");
		
		MultiLayerPerceptron multiLayerPerceptron = deserializeMultilayerPerceptron(multilayerPerceptronDBFileOptional.get());
		
		DataSet testSet = dataConverterService.createDataSetFromCsv(learningFile, 120, 12, " ");
		
		TestingProcess testingProcess = multilayerPerceptronTestingService.testNeuralNetwork(multiLayerPerceptron, testSet);
		
		testingProcess.setDescription(description);
		testingProcess.setMultilayerPerceptronDBFile(multilayerPerceptronDBFileOptional.get());
		
		testingProcessRepository.save(testingProcess);
		
		return testingProcess;
	}

	private LearningProcess createLearningProcess(int hiddenLayersCount, String hiddenLayersNeuronsCount,
			double learningRate, double maxDesiredError, int maxIterationsCount, NeuralNetwork selectedNeuralNetwork) {
		LearningProcess learningProcess = new LearningProcess();
		learningProcess.setNeuralNetwork(selectedNeuralNetwork);
		learningProcess.setHiddenLayersCount(hiddenLayersCount);
		learningProcess.setHiddenLayersNeuronsCount(hiddenLayersNeuronsCount);
		learningProcess.setLearningRate(learningRate);
		learningProcess.setMaxDesiredError(maxDesiredError);
		learningProcess.setMaxIterationsCount(maxIterationsCount);
		learningProcess.setLearningStatus(NeuralNetworkConsts.LEARNING_PROCESS_STATUS_CREATED);
		return learningProcess;
	}
	
	private BackPropagation prepareLearningRule(LearningProcess learningProcess,
			MultiLayerPerceptron multilayerPerceptron) {
		BackPropagation learningRule = multilayerPerceptron.getLearningRule();
		learningRule.setLearningRate(learningProcess.getLearningRate());
		learningRule.setMaxError(learningProcess.getMaxDesiredError());
		learningRule.setMaxIterations(learningProcess.getMaxIterationsCount());
		return learningRule;
	}

	private List<Integer> createNetworkLayers(int inputSize, int outputSize, String hiddenLayersNeuronsCount) {
		List<Integer> layers = new ArrayList<Integer>();
    	layers.add(inputSize);
    	
    	for (String layerNeuronsCount : hiddenLayersNeuronsCount.split(";")) {
			Integer count = Integer.parseInt(layerNeuronsCount);
			
			layers.add(count);
		}
    	layers.add(outputSize);
		return layers;
	}
		
	private MultiLayerPerceptron deserializeMultilayerPerceptron(MultilayerPerceptronDBFile multilayerPerceptronDBFile) {		
		return (MultiLayerPerceptron) SerializationUtils.deserialize(multilayerPerceptronDBFile.getData());
	}
}
