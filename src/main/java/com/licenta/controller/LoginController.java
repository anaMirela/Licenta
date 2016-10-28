package com.licenta.controller;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.licenta.dao.UserDao;
import com.licenta.model.User;
import com.licenta.utils.CommonUtils;

@Controller
@RequestMapping(value = "/user")
public class LoginController {

	@Autowired
	private UserDao userDao;

 	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public User login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws UnknownHostException {

 		if (CommonUtils.isNullOrEmpty(user.getUsername()) || CommonUtils.isNullOrEmpty(user.getPassword())) {
			return null;
		}
		return userDao.getUserByUsername(user.getUsername(), user.getPassword());
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public User register(@RequestBody User user, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		if (CommonUtils.isNullOrEmpty(user.getUsername()) || CommonUtils.isNullOrEmpty(user.getPassword())) {
			return null;
		}
		return userDao.saveUser(user);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
