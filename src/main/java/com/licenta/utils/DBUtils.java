package com.licenta.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

/**
 * Class defining methods that are related to the database
 * @author mirela
 *
 */
public class DBUtils {

	@SuppressWarnings("resource")
	public static MongoDatabase getDB() {
		MongoCredential credential = MongoCredential.createScramSha1Credential("admin-mirela", "heroku_d9l2zq8t", "pass".toCharArray());
		List<MongoCredential> mongoCred = new ArrayList<>();
		mongoCred.add(credential);
		MongoClient client = new MongoClient(Arrays.asList(new ServerAddress("ds019470.mlab.com", 19470)), mongoCred);
        MongoClientOptions.builder().serverSelectionTimeout(2000).build();
		MongoDatabase mongoDB = client.getDatabase("heroku_d9l2zq8t");
		
		return mongoDB;
	}
}
