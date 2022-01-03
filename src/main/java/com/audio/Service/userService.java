package com.audio.Service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.audio.VO.userVO;

public interface userService {
	
	public void Join(userVO vo) throws Exception;
	
	public userVO  Login(userVO vo) throws Exception;
	
	public void Logout(HttpSession session) throws Exception;

	public void setKakaoConnection(Map<String, Object> paramMap) throws Exception;

	public Map<String, Object> kakaoConnectionCheck(Map<String, Object> paramMap) throws Exception;

	public Map<String, Object> naverConnectionCheck(Map<String, Object> paramMap) throws Exception;
	
	

	public void setnaverConnection(Map<String, Object> apiJson) throws Exception;
	
	public userVO googleConnectionCheck(userVO vo) throws Exception;
	
	public void setgoogleConnection(userVO vo) throws Exception;
	
	public int firstPayUpdate(userVO vo) throws Exception;
	
	public int idChk(userVO vo) throws Exception;
	
	public int loginChk(userVO vo) throws Exception;
	
	
}
