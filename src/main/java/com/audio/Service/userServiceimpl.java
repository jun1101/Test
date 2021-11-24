package com.audio.Service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.audio.userDAO.userDAO;
import com.audio.userVO.userVO;

@Service
public class userServiceimpl implements userService{
	@Inject
	private userDAO dao;

	@Override
	public void Join(userVO vo) throws Exception {
		dao.Join(vo);
	}

}
