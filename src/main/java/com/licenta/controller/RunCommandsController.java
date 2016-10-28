package com.licenta.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.licenta.model.DeploymentConfiguration;
import com.licenta.utils.CommandsUtils;

@Controller
@RequestMapping(value = "/run")
public class RunCommandsController {

	@Autowired
	CommandsUtils commandsUtils;
	
	@RequestMapping(value = "/build", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void build(@RequestBody DeploymentConfiguration deploymentConfiguration) {
		commandsUtils.checkoutAndBuild(deploymentConfiguration);
	}
	
	@RequestMapping(value = "/deploy", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void runDeploy(@RequestBody DeploymentConfiguration deploymentConfiguration) {
		commandsUtils.checkoutBuildAndRun(deploymentConfiguration);
	}
	
	@RequestMapping(value = "/download/{filename}", method = RequestMethod.GET)
	public void downloadWarFile(@PathVariable("filename") String filename, HttpServletRequest request, HttpServletResponse response) throws IOException {
			 System.out.println(">>>>>>>>>>>>>>>>>>>>>>filename" + filename);
			 String filenameParsed= filename.replace('$', '/');
			 System.out.println(">>>>>>>>>>>>>>>>>>>>>>filename" + filenameParsed);
			 File targetDir = new File(filenameParsed + "/target");
			 File downloadFile = null;
			 String[] files = targetDir.list();
			 
			 boolean foundFile = false;
			 for(String f : files) {
				 if (f.endsWith(".jar")) {
					 downloadFile = new File(targetDir + "/" + f);
					 foundFile = true;
					 break;
				 }
			 }
			if (!foundFile) {
				// set content attributes for the response
		        response.setContentType("application/octet-stream");
		        response.setContentLength(0);
		 
		        // set headers for the response
		        String headerKey = "Content-Disposition";
		        String headerValue = String.format("attachment; filename=\"%s\"", "empty-file");
		        response.setHeader(headerKey, headerValue);
				return;
			}
	        FileInputStream inputStream = new FileInputStream(downloadFile);
	 
	        // set content attributes for the response
	        response.setContentType("application/octet-stream");
	        response.setContentLength((int) downloadFile.length());
	 
	        // set headers for the response
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"",downloadFile.getName());
	        response.setHeader(headerKey, headerValue);
	 
	        // get output stream of the response
	        OutputStream outStream = response.getOutputStream();
	 
	        byte[] buffer = new byte[4096];
	        int bytesRead = -1;
	 
	        // write bytes read from the input stream into the output stream
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            outStream.write(buffer, 0, bytesRead);
	        }
	        inputStream.close();
	        outStream.close();
	 
	}
}
