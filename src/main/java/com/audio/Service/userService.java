package com.audio.Service;

import javax.servlet.http.HttpSession;

import com.audio.userVO.userVO;

public interface userService {
	
	public void Join(userVO vo) throws Exception;
	
	public userVO  Login(userVO vo) throws Exception;
	
	public void Logout(HttpSession session) throws Exception;

}
