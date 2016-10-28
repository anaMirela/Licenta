package com.licenta.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.bson.Document;
import org.codehaus.jackson.map.ObjectMapper;

import com.licenta.model.DeploymentConfiguration;
import com.licenta.utils.DBUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**
* Class for CRUD operations for the DeploymentConfiguration objects
* @author mirela
 *
*/
public class DeploymentConfigurationDao {

	private MongoDatabase mongoDB;
	
	@PostConstruct
	public void init() {
		mongoDB = DBUtils.getDB();
	}

	/**
	 * Method used for retrieving a deployment configuration by it's name 
	 * @param name
	 * 		The name for the deployment configuration
	 * @return	the deployment configuration
	 */
	public DeploymentConfiguration findDeploymentConfigurationByName(String name) {
		ObjectMapper objMapper = new ObjectMapper();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("name", name);
		Document deploymentConfiguration = getDeploymentConfigurationCollection().find(whereQuery).first();
		if (deploymentConfiguration != null) {
			try {
				return objMapper.readValue(deploymentConfiguration.toJson(), DeploymentConfiguration.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Method used for retrieving all deployment configurations for an user
	 * @param username
	 * 		The username
	 * @return	the list of deployment configurations
	 */
	public List<DeploymentConfiguration> getDeploymentConfigurationsByUsername(String username) {
		List<DeploymentConfiguration> result = new ArrayList<DeploymentConfiguration>();
		ObjectMapper objMapper = new ObjectMapper();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("username", username);
		MongoCursor<Document> deploymentConfigurations = getDeploymentConfigurationCollection().find(whereQuery).iterator();
		while (deploymentConfigurations.hasNext()) {
			String jsonItem = deploymentConfigurations.next().toJson();
			try {
				result.add(objMapper.readValue(jsonItem, DeploymentConfiguration.class));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * Method used for saving a new deployment configuration
	 * @param deploymentConfiguration
	 * 		The deployment configuration to be saved
	 * @return	the new deployment configuration
	 */
	public DeploymentConfiguration save(DeploymentConfiguration deploymentConfiguration) {
		ObjectMapper objMapper = new ObjectMapper();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("name", deploymentConfiguration.getName());
		Document existingDeployment = getDeploymentConfigurationCollection().find(whereQuery).first();
		if (existingDeployment == null) {
			Document newDeploymentConfig = convertDeploymentConfiguration(deploymentConfiguration);
			getDeploymentConfigurationCollection().insertOne(newDeploymentConfig);
			try {
				return objMapper.readValue(newDeploymentConfig.toJson(), DeploymentConfiguration.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Method used for updating a deployment configuration
	 * @param deploymentConfiguration
	 * 		The deployment configuration to be updated
	 * @return	the updated deployment configuration
	 */
	public DeploymentConfiguration update(DeploymentConfiguration deploymentConfiguration) {
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("name", deploymentConfiguration.getName());
		getDeploymentConfigurationCollection().replaceOne(whereQuery, convertDeploymentConfiguration(deploymentConfiguration));
		return deploymentConfiguration;
	}

	/**
	 * Method used for deleting a deployment configuration
	 * @param name
	 * 		The name of the deployment configuration to be deleted
	 */
	public void delete(String name) {
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("name", name);
		Document existingDeployment = getDeploymentConfigurationCollection().find(whereQuery).first();
		if (existingDeployment != null) {
			getDeploymentConfigurationCollection().deleteOne(existingDeployment);
		}
	}
	
	/**
	 * Find the DeploymentConfiguration collection
	 */
	public MongoCollection<Document> getDeploymentConfigurationCollection() {
		return mongoDB.getCollection("deployments");
	}
	
	/**
	 * Convert DeploymentConfiguration object to Document object
	 * @param deploymentConfiguration
	 * 		The deployment configuration to be converted
	 * @return	the document object
	 */
	public Document convertDeploymentConfiguration(DeploymentConfiguration deploymentConfiguration) {
		Document result = new Document();
		result.put("name", deploymentConfiguration.getName());
		result.put("username", deploymentConfiguration.getUsername());
		result.put("versioningSystem", deploymentConfiguration.getVersioningSystem());
		result.put("repositoryLocation", deploymentConfiguration.getRepositoryLocation());
		result.put("localPath", deploymentConfiguration.getLocalPath());
		result.put("packaging", deploymentConfiguration.getPackaging());
		result.put("lastBuildDate", deploymentConfiguration.getLastBuildDate());
		result.put("lastSuccess", deploymentConfiguration.getLastSuccess());
		result.put("server", deploymentConfiguration.getServer());
		result.put("emailAddresses", deploymentConfiguration.getEmailAddresses());
		result.put("description", deploymentConfiguration.getDescription());
		return result;
	}
	
	public MongoDatabase getMongoDB() {
		return mongoDB;
	}

	public void setMongoDB(MongoDatabase mongoDB) {
		this.mongoDB = mongoDB;
	}
	
	
}
