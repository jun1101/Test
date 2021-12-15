
package com.audio.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.audio.Service.BoardService;
import com.audio.Service.FileService;
import com.audio.Service.PayService;
import com.audio.Service.userService;
import com.audio.VO.FileVO;
import com.audio.VO.boardVO;
import com.audio.VO.userVO;
import com.audio.file.FTPUpLoader;
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
	@Inject
	PayService payservice;
	@Inject
	BoardService boardservice;
	
	@Inject
	FileService fileservice;
	
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
	public String home(Locale locale, Model model,HttpSession session) {
		logger.info("Welcome home! The client locale is {}.", locale);
//		System.out.println(Integer.parseInt(String.valueOf(session.getAttribute("num_id"))));
		return "home";
	}
	@RequestMapping(value = "/board.do", method = RequestMethod.GET)
	 public String getList(Locale locale, Model model,@RequestParam("num") int num) throws Exception {
		int count = boardservice.count();
		
		int postNum = 10;
		
		int pageNum = (int)Math.ceil((double)count/postNum);
		
		int displayPost = (num - 1) * postNum;
		
		int pageNum_cnt = 5;
		
		int endPageNum = (int)(Math.ceil((double)num/ (double)pageNum_cnt) * pageNum_cnt);
		
		int startPageNum = endPageNum - (pageNum_cnt -1);
		
		// 마지막 번호 재계산
		int endPageNum_tmp = (int)(Math.ceil((double)count / (double)pageNum_cnt));
		 
		if(endPageNum > endPageNum_tmp) {
		 endPageNum = endPageNum_tmp;
		}
		
		boolean prev = startPageNum == 1 ? false : true;
		boolean next = endPageNum * pageNum_cnt >= count ? false : true;
		
		
		
		List<boardVO> list = boardservice.listPage(displayPost, postNum);
		 model.addAttribute("list", list);
		 model.addAttribute("pageNum", pageNum);
		 
		// 시작 및 끝 번호
		 model.addAttribute("startPageNum", startPageNum);
		 model.addAttribute("endPageNum", endPageNum);

		 // 이전 및 다음 
		 model.addAttribute("prev", prev);
		 model.addAttribute("next", next);
		 
		 model.addAttribute("select", num);
		 
		 Page page = new Page();
		 
		 return "board/list";

		 
}
	@RequestMapping(value = "/boardWrite.do", method = RequestMethod.GET)
	public String boardWrite(Locale locale, Model model) {
		
	
		return "board/Write";
	}
	
	@RequestMapping(value = "/postWrite.do", method = RequestMethod.GET)
	public String postWrite(boardVO vo,  Model model ) throws Exception{
		boardservice.board_write(vo);
		
		
		return "redirect:/board.do?num=1";
		
	}
	@RequestMapping(value = "/view.do", method = RequestMethod.GET)
	public String boardView(@RequestParam("bno") int bno, Model model) throws Exception{
		boardVO vo =boardservice.board_view(bno);
		model.addAttribute("view", vo);
		
		return "board/view";
		
	}
	
	@RequestMapping(value = "/modify.do", method = RequestMethod.GET)
	public String getModify(Locale locale, Model model,@RequestParam("bno")int bno) throws Exception{
		boardVO vo = boardservice.board_view(bno);
		model.addAttribute("view", vo);
		
		
		return "board/modify";
		
	}
	
		
		
		
		
	
	
	@RequestMapping(value ="/board_modify.do", method = RequestMethod.POST)
	public String boardModify(boardVO vo, Model model) throws Exception{
		
		boardservice.board_modify(vo);
		
				
		
		return "redirect:/view.do?bno="+vo.getBno();
		
	}
	
	@RequestMapping(value = "/board_delete.do", method = RequestMethod.GET)
	public String getDelete(@RequestParam("bno") int bno) throws Exception {
		boardservice.board_delete(bno);
		
		return "redirect:/board.do?num=1";
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
		userVO vo2 = new userVO();
		String userrole = login.getUserrole();
		
		if(login != null&& passMatch) {
			session.setAttribute("member1", login);
			session.setAttribute("num_id", login.getNum_id());
			session.setAttribute("mem_day", login.getMem_day());
			session.setAttribute("userrole", userrole);
			System.out.println(userrole);
			
			
			
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
	@RequestMapping(value="/orderCompleteMobile.do", produces = "application/text; charset=utf8", method = RequestMethod.GET)
	public String orderCompleteMobile(@RequestParam(required = false) String imp_uid, @RequestParam(required = false) String merchant_uid, Model model, Locale locale
			, HttpSession session) throws IamportResponseException, IOException
	{
		
		IamportResponse<Payment> result = iamport.paymentByImpUid(imp_uid);
		
		// ���� ���ݰ� ��������� ���Ѵ�.
		if(result.getResponse().getAmount().compareTo(BigDecimal.valueOf(100)) == 0) {
			System.out.println("�������");
		}
		
		return "main.do";
	}
	@ResponseBody
	@RequestMapping(value="/paysuccess.do", method={ RequestMethod.GET, RequestMethod.POST })
	public void paysuccess(@RequestParam Map<String, Object> param, HttpServletRequest requet, HttpSession session) throws Exception{
		System.out.println((int)session.getAttribute("num_id"));
		int sessionidx= (int)(session.getAttribute("num_id"));
		Date sessionpay=(Date) session.getAttribute("mem_day");
		Date tday =new Date();
		Calendar cal = Calendar.getInstance();
		
		System.out.println("여기탔음ㅋ");
		int month = 1;
		System.out.println(param.get("name"));
		if(param.get("name").equals("1개월 이용권")) {
			month=1;
		}
		else if(param.get("name").equals("6개월 이용권")) {
			month=6;
		}
		else if(param.get("name").equals("1년 이용권")) {
			month=12;
		}
		
		userVO vo= new userVO();
		vo.setMonth(month);
		vo.setNum_id(sessionidx);
		
		int result = 0;
		if(sessionpay ==null|| sessionpay.before(tday)) {
			result = service.firstPayUpdate(vo);
			System.out.println(result);
			if(result ==1) {
				cal.setTime(tday);
				cal.add(Calendar.MONTH, month);
				session.setAttribute("mem_day", cal.getTime());
			}
			
		}
		else {
			result = payservice.payUpdate(vo);
			System.out.println(result);
			if(result ==1) {
				cal.setTime(sessionpay);
				cal.add(Calendar.MONTH, month);
				session.setAttribute("mem_day", cal.getTime());
				
			}
		}
		if(result ==0) {
			System.out.println("ㅇㅇㅇ");
		}
		System.out.println("끄으으으으으으읏");
	}
	
	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		 if (header.indexOf("MSIE") > -1) {
             return "MSIE";
        } else if (header.indexOf("Chrome") > -1) {
             return "Chrome";
        } else if (header.indexOf("Opera") > -1) {
             return "Opera";
        } else if (header.indexOf("Firefox") > -1) {
             return "Firefox";
        } else if (header.indexOf("Mozilla") > -1) {
             if (header.indexOf("Firefox") > -1) {
                  return "Firefox";
             }else{
                  return "MSIE";
             }
        }
        return "MSIE";
		
		
	}
	
	public void setDisposition(String filename, HttpServletRequest request,HttpServletResponse response) throws Exception {
        String browser = getBrowser(request);
        String dispositionPrefix = "attachment; filename=";
        String encodedFilename = null;

        if (browser.equals("MSIE")) {
            encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll(
            "\\+", "%20");
        } else if (browser.equals("Firefox")) {
            encodedFilename = "\""
            + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
        } else if (browser.equals("Opera")) {
            encodedFilename = "\""
            + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
        } else if (browser.equals("Chrome")) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < filename.length(); i++) {
            char c = filename.charAt(i);
            if (c > '~') {
                sb.append(URLEncoder.encode("" + c, "UTF-8"));
            } else {
                sb.append(c);
            }
        }
        encodedFilename = sb.toString();
        } else {
            throw new IOException("Not supported browser");
        }
        response.setHeader("Content-Disposition", dispositionPrefix
        + encodedFilename);
        if ("Opera".equals(browser)) {
            response.setContentType("application/octet-stream;charset=UTF-8");
        }
    }
	
	@ResponseBody
	@RequestMapping(value = "/uploadForm.do", method=RequestMethod.POST)
	public ResponseEntity<String[]> uploadFormAJAX(MultipartFile[] files, FileVO fvo,userVO uvo, HttpSession session) throws Exception{
		int len = files == null ? 0: files.length;
		String userrole = uvo.getUserrole();
		String idx = Integer.toString(fvo.getUploaduser());
		int m_idx = fvo.getUploaduser();
		
		try {
			String[] uploadedFiles = new String[len];
			for(int i=0; i<len; i++) {
				File savefile = new File(files[i].getOriginalFilename());
				FileInputStream fis = (FileInputStream) files[i].getInputStream();
				uploadedFiles[i] = files[i].getOriginalFilename(); // ���Ͽ�
				String filepath;
				String filesize;
				String filename;
				String originalfilename;
				if(userrole.equals("admin")) {
					filesize = FTPUpLoader.byteCalculation(files[i].getSize());
					filename = files[i].getOriginalFilename();
					String[] nameresult = filename.split("_",3);
					System.out.println(nameresult[0]+" / "+nameresult[1]+" / " +nameresult[2]);
					originalfilename = nameresult[2];
					int uidx= Integer.parseInt(nameresult[1]);
					fvo.setUploaduser(uidx);
					
					filepath = FTPUpLoader.FtpUpLoad(savefile, fis, nameresult[1], userrole);
					
					
					if(nameresult[0].equals("S")) {
						fvo.setFilesort("S");
					}
					else if (nameresult[0].equals("H")) {
						fvo.setFilesort("H");
					}
					else {
						fvo.setFilesort("N");
					}
				}
				else {
					filepath = FTPUpLoader.FtpUpLoad(savefile, fis, idx, userrole);
					filesize = FTPUpLoader.byteCalculation(files[i].getSize());
					filename = idx+'_'+files[i].getOriginalFilename();
					originalfilename = files[i].getOriginalFilename();
					fvo.setFilesort("U");
				}
				
				if(filepath == "error") {
					System.out.println("업로드 에러");
				}else {
					fvo.setFilepath(filepath);
					fvo.setFilesize(filesize);
					fvo.setFilename(filename);
					fvo.setOriginalfilename(originalfilename);
					fileservice.firstFileUpload(fvo);
					
					if(userrole.equals("admin")) {
						int doneResult = fileservice.fileUploadDone(fvo);
						if(doneResult == 0) {
							System.out.println("같은 정보인 파일중 U인 파일이 DB에 없음");
						}else {
							System.out.println("D로 상태 변경 완료");
						}
					}
				}
				
			}
			return new ResponseEntity<>(uploadedFiles, HttpStatus.CREATED);
			}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(new String[] { e.getMessage() },HttpStatus.BAD_REQUEST);
		}
		
	}
	@RequestMapping(value="/fileDownLoadForm.do")
	public String fileDownLoad(HttpSession session, Model model) throws Exception {
		
		int m_idx = (int) session.getAttribute("m_idx");
		System.out.println(m_idx);
		List<FileVO> result = fileservice.list(m_idx);
		model.addAttribute("list",result);
		
		return"fileDownLoad";
	}
	
	@RequestMapping(value="/fileDownLoad.do", produces="text/plain;charset=UTF-8")
    public void ftpDownload(HttpServletRequest request, HttpServletResponse response){
        int f_idx = Integer.parseInt(request.getParameter("f_idx"));
        FileVO fileVO = new FileVO();
        boolean result = false;
        try{
            fileVO = fileservice.getFileInfo(f_idx); 
        }catch(Exception e){
            e.printStackTrace();
        }
        
        try {
            setDisposition(fileVO.getFilename(), request, response);
            result = FTPUpLoader.download(fileVO, response);
            if(!result){
               System.out.println("다운로드 에러!!!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	
	
}

	
	
	

