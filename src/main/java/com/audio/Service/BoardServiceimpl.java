package com.audio.Service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.audio.DAO.BoardDAO;
import com.audio.VO.boardVO;
@Service
public class BoardServiceimpl implements BoardService{
	
	@Inject
	private BoardDAO dao;

	@Override
	public List<boardVO> list() throws Exception {
		// TODO Auto-generated method stub
		return dao.list();
	}

	@Override
	public void board_write(boardVO vo) throws Exception {
		dao.board_write(vo);
	}

	@Override
	public boardVO board_view(int bno) throws Exception {
		// TODO Auto-generated method stub
		return dao.board_view(bno);
	}



}
