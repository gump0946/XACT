package com.gw.xact.common.view;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "xml")
public class RequestWrapper<T> extends Request<T> {

	private String uniqueId;
	private String serverId;
	private String extTransFlag = "JSON";
	private String customNo;
	private String operNo;
	private String reqSeqNo;
	private Date timestamp;

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getExtTransFlag() {
		return extTransFlag;
	}

	public void setExtTransFlag(String extTransFlag) {
		this.extTransFlag = extTransFlag;
	}

	public String getCustomNo() {
		return customNo;
	}

	public void setCustomNo(String customNo) {
		this.customNo = customNo;
	}

	public String getOperNo() {
		return operNo;
	}

	public void setOperNo(String operNo) {
		this.operNo = operNo;
	}

	public String getReqSeqNo() {
		return reqSeqNo;
	}

	public void setReqSeqNo(String reqSeqNo) {
		this.reqSeqNo = reqSeqNo;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}


}
