package com.audio.DAO;

import java.util.Map;

import com.audio.VO.userVO;

public interface userDAO {
	public void Join(userVO vo) throws Exception;
	
	public userVO Login(userVO vo) throws Exception;
	
	public void setKakaoConnection(Map<String, Object> paramMap) throws Exception;

	public Map<String, Object> kakaoConnectionCheck(Map<String, Object> paramMap) throws Exception;
	
	public Map<String, Object> naverConnectionCheck(Map<String, Object> apiJson) throws Exception;
	
	public void setnaverConnection(Map<String, Object> paramMap) throws Exception;
	
	
	public userVO googleConnectionCheck(userVO vo) throws Exception;
	
	public void setgoogleConnection(userVO vo) throws Exception;
	
	public int firstPayUpdate(userVO vo) throws Exception;
	
	

}
