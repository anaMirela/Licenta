package com.licenta.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.bson.Document;
import org.codehaus.jackson.map.ObjectMapper;

import com.licenta.model.BuildHistory;
import com.licenta.utils.DBUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class BuildHistoryDao {

	private MongoDatabase mongoDB;
	
	@PostConstruct
	public void init() {
		mongoDB = DBUtils.getDB();
	}
	
	/**
	 * Method called to get build history for a specific user
	 * @param username
	 * 			the username of the user
	 * @return	the list with build histories
	 */
	public List<BuildHistory> getBuildHistoryByUsername(String username) {
		List<BuildHistory> result = new ArrayList<BuildHistory>();
		ObjectMapper objMapper = new ObjectMapper();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("username", username);
		MongoCursor<Document> buildHistories = getBuildHistoryCollection().find(whereQuery).iterator();
		while (buildHistories.hasNext()) {
			String jsonItem = buildHistories.next().toJson();
			try {
				result.add(objMapper.readValue(jsonItem, BuildHistory.class));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * Method called to get build history for a specific deployment configuration
	 * @param username
	 * 			the name of the configuration
	 * @return	the list with build histories
	 */
	public List<BuildHistory> getBuildHistoryByName(String configurationName) {
		List<BuildHistory> result = new ArrayList<BuildHistory>();
		ObjectMapper objMapper = new ObjectMapper();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("configurationName", configurationName);
		MongoCursor<Document> buildHistories = getBuildHistoryCollection().find(whereQuery).iterator();
		while (buildHistories.hasNext()) {
			String jsonItem = buildHistories.next().toJson();
			try {
				result.add(objMapper.readValue(jsonItem, BuildHistory.class));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * Method called to save a new build history 
	 * @param buildHistory
	 * 		the build history to be saved
	 * @return the new build history
	 */
	public BuildHistory save(BuildHistory buildHistory) {
		ObjectMapper objMapper = new ObjectMapper();
		Document newBuildHistory = convertDeploymentConfiguration(buildHistory);
		try {
			getBuildHistoryCollection().insertOne(newBuildHistory);
			return objMapper.readValue(newBuildHistory.toJson(), BuildHistory.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method called for getting the number of documents by username and configuration name
	 * @param username
	 * 		The username
	 * @param configurationName
	 * 		The configuration name
	 * @return	the number of documents matching the criteria
	 */
	public int countByUsernameAndConfigName(String username, String configurationName) {
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("username", username);
		whereQuery.put("configurationName", configurationName);
		getBuildHistoryCollection().find(whereQuery);
		return (int) getBuildHistoryCollection().count(whereQuery);
	}
	
	/**
	 * Method called to delete a build history
	 * 
	 * @param buildHistory
	 * 		the build history to be deleted
	 */
	public void delete(BuildHistory buildHistory) {
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("configurationName", buildHistory.getConfigurationName());
		whereQuery.put("username", buildHistory.getUsername());
		getBuildHistoryCollection().findOneAndDelete(whereQuery);
	}
	
	/**
	 * Find the BuildHistory collection
	 */
	public MongoCollection<Document> getBuildHistoryCollection() {
		return mongoDB.getCollection("buildHistory");
	}
	
	/**
	 * Convert BuildHistory object to Document object
	 * @param 
	 * 		The build history to be converted
	 * @return	the document object
	 */
	public Document convertDeploymentConfiguration(BuildHistory buildHistory) {
		Document result = new Document();
		result.put("configurationName", buildHistory.getConfigurationName());
		result.put("username", buildHistory.getUsername());
		result.put("pathToBuild", buildHistory.getPathToBuild());
		result.put("buildDate", buildHistory.getBuildDate());
		result.put("success", buildHistory.getSuccess());
		result.put("log", buildHistory.getLog());
		result.put("count", buildHistory.getCount());
		return result;
	}
	
	public MongoDatabase getMongoDB() {
		return mongoDB;
	}

	public void setMongoDB(MongoDatabase mongoDB) {
		this.mongoDB = mongoDB;
	}

}
