package com.audio.Controller;

import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.audio.Controller.kakaoapi.KakaoApi;

@RestController
@RequestMapping("/api")
public class ApiController {
	@Value("${KAKAO.JS}")
	String kakaoApi;
	


	@RequestMapping(value="/getKakaoApi.do", method=RequestMethod.POST)
	public String getKakaoApi() throws SQLException, Exception{
		System.out.println("getKakaoApi");
		return kakaoApi;
	}
	
}
	
	
	
	
	
	
//	@Autowired
//	private KakaoApi kakao;
//	
//	@RequestMapping(value="/kakaologin.do", method = RequestMethod.GET)
//	public String kakaoLogin(@RequestParam("code") String code, HttpSession session) {
//		System.out.println("============================"+code);
//		String access_Token = kakao.getAccessToken(code);
//		HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
//		System.out.println("login Controller:" + userInfo);
//		
//		if (userInfo.get("email") !=null) {
//			session.setAttribute("userId", userInfo.get("email"));
//			session.setAttribute("access_Token", access_Token);
//			System.out.println("n12312312");
//		}
//		else {
//			System.out.println("기기기기기");
//		}
//	
//		
//		
//		return "home";
//	}


