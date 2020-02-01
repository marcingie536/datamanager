package com.zaju.datamanager.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name = "learningprocess")
public class LearningProcess {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "starttime")
	private Date startTime;

	@Column(name = "finishtime")
	private Date finishTime;

	@Column(name = "learningrate")
	private Double learningRate;

	@Column(name = "maxiterationscount")
	private Integer maxIterationsCount;

	@Column(name = "hiddenlayerscount")
	private Integer hiddenLayersCount;

	@Column(name = "hiddenlayersneuronscount")
	private String hiddenLayersNeuronsCount;

	@Column(name = "activatefunction")
	private String activateFunction;

	@Column(name = "maxdesirederror")
	private Double maxDesiredError;

	@Column(name = "maxgainederror")
	private Double maxGainedError;

	@Column(name = "learningstatus")
	private String learningStatus;

	@Column(name = "iterationscount")
	private Integer iterationsCount;

	@Column(name = "networkerrorchanges")
	private String networkErrorChanges;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "structureid")
	private MultilayerPerceptronDBFile multilayerPerceptronDBFile;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "neuralnetworkid")
	private NeuralNetwork neuralNetwork;

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

	public Double getLearningRate() {
		return learningRate;
	}

	public void setLearningRate(Double learningRate) {
		this.learningRate = learningRate;
	}

	public Integer getMaxIterationsCount() {
		return maxIterationsCount;
	}

	public void setMaxIterationsCount(Integer maxIterationsCount) {
		this.maxIterationsCount = maxIterationsCount;
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

	public String getActivateFunction() {
		return activateFunction;
	}

	public void setActivateFunction(String activateFunction) {
		this.activateFunction = activateFunction;
	}

	public Double getMaxDesiredError() {
		return maxDesiredError;
	}

	public void setMaxDesiredError(Double maxDesiredError) {
		this.maxDesiredError = maxDesiredError;
	}

	public Double getMaxGainedError() {
		return maxGainedError;
	}

	public void setMaxGainedError(Double maxGainedError) {
		this.maxGainedError = maxGainedError;
	}

	public String getLearningStatus() {
		return learningStatus;
	}

	public void setLearningStatus(String learningStatus) {
		this.learningStatus = learningStatus;
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

	public MultilayerPerceptronDBFile getMultilayerPerceptronDBFile() {
		return multilayerPerceptronDBFile;
	}

	public void setMultilayerPerceptronDBFile(MultilayerPerceptronDBFile multilayerPerceptronDBFile) {
		this.multilayerPerceptronDBFile = multilayerPerceptronDBFile;
	}

	public NeuralNetwork getNeuralNetwork() {
		return neuralNetwork;
	}

	public void setNeuralNetwork(NeuralNetwork neuralNetwork) {
		this.neuralNetwork = neuralNetwork;
	}
}
