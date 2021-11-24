package com.audio.Controller;

import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.audio.Service.userService;
import com.audio.userVO.userVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Inject
	userService service;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
	
		return "home";
	}
	@RequestMapping(value = "/join.do", method = RequestMethod.GET)
	public String Join(Locale locale, Model model) {
		return "Join";
	}
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String Login(Locale locale, Model model) {
		return "Login";
	}
	@RequestMapping(value = "signup.do", method = RequestMethod.POST)
	public String postSignup(userVO vo) throws Exception{
		service.Join(vo);
		return "redirect:/home";
	}
	
	
	
}
