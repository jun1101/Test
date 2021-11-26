package com.audio.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

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

	@Override
	public userVO Login(userVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.Login(vo);
	}

	@Override
	public void Logout(HttpSession session) throws Exception {
		session.invalidate();
	}

}
