package com.licenta.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class for defining the mapping with the collection buildHistory
 * 
 * @author mirela
 *
 */
@Document(collection = "buildHistory")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuildHistory {

	private String configurationName;
	private String username;
	private String pathToBuild;
	private String buildDate;
	private String success;
	private String log;
	private Integer count;
	
	public BuildHistory() {
		super();
	}
	
	public String getConfigurationName() {
		return configurationName;
	}


	public void setConfigurationName(String configurationName) {
		this.configurationName = configurationName;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPathToBuild() {
		return pathToBuild;
	}
	
	public void setPathToBuild(String pathToBuild) {
		this.pathToBuild = pathToBuild;
	}
	
	public String getBuildDate() {
		return buildDate;
	}
	
	public void setBuildDate(String buildDate) {
		this.buildDate = buildDate;
	}
	
	public String getSuccess() {
		return success;
	}
	
	public void setSuccess(String success) {
		this.success = success;
	}
	
	public String getLog() {
		return log;
	}
	
	public void setLog(String log) {
		this.log = log;
	}
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "BuildHistory [configurationName=" + configurationName + ", username=" + username + ", buildDate=" + buildDate + "]";
	}
	
}
