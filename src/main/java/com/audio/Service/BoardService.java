package com.audio.Service;

import java.util.List;

import com.audio.VO.boardVO;

public interface BoardService {
	public List<boardVO> list() throws Exception;
	
	public void board_write(boardVO vo) throws Exception;
	
	public boardVO board_view(int bno) throws Exception;

}
