package com.audio.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.audio.Service.PayService;
import com.audio.Service.userService;
import com.audio.VO.userVO;
import com.audio.naver.NaverLoginBO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.util.Utils;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Inject
	userService service;
	PayService payservice;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	BCryptPasswordEncoder passEncoder;
	@Inject
	NaverLoginBO naverloginbo;
	private String apiResult = null;
	private IamportClient iamport;
	public HomeController() {
		//rest api , api secret ������ ����
		this.iamport = new IamportClient("4600918309383969","781a0ada65d5278b8abcc14a90eaf80508335ba02c984b140d61a3f7dd67baa8fe042d092847b1f2");
	}
	
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
	
		return "home";
	}
	@RequestMapping(value = "/pay.do", method = RequestMethod.GET)
	public String pay(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
	
		return "Pay";
	}
	@RequestMapping(value = "/join.do", method = RequestMethod.GET)
	public String Join(Locale locale, Model model) {
		return "Join";
	}
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String Login(HttpSession session, Model model) {
		String naverAuthUrl = naverloginbo.getAuthorizationUrl(session);
		//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		//redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
		System.out.println("네이버:" + naverAuthUrl);
		//네이버
		model.addAttribute("url", naverAuthUrl);
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
	
	
	
	@ResponseBody
	@RequestMapping(value="/kakaoLoginPro.do", method=RequestMethod.POST)
	public Map<String, Object> kakaoLoginPro(@RequestParam Map<String,Object> paramMap,HttpSession session) throws SQLException, Exception {
		System.out.println("paramMap:" + paramMap);
		Map <String, Object> resultMap = new HashMap<String, Object>();
		
		Map<String, Object> kakaoConnectionCheck = service.kakaoConnectionCheck(paramMap);
		if(kakaoConnectionCheck == null) { //일치하는 이메일 없으면 가입
			resultMap.put("JavaData", "register");
		}
		else {
			Map<String, Object> loginCheck = kakaoConnectionCheck;
			session.setAttribute("member1", loginCheck);
			resultMap.put("JavaData", "YES");
		}
		System.out.println("resultMap:" + resultMap);
		
		return resultMap;
	}
	@RequestMapping(value="/setSnsInfo.do", method=RequestMethod.POST)
	public String setKakaoInfo(Model model,HttpSession session,@RequestParam Map<String,Object> paramMap) throws Exception {
		System.out.println("setKakaoInfo");	
		System.out.println("param ==>"+paramMap);
		service.setKakaoConnection(paramMap);
		Map<String, Object> kakaoConnectionCheck = service.kakaoConnectionCheck(paramMap);
		Map<String, Object> loginCheck = kakaoConnectionCheck;
		model.addAttribute("mem_id",loginCheck.get("mem_id"));
		model.addAttribute("password",loginCheck.get("id"));
		model.addAttribute("flag",loginCheck.get("flag"));
		return "home";
	}
	
//	@SuppressWarnings("unchecked")
//	@RequestMapping(value="/userNaverLoginPro",  method = {RequestMethod.GET,RequestMethod.POST})
//	public String userNaverLoginPro(Model model,@RequestParam Map<String,Object> paramMap, @RequestParam String code, @RequestParam String state,HttpSession session) throws SQLException, Exception {
//		System.out.println("paramMap:" + paramMap);
//		Map <String, Object> resultMap = new HashMap<String, Object>();
//
//		OAuth2AccessToken oauthToken;
//		oauthToken = naverloginbo.getAccessToken(session, code, state);
//		//로그인 사용자 정보를 읽어온다.
//		String apiResult = naverloginbo.getUserProfile(oauthToken);
//		System.out.println("apiResult =>"+apiResult);
//		ObjectMapper objectMapper =new ObjectMapper();
//		Map<String, Object> apiJson = (Map<String, Object>) objectMapper.readValue(apiResult, Map.class).get("response");
//		
//		Map<String, Object> naverConnectionCheck = service.naverConnectionCheck(apiJson);
//		
//		if(naverConnectionCheck == null) { //일치하는 이메일 없으면 가입
//		
//			model.addAttribute("mem_id",apiJson.get("email"));
//			model.addAttribute("mem_naver",apiJson.get("id"));
//			service.setnaverConnection(apiJson);
////			Map<String, Object> loginCheck = service.userNaverLoginPro(apiJson);
////			session.setAttribute("userInfo", loginCheck);
//			
//		
//			return "home";
//		}else if(naverConnectionCheck.get("NAVERLOGIN") == null && naverConnectionCheck.get("EMAIL") != null) { //이메일 가입 되어있고 네이버 연동 안되어 있을시
//			service.setnaverConnection(apiJson);
//////			Map<String, Object> loginCheck = service.userNaverLoginPro(apiJson);
////			session.setAttribute("userInfo", loginCheck);
////		}else { //모두 연동 되어있을시
////			Map<String, Object> loginCheck = service.userNaverLoginPro(apiJson);
////			session.setAttribute("userInfo", loginCheck);
////		}
//
//		return "redirect:/main.do";
//	}
	@RequestMapping(value = "/userNaverLoginPro.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session) throws IOException, ParseException {
		System.out.println("여기는 callback");
		OAuth2AccessToken oauthToken;
		oauthToken = naverloginbo.getAccessToken(session, code, state);
		apiResult = naverloginbo.getUserProfile(oauthToken);
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		JSONObject response_obj = (JSONObject)jsonObj.get("response");
		//response의 nickname값 파싱
		String nickname = (String)response_obj.get("nickname");
		System.out.println(nickname);
		session.setAttribute("sessionId",nickname);
		//세션 생성
		model.addAttribute("userInfo", apiResult);
		return "home";

	}
	
//	@RequestMapping(value ="/googleLoginPro.do",method = { RequestMethod.GET, RequestMethod.POST } )
//		public String googleLogin(Locale locale, Model model, HttpServletRequest req, HttpSession session) throws IOException, ParseException, Exception{
//		System.out.println("여기탔음"+req.getParameter("id_token") + req.getParameter("google_email"));
//		String name = req.getParameter("id_token");
//		session.setAttribute("sessionId", name);
//		model.addAttribute("userInfo", name);
//		
//		return "home";
//	}
//	@RequestMapping(value ="/googleLoginPro.do",method = { RequestMethod.GET, RequestMethod.POST } )
//	public String googleLoginPro(userVO vo, HttpSession session,  RedirectAttributes rttr) throws Exception{
//		userVO returnvo = service.googleConnectionCheck(vo);
//		
//		return "home";
//	}
	@ResponseBody
	@RequestMapping(value="/googleLoginPro.do", method=RequestMethod.POST)
	public Map<String, Object> googleLogin(/* UserVO vo, */String idtoken,HttpSession session) throws Exception {
		/*
		 * System.out.println("email: " + vo.getEmail());
		 * System.out.println("id: "+vo.getGooglelogin());
		 */
		
		HttpTransport transport = Utils.getDefaultTransport();
		JsonFactory jsonFactory = Utils.getDefaultJsonFactory();
		
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
				.setAudience(Collections.singletonList("830802709474-ucqn322s2tf8fbuda6sl4jat3on7vskn.apps.googleusercontent.com")).build();
		
		GoogleIdToken idToken = verifier.verify(idtoken);
		
		Map <String, Object> resultMap = new HashMap<String, Object>();
		
		if (idToken != null) {
			Payload payload = idToken.getPayload();
			String userId = payload.getSubject();
			String useremail = payload.getEmail(); 
			
			userVO vo = new userVO();
			vo.setMem_id(useremail);
			vo.setMem_google(userId);
			System.out.println("User ID: " + vo.getMem_id());
			System.out.println("User email: " + vo.getMem_google());
			userVO googleCheck = service.googleConnectionCheck(vo);
			
			if(googleCheck == null) {
				service.setgoogleConnection(vo);
				System.out.println("-- ���� ���� ���� ���� --");
				userVO afterJoinChk = new userVO();
				afterJoinChk = service.googleConnectionCheck(vo);
				if(afterJoinChk.getMem_google().equals(userId)) {
					session.setAttribute("sessionId",afterJoinChk.getMem_id());
				
					resultMap.put("JoinChk", "YES"); 
				}
				else {
					resultMap.put("JoinChk", "False");
				}
				
				return resultMap; 
			}
			else {
				session.setAttribute("sessionId",googleCheck.getMem_id());
				
				resultMap.put("JoinChk", "YES"); 
				return resultMap; 
			}
		}
		/*
		 * String chkEmail = vo.getEmail(); String chkGoogle = vo.getGooglelogin();
		 * resultMap.put("email", chkEmail); resultMap.put("googlelogin", chkGoogle);
		 * if(googleConnectionCheck == null) { userservice.setGoogleConnection(vo);
		 * resultMap.put("JoinChk", "No"); return resultMap; } else {
		 * session.setAttribute("email",googleConnectionCheck.getEmail());
		 * session.setAttribute("m_idx",googleConnectionCheck.getM_idx());
		 * session.setAttribute("userrole",googleConnectionCheck.getUserrole());
		 * session.setAttribute("payendday",googleConnectionCheck.getPayendday());
		 * session.setAttribute("freecount", googleConnectionCheck.getFreecount());
		 * resultMap.put("JoinChk", "YES"); return resultMap; }
		 */
		 
		return resultMap;
	}
	@RequestMapping(value = "/joinAfter.do", method=RequestMethod.POST)
	public String joinAfter(userVO vo, HttpSession session) throws Exception {
		userVO googleConnectionCheck = service.googleConnectionCheck(vo);
		if(vo.getMem_google().equals(googleConnectionCheck.getMem_google())) {
			session.setAttribute("sessionId",googleConnectionCheck.getMem_id());
			return "main.do";
		}
		else {
			return "Login.do";
		}
		
		
	}
	@ResponseBody
	@RequestMapping(value="/verifyIamport.do/{imp_uid}")
	public IamportResponse<Payment> paymentByImpUid(Model model, Locale locale,HttpSession session, @PathVariable(value="imp_uid") String imp_uid) throws IamportResponseException, IOException {
		
		
		return iamport.paymentByImpUid(imp_uid);
	}
	
	
	
	
}
