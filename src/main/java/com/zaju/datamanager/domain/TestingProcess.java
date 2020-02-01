package com.zaju.datamanager.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "testingprocess")
public class TestingProcess {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "testingrecordscount")
	private Integer testingRecordsCount;
	
	@Column(name = "correctanswerscount")
	private Integer correctAnswersCount;
	
	@Column(name = "incorrectanswerscount")
	private Integer incorrectAnswersCount;
	
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "structureid")
	private MultilayerPerceptronDBFile multilayerPerceptronDBFile;
	
	public TestingProcess() {
		
	}
	
	public TestingProcess(Integer testingRecordsCount, Integer correctAnswersCount, Integer incorrectAnswersCount) {
		this.testingRecordsCount = testingRecordsCount;
		this.correctAnswersCount = correctAnswersCount;
		this.incorrectAnswersCount = incorrectAnswersCount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTestingRecordsCount() {
		return testingRecordsCount;
	}

	public void setTestingRecordsCount(Integer testingRecordsCount) {
		this.testingRecordsCount = testingRecordsCount;
	}

	public Integer getCorrectAnswersCount() {
		return correctAnswersCount;
	}

	public void setCorrectAnswersCount(Integer correctAnswersCount) {
		this.correctAnswersCount = correctAnswersCount;
	}

	public Integer getIncorrectAnswersCount() {
		return incorrectAnswersCount;
	}

	public void setIncorrectAnswersCount(Integer incorrectAnswersCount) {
		this.incorrectAnswersCount = incorrectAnswersCount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MultilayerPerceptronDBFile getMultilayerPerceptronDBFile() {
		return multilayerPerceptronDBFile;
	}

	public void setMultilayerPerceptronDBFile(MultilayerPerceptronDBFile multilayerPerceptronDBFile) {
		this.multilayerPerceptronDBFile = multilayerPerceptronDBFile;
	}
}
