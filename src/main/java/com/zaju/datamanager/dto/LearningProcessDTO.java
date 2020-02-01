package com.zaju.datamanager.dto;

import java.util.Date;

public class LearningProcessDTO {
	private Long id;
	private Date startTime;
	private Date finishTime;
	private double learningRate;
	private int maxIterationsCount;
	private int hiddenLayersCount;
	private String hiddenLayersNeuronsCount;
	private String activateFunction;
	private double maxDesiredError;
	private double maxGainedError;
	private String learningStatus;
	private Long structureId;
	private Integer iterationsCount;
	private String networkErrorChanges;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public double getLearningRate() {
		return learningRate;
	}
	public void setLearningRate(double learningRate) {
		this.learningRate = learningRate;
	}
	public int getMaxIterationsCount() {
		return maxIterationsCount;
	}
	public void setMaxIterationsCount(int maxIterationsCount) {
		this.maxIterationsCount = maxIterationsCount;
	}
	public int getHiddenLayersCount() {
		return hiddenLayersCount;
	}
	public void setHiddenLayersCount(int hiddenLayersCount) {
		this.hiddenLayersCount = hiddenLayersCount;
	}
	public String getHiddenLayersNeuronsCount() {
		return hiddenLayersNeuronsCount;
	}
	public void setHiddenLayersNeuronsCount(String hiddenLayersNeuronsCount) {
		this.hiddenLayersNeuronsCount = hiddenLayersNeuronsCount;
	}
	public String getActivateFunction() {
		return activateFunction;
	}
	public void setActivateFunction(String activateFunction) {
		this.activateFunction = activateFunction;
	}
	public double getMaxDesiredError() {
		return maxDesiredError;
	}
	public void setMaxDesiredError(double maxDesiredError) {
		this.maxDesiredError = maxDesiredError;
	}
	public double getMaxGainedError() {
		return maxGainedError;
	}
	public void setMaxGainedError(double maxGainedError) {
		this.maxGainedError = maxGainedError;
	}
	public String getLearningStatus() {
		return learningStatus;
	}
	public void setLearningStatus(String learningStatus) {
		this.learningStatus = learningStatus;
	}	
	public Long getStructureId() {
		return structureId;
	}
	public void setStructureId(Long structureId) {
		this.structureId = structureId;
	}
	public Integer getIterationsCount() {
		return iterationsCount;
	}
	public void setIterationsCount(Integer iterationsCount) {
		this.iterationsCount = iterationsCount;
	}
	public String getNetworkErrorChanges() {
		return networkErrorChanges;
	}
	public void setNetworkErrorChanges(String networkErrorChanges) {
		this.networkErrorChanges = networkErrorChanges;
	}

}
