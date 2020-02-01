package com.zaju.datamanager.neuralnetwork.service;

import java.util.Arrays;

import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.springframework.stereotype.Service;

import com.zaju.datamanager.domain.TestingProcess;

@Service
public class MultilayerPerceptronTestingService {

	public TestingProcess testNeuralNetwork(MultiLayerPerceptron multilayerPerceptron, DataSet testSet) {
		int correctAnswersCount = 0;
		int incorrectAnswersCount = 0;
		int testingRecordsCount = testSet.getRows().size();
		
		for(DataSetRow dataRow : testSet.getRows()) {
			multilayerPerceptron.setInput(dataRow.getInput());
			multilayerPerceptron.calculate();
			double[] networkOutput = multilayerPerceptron.getOutput();

			int networkAnswer = getMaxVectorValueIndex(networkOutput);
			int desiredAnswer = getMaxVectorValueIndex(dataRow.getDesiredOutput());
			

			if(networkAnswer == desiredAnswer) {
				correctAnswersCount++;
			}
			else {
				incorrectAnswersCount++;
			}
			
			System.out.print(" WO: " + Arrays.toString(dataRow.getDesiredOutput()) );
			System.out.print(" Output: " + Arrays.toString(networkOutput) );
			System.out.println(networkAnswer + " " + desiredAnswer);
		}
		System.out.println("Correct answers: " + correctAnswersCount + " incorrect answers " + incorrectAnswersCount);
		
		return new TestingProcess(testingRecordsCount, correctAnswersCount, incorrectAnswersCount);
	}
	
	private int getMaxVectorValueIndex(double[] vector) {
		int index = 0;
		double maxValue = vector[0];
		
		for(int i = 0; i < vector.length; i++) {
			if( Double.compare(vector[i], maxValue) > 0 ) {
				index = i;
				maxValue = vector[i];
			}
		}
		return index;
	}
}
