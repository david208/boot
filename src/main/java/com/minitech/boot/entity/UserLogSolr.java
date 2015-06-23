package com.minitech.boot.entity;

import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument//(solrCoreName="techproducts")
(solrCoreName="new_core")
public class UserLogSolr {

	@Id
	@Field
	private String id ;
	@Field
	private String remark;// 备注
	@Field
	private Long userId;// 用户
	@Field
	private String arg;// 参数
	@Field
	private String result;// 结果

	@Field
	private String username;// 用户名
	@Field
	private boolean ucFlag;// uc标志
	@Field
	@Indexed(copyTo="jsonString")
	private String systemCode;//
	@Field
	private Date logTime;
	@Field
	@Indexed(copyTo="jsonString")
	private String ip;
	@Field
	@Indexed(type="text_general")
	private List<String> jsonString;// json后字符串

	
	
	
	public List<String> getJsonString() {
		return jsonString;
	}

	public void setJsonString(List<String> jsonString) {
		this.jsonString = jsonString;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getArg() {
		return arg;
	}

	public void setArg(String arg) {
		this.arg = arg;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean getUcFlag() {
		return ucFlag;
	}

	public void setUcFlag(boolean ucFlag) {
		this.ucFlag = ucFlag;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	
	

}
