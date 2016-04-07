package com.licenta;
import java.net.UnknownHostException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

@Controller("/")
public class ExampleController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		return "index";
	}
	
	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public String doCheckout(ModelMap model) throws MongoException, UnknownHostException {
		
		Mongo mongo = new Mongo("ds019470.mlab.com", 19470);
		DB db = mongo.getDB("heroku_d9l2zq8t");
		db.authenticate("mirela", "pass123".toCharArray());
		return "page";
	}

}
