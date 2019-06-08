package com.gw.xact.common.view;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Request<T> {
	@JsonProperty(value = "data")
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
