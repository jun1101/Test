package com.audio.Controller;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	@Autowired
	BCryptPasswordEncoder passEncoder;
	
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
		String inputPass = vo.getMem_pass();
		String pass = passEncoder.encode(inputPass);
		vo.setMem_pass(pass);
		System.out.println(vo.getMem_pass());
		service.Join(vo);
		return "home";
	}
	@RequestMapping(value = "/signin.do", method = RequestMethod.POST)
	public String postLogin(userVO vo, HttpServletRequest req, RedirectAttributes rttr, HttpSession session) throws Exception{
		userVO login = service.Login(vo);
		boolean passMatch = passEncoder.matches(vo.getMem_pass(), login.getMem_pass());
		
		if(login != null&& passMatch) {
			session.setAttribute("member1", login);
			
			System.out.println(session.getAttribute("member1"));
			return "home";
		}else {
			rttr.addFlashAttribute("msg", false);
			System.out.println("222");
			return "Login";
		}
		
		
	}
	@RequestMapping(value = "/Logout.do", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {
		service.Logout(session);
		
		return "redirect:/main.do";
	}
	
	
}
