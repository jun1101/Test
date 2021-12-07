package com.audio.Controller;

import javax.inject.Inject;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.audio.naver.NaverLoginBO;


@Controller
public class naverController {
	
	@Inject
	NaverLoginBO naverloginbo;
	
	
	
}
