package com.audio.Service;

import java.util.List;

import com.audio.VO.boardVO;

public interface BoardService {
	public List<boardVO> list() throws Exception;
	
	public void board_write(boardVO vo) throws Exception;
	
	public boardVO board_view(int bno) throws Exception;
	
	public void board_modify(boardVO vo) throws Exception;
	
	public void board_delete(int bno) throws Exception;
	
	public int count () throws Exception;
	
	public List<boardVO> listPage(int displayPost, int postNum) throws Exception;
	
	public List<boardVO> listPageSearch(
			  int displayPost, int postNum, String searchType, String keyword) throws Exception;
	

	
	
	

}
