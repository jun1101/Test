package com.audio.Service;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.audio.DAO.userDAO;
import com.audio.VO.userVO;

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

	@Override
	public void setKakaoConnection(Map<String, Object> paramMap) throws Exception {
		dao.setKakaoConnection(paramMap);
	}

	@Override
	public Map<String, Object> kakaoConnectionCheck(Map<String, Object> paramMap) throws Exception {
		return dao.kakaoConnectionCheck(paramMap);
	}

	@Override
	public void setnaverConnection(Map<String, Object> apiJson) throws Exception {
		dao.setnaverConnection(apiJson);
		
	}

	@Override
	public Map<String, Object> naverConnectionCheck(Map<String, Object> paramMap) throws Exception {
		return dao.naverConnectionCheck(paramMap);
	}

	

	@Override
	public userVO googleConnectionCheck(userVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.googleConnectionCheck(vo);
	}

	@Override
	public void setgoogleConnection(userVO vo) throws Exception {
		dao.setgoogleConnection(vo);
	}
	
	@Override
	public int firstPayUpdate(userVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.firstPayUpdate(vo);
	}

	@Override
	public int idChk(userVO vo) throws Exception {
		int result = dao.idChk(vo);
		return result;
	}

	@Override
	public int loginChk(userVO vo) throws Exception {
		int result = dao.loginChk(vo);
		return result;
	}

	

	

}
