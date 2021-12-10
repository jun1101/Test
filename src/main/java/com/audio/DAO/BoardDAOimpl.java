package com.audio.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.audio.VO.BoardVO;
@Repository
public class BoardDAOimpl implements BoardDAO {

	
	@Inject
	private SqlSession sql;
	private static String namespace = "boardMapper";
	
	
	@Override
	public List<BoardVO> list() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList("boardMapper.list");
	}
	
	

}
