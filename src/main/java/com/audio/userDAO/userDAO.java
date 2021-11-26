package com.audio.userDAO;

import com.audio.userVO.userVO;

public interface userDAO {
	public void Join(userVO vo) throws Exception;
	
	public userVO Login(userVO vo) throws Exception;
}
