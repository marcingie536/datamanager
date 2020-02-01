package com.zaju.datamanager.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "operationhistory")
public class OperationHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "methodsignature")
	private String methodSignatue;
	
	@Column(name = "issuccess")
	private boolean isSuccess;
	
	@Column(name = "exceptionname")
	private String exceptionName;
	
	@Column(name = "exceptionmessage")
	private String exceptionMessage;
	
	private String params;
	@Column(name = "executiondate")
	
	private Date executionDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMethodSignatue() {
		return methodSignatue;
	}
	public void setMethodSignatue(String methodSignatue) {
		this.methodSignatue = methodSignatue;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getExceptionName() {
		return exceptionName;
	}
	public void setExceptionName(String exceptionName) {
		this.exceptionName = exceptionName;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public Date getExecutionDate() {
		return executionDate;
	}
	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}
}
