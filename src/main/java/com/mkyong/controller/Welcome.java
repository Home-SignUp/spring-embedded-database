package com.mkyong.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mkyong.dao.UserDao;
import com.mkyong.model.User;

@Controller
public class Welcome {

	private static final Logger logger = LoggerFactory.getLogger(Welcome.class);

	@Autowired
	UserDao userDao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(Model model) {
		logger.debug("mkyong");

		List<User> users = userDao.findAll(); //User user = userDao.findByName("mkyong");
		System.out.println(users);
		model.addAttribute("user", users);

		return "index";
	}
}