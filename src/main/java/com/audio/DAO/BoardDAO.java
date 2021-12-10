package com.audio.DAO;

import java.util.List;

import com.audio.VO.BoardVO;

public interface BoardDAO {
	public List<BoardVO> list() throws Exception;

}
