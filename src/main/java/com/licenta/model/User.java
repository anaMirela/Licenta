package com.licenta.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class for defining the mapping with the collection utilizatori
 * 
 * @author mirela
 *
 */
@Document(collection = "utilizatori")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	private String username;

	private String password;

	
	public User() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "User" + ", username=" + username + ", password=" + password + "]";
	}

}