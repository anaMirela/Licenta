/*package com.licenta;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.DB;
import com.mongodb.MongoException;
import com.mongodb.MongoURI;

@Configuration
public class SpringConfig {

    
	@Bean
	public DB getDb() throws UnknownHostException, MongoException {
		
		MongoURI mongoURI = new MongoURI("mongodb://mirela:pass123@ds019470.mlab.com:19470/heroku_d9l2zq8t");

		//MongoURI mongoURI = new MongoURI(System.getenv("MONGOHQ_URL"));
        DB db = mongoURI.connectDB();
        db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());
    
        return db;
	}
}*/