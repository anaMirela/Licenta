package com.licenta.controller;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExampleController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "index";
	}
	
	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public String doCheckout(ModelMap model) {
		//model.addAttribute("message", "Spring 3 MVC Hello World");
		System.out.println(">>>>Check out method");

//		File workingDirectory;
//		try {
//			workingDirectory = File.createTempFile("git-test", "");
//
//			workingDirectory.delete();
//			workingDirectory.mkdirs();
//			Repository repo = FileRepositoryBuilder.create(new File(workingDirectory, ".git"));
//			repo.create();
//			Git git = new Git(repo);
//			git.checkout();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String remoteUrl = "https://github.com/github/testrepo.git";
		File localPath;
		try {
			localPath = File.createTempFile("~/TestGitRepository", "");
			
			localPath.delete();
			
			// then clone
			System.out.println("Cloning from " + remoteUrl + " to " + localPath);
			try (Git result = Git.cloneRepository()
			        .setURI(remoteUrl)
			        .setDirectory(localPath)
			        .call()) {
			    // Note: the call() returns an opened repository already which needs to be closed to avoid file handle leaks!
			System.out.println("Having repository: " + result.getRepository().getDirectory());
        } 
		
	} catch(Exception e) {
		e.printStackTrace();
	}
		return "page";
	}

}
