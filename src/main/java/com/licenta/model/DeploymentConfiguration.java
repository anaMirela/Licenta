package com.licenta.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class for defining the mapping with the collection deployments
 * 
 * @author mirela
 *
 */
@Document(collection = "deployments")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeploymentConfiguration {
	
	private String name;
	private String username;
	private String versioningSystem;
	private String repositoryLocation;
	private String localPath;
	private String packaging; // jar or war
	private String lastBuildDate;
	private String lastSuccess;
	private String server;
	private String emailAddresses;
	private String description;
	
	public DeploymentConfiguration() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getVersioningSystem() {
		return versioningSystem;
	}

	public void setVersioningSystem(String versioningSystem) {
		this.versioningSystem = versioningSystem;
	}

	public String getRepositoryLocation() {
		return repositoryLocation;
	}

	public void setRepositoryLocation(String repositoryLocation) {
		this.repositoryLocation = repositoryLocation;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public String getPackaging() {
		return packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}

	public String getLastBuildDate() {
		return lastBuildDate;
	}

	public void setLastBuildDate(String lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}

	public String getLastSuccess() {
		return lastSuccess;
	}

	public void setLastSuccess(String lastSuccess) {
		this.lastSuccess = lastSuccess;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getEmailAddresses() {
		return emailAddresses;
	}

	public void setEmailAddresses(String emailAddresses) {
		this.emailAddresses = emailAddresses;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "DeploymentConfiguration [name=" + name + ", username=" + username + ", repositoryLocation="
				+ repositoryLocation + "]";
	}

}
