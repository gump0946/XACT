package com.gw.xact.common.view;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponseWrapper<T> {
	private ReturnSource returnSource;
	private String errorCode;
	private String errorInfo;
	private String detailErrorInfo;
	private String suggestion;
	private T data;

	public ResponseWrapper() {
		setSuccessResult();
	}


	public ResponseWrapper<T> setSuccessResult() {
		this.returnSource = ReturnSource.SUCCESS;
		this.errorCode = "0000";
		this.errorInfo = "交易成功";
		this.detailErrorInfo = "";
		this.suggestion = "";
		return this;
	}

	public ResponseWrapper<T> setSuccessResult(T responseData) {
		setSuccessResult();
		this.data = responseData;
		return this;
	}


	public ResponseWrapper<T> setFailResult(String errcode, T responseData) {
		this.data = responseData;
		return this;
	}


	public ResponseWrapper<T> setUnknownResult() {
		this.returnSource = ReturnSource.UNKOWN;
		this.errorCode = "CICS";
		this.errorInfo = "交易异常，请查证交易是否成功";
		this.detailErrorInfo = "";
		this.suggestion = "发起查证交易，查询原交易是否成功并核对账务。若失败，酌情判断是否重发";
		return this;
	}

	public ReturnSource getReturnSource() {
		return this.returnSource;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public String getErrorInfo() {
		return this.errorInfo;
	}

	public String getDetailErrorInfo() {
		return this.detailErrorInfo;
	}

	public ResponseWrapper<T> setDetailErrorInfo(String detailErrorInfo) {
		this.detailErrorInfo = detailErrorInfo;
		return this;
	}

	public ResponseWrapper<T> setSuggestion(String suggestion) {
		this.suggestion = suggestion;
		return this;
	}

	public String getSuggestion() {
		return this.suggestion;
	}

	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
