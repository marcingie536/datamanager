package com.zaju.datamanager.dto;

public class NeuralNetworkDTO {
	private Long id;
	private String name;
	private String type;
	private int inputSize; 
	private Integer hiddenLayersCount;
	private String hiddenLayersNeuronsCount;
	private Double learningRate;
	private int outputSize;
	private String learningRule;
	private String activateFunction;
	private int maxNetwoksCount;
	private Long selectedStructureId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getInputSize() {
		return inputSize;
	}
	public void setInputSize(int inputSize) {
		this.inputSize = inputSize;
	}
	public Integer getHiddenLayersCount() {
		return hiddenLayersCount;
	}
	public void setHiddenLayersCount(Integer hiddenLayersCount) {
		this.hiddenLayersCount = hiddenLayersCount;
	}
	public String getHiddenLayersNeuronsCount() {
		return hiddenLayersNeuronsCount;
	}
	public void setHiddenLayersNeuronsCount(String hiddenLayersNeuronsCount) {
		this.hiddenLayersNeuronsCount = hiddenLayersNeuronsCount;
	}
	public Double getLearningRate() {
		return learningRate;
	}
	public void setLearningRate(Double learningRate) {
		this.learningRate = learningRate;
	}
	public int getOutputSize() {
		return outputSize;
	}
	public void setOutputSize(int outputSize) {
		this.outputSize = outputSize;
	}
	public String getLearningRule() {
		return learningRule;
	}
	public void setLearningRule(String learningRule) {
		this.learningRule = learningRule;
	}
	public String getActivateFunction() {
		return activateFunction;
	}
	public void setActivateFunction(String activateFunction) {
		this.activateFunction = activateFunction;
	}
	public int getMaxNetwoksCount() {
		return maxNetwoksCount;
	}
	public void setMaxNetwoksCount(int maxNetwoksCount) {
		this.maxNetwoksCount = maxNetwoksCount;
	}
	public Long getSelectedStructureId() {
		return selectedStructureId;
	}
	public void setSelectedStructureId(Long selectedStructureId) {
		this.selectedStructureId = selectedStructureId;
	}
	
}
