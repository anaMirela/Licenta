package com.licenta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.licenta.dao.BuildHistoryDao;
import com.licenta.model.BuildHistory;

@Controller
@RequestMapping(value = "/history")
public class BuildHistoryController {

	@Autowired
	private BuildHistoryDao buildHistoryDao;
	
	@RequestMapping(value = "/configName/{name}", method = RequestMethod.GET)
	@ResponseBody
	public List<BuildHistory> getAllBuildHistory(@PathVariable("name") String configName) {
		return buildHistoryDao.getBuildHistoryByName(configName);
	}
	
	@RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
	@ResponseBody
	public List<BuildHistory> getAllBuildHistoryByUser(@PathVariable("username") String username) {
		return buildHistoryDao.getBuildHistoryByUsername(username);
	}
	
	public BuildHistoryDao getBuildHistoryDao() {
		return buildHistoryDao;
	}

	public void setBuildHistoryDao(BuildHistoryDao buildHistoryDao) {
		this.buildHistoryDao = buildHistoryDao;
	}
	
}
