package com.licenta.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.licenta.dao.DeploymentConfigurationDao;
import com.licenta.model.DeploymentConfiguration;

@Controller
@RequestMapping(value = "/deployments")
public class DeploymentConfigurationController {

	@Autowired
	private DeploymentConfigurationDao deploymentConfigurationDao;

	@RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
	@ResponseBody
	public List<DeploymentConfiguration> getAllDeploymentsForUser(@PathVariable("username") String username) {
		return deploymentConfigurationDao.getDeploymentConfigurationsByUsername(username);
	}

	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	@ResponseBody
	public DeploymentConfiguration getDeploymentByName(@PathVariable("name") String name) {
		return deploymentConfigurationDao.findDeploymentConfigurationByName(name);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public DeploymentConfiguration addDeploymentConfiguration(
			@RequestBody DeploymentConfiguration deploymentConfiguration, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		return deploymentConfigurationDao.save(deploymentConfiguration);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public DeploymentConfiguration updateDeploymentConfiguration(
			@RequestBody DeploymentConfiguration deploymentConfiguration, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		return deploymentConfigurationDao.update(deploymentConfiguration);
	}
	
	@RequestMapping(value = "/delete/{name}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteDeploymentConfiguration(@PathVariable String name) {
		deploymentConfigurationDao.delete(name);
	}

	public DeploymentConfigurationDao getDeploymentConfigurationDao() {
		return deploymentConfigurationDao;
	}

	public void setDeploymentConfigurationDao(DeploymentConfigurationDao deploymentConfigurationDao) {
		this.deploymentConfigurationDao = deploymentConfigurationDao;
	}
}
