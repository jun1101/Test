package com.audio.DAO;

import java.util.HashMap;
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


	@Override
	public void board_modify(boardVO vo) throws Exception {
		 sql.update("boardMapper.board_update", vo);
		
	}


	@Override
	public void board_delete(int bno) throws Exception {
		sql.delete("boardMapper.board_delete", bno);
	}


	@Override
	public int count() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne("boardMapper.count");
	}


	@Override
	public List<boardVO> listPage(int displayPost, int postNum) throws Exception {
		HashMap<String, Integer> data = new HashMap<String, Integer>();
		
		data.put("displayPost", displayPost);
		data.put("postNum", postNum);
		
		
	
		// TODO Auto-generated method stub
		return sql.selectList("boardMapper.listPage", data);
	}


	@Override
	 public List<boardVO> listPageSearch(
			   int displayPost, int postNum, String searchType, String keyword) throws Exception {

			  HashMap<String, Object> data = new HashMap<String, Object>();
			  
			  data.put("displayPost", displayPost);
			  data.put("postNum", postNum);
			  
			  data.put("searchType", searchType);
			  data.put("keyword", keyword);
			  
			  return sql.selectList("boardMapper.listPageSearch", data);
			 }


	@Override
	public void boardCount(int bno) throws Exception {
		sql.update("boardMapper.count",bno);
		
	}


	@Override
	public void boardHit(int bno) throws Exception {
		// TODO Auto-generated method stub
		sql.update("boardMapper.boardHit", bno);
		
	}
	
	
	

}
