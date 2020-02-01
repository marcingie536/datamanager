package com.zaju.datamanager.neuralnetwork.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DataConvertService {
	
	public DataSet createDataSetFromCsv(MultipartFile file, int inputSize, int outputSize, String separator) throws IOException {
		DataSet dataSet = new DataSet(inputSize, outputSize);

		InputStream inputStream = file.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

		String line;
		while ((line = bufferedReader.readLine()) != null) {
			DataSetRow dataSetRow = createDataSetRow(line, inputSize, outputSize, separator);

			dataSet.addRow(dataSetRow);
		}

		return dataSet;
	}

	private DataSetRow createDataSetRow(String line, int inputSize, int outputSize, String separator) {
		double[] inputVector = new double[inputSize];
		double[] desiredOutputVector = new double[outputSize];
		
		String[] splittedLine = line.split(separator);
		
		for(int i = 0; i < inputSize; i++) {
			inputVector[i] = Double.parseDouble(splittedLine[i]);
		}
		
		for(int i = (inputSize ); i < (inputSize + outputSize); i++) {
			desiredOutputVector[i - inputSize] = Double.parseDouble(splittedLine[i]);
		}
		
		return new DataSetRow(inputVector, desiredOutputVector);
	}

}
