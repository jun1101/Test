package com.audio.Service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.audio.DAO.BoardDAO;
import com.audio.VO.BoardVO;
@Service
public class BoardServiceimpl implements BoardService{
	
	@Inject
	private BoardDAO dao;

	@Override
	public List<BoardVO> list() throws Exception {
		// TODO Auto-generated method stub
		return dao.list();
	}



}
