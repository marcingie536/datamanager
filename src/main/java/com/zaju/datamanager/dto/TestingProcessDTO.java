package com.zaju.datamanager.dto;

public class TestingProcessDTO {
	private Integer testingRecordsCount;
	private Integer correctAnswersCount;
	private Integer incorrectAnswersCount;
	private String description;
	
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
}
