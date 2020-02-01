package com.zaju.datamanager.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="neuralnetwork")
public class NeuralNetwork {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String type;
	
	@Column(name="inputsize")
	private int inputSize; 
	
	@Column(name="hiddenlayerscount")
	private Integer hiddenLayersCount;
	
	@Column(name="hiddenlayersneuronscount")
	private String hiddenLayersNeuronsCount;
	
	@Column(name="learningrate")
	private Double learningRate;
	
	@Column(name="outputsize")
	private int outputSize;
	
	@Column(name="learningrule")
	private String learningRule;
	
	@Column(name="activatefunction")
	private String activateFunction;
	
	@Column(name="maxnetworkscount")
	private int maxNetwoksCount;
	
	@Column(name="selectedstructureid")
	private Long selectedStructureId;
	
	@Column(name="userid")
	private Long userId;
	
	@OneToMany
	private List<LearningProcess> learningProcesses;
	
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<LearningProcess> getLearningProcesses() {
		return learningProcesses;
	}
	public void setLearningProcesses(List<LearningProcess> learningProcesses) {
		this.learningProcesses = learningProcesses;
	}
}
