package com.licenta.dao;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.bson.Document;
import org.codehaus.jackson.map.ObjectMapper;

import com.licenta.model.User;
import com.licenta.utils.DBUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


/**
* Class for CRUD operations for the User object
* @author mirela
 *
*/
public class UserDao {

	private MongoDatabase mongoDB;
	
	@PostConstruct
	public void init() {
		mongoDB = DBUtils.getDB();
	}
	
	/**
	 * Get all users
	 * 
	 * @return
	 * 		List with all users
	 */
	public List<User> getAllUsers() {
		return null;
	}
	
	/**
	 * Get a user by it's username and password
	 * @param username
	 * @param password
	 * @return	the user if the credentials are valid
	 */
	public User getUserByUsername(String username, String password) {
		ObjectMapper objMapper = new ObjectMapper();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("username", username);
		whereQuery.put("password", password);
		Document user =  getUserCollection().find(whereQuery).first();
		if (user != null) {
			try {
				return objMapper.readValue(user.toJson(), User.class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Save a user in the database
	 * @param 
	 * 		The user to be saved
	 * @return	the new user
	 */
	public User saveUser(User user) {
		ObjectMapper objMapper = new ObjectMapper();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("username", user.getUsername());
		Document existingUser = getUserCollection().find(whereQuery).first();
		
		// if a user with this user name doesn't exists
		if (existingUser == null) {
			Document newUser = new Document();
			newUser.put("username", user.getUsername());
			newUser.put("password", user.getPassword());
			getUserCollection().insertOne(newUser);
			try {
				return objMapper.readValue(newUser.toJson(), User.class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Update a user
	 * @param user
	 * 		User to be updated
	 * @return
	 */
	public void updateUser(User user) {
		
	}
	
	/**
	 * Delete a user
	 * @param id
	 * 		id of the user to be deleted
	 */
	public void deleteUser(String id) {
	}
	
	/**
	 * Find the User collection
	 */
	public MongoCollection<Document> getUserCollection() {
		return mongoDB.getCollection("utilizatori");
	}

	public MongoDatabase getMongoDB() {
		return mongoDB;
	}

	public void setMongoDB(MongoDatabase mongoDB) {
		this.mongoDB = mongoDB;
	}
	
}
