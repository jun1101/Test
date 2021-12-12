package com.audio.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.audio.VO.boardVO;
@Repository
public class BoardDAOimpl implements BoardDAO {

	
	@Inject
	private SqlSession sql;
	private static String namespace = "boardMapper";
	
	
	@Override
	public List<boardVO> list() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList("boardMapper.list");
	}


	@Override
	public void board_write(boardVO vo) throws Exception {
		sql.insert("boardMapper.board_write", vo);
		
	}


	@Override
	public boardVO board_view(int bno) throws Exception {
		return sql.selectOne("boardMapper.board_view", bno);
	}
	
	
	
	

}
