package com.zaju.datamanager.neuralnetwork.service;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.neuroph.core.data.DataSet;
import org.springframework.mock.web.MockMultipartFile;

import com.zaju.datamanager.exceptions.DataConvertException;

public class DataConvertServiceTest {
	
	private final DataConvertService service = new DataConvertService();
	
	private MockMultipartFile dataSetFile;

	@Before
	public void setUp() throws IOException {
		FileInputStream nnTestingDataSetInputStream = new FileInputStream("src/test/resources/nnTestingDataSet.txt");
		
		dataSetFile = new MockMultipartFile("test.csv", nnTestingDataSetInputStream);
	}

	@Test
	public void loadDataWithValidIOSize() throws IOException {
		DataSet dataSet = service.createDataSetFromCsv(dataSetFile, 120, 12, " ");
		
		assertEquals(dataSet.getInputSize(), 120);
		assertEquals(dataSet.getOutputSize(), 12);
	}
	
	@Test(expected = DataConvertException.class)
	public void loadDataWithInvalidSeparator() throws IOException {
		service.createDataSetFromCsv(dataSetFile, 120, 12, ":");
	}
	
	@Test(expected = DataConvertException.class)
	public void loadDataWithInvalidVectorInputSize() throws IOException {
		service.createDataSetFromCsv(dataSetFile, 125, 12, " ");
	}
	
	@Test(expected = DataConvertException.class)
	public void loadDataWithInvalidVectorOutputSize() throws IOException {
		service.createDataSetFromCsv(dataSetFile, 120, 15, " ");
	}
	
	
}