package com.audio.Service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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
		
		return dao.board_view(bno);
	}

	@Override
	public void board_modify(boardVO vo) throws Exception {
		 dao.board_modify(vo);
		
	}

	@Override
	public void board_delete(int bno) throws Exception {
		dao.board_delete(bno);
		
	}

	@Override
	public int count() throws Exception {
		// TODO Auto-generated method stub
		return dao.count();
	}

	@Override
	public List<boardVO> listPage(int displayPost, int postNum) throws Exception {
		// TODO Auto-generated method stub
		return dao.listPage(displayPost, postNum);
	}
	
	@Override
	public List<boardVO> listPageSearch(
	  int displayPost, int postNum, String searchType, String keyword) throws Exception {
	 return  dao.listPageSearch(displayPost, postNum, searchType, keyword);
	}

	@Override
	public void board_Hit(int bno) throws Exception {
		// TODO Auto-generated method stub
		dao.boardHit(bno);
		
	}



}
