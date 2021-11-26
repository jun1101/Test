package com.audio.userDAO;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.audio.userVO.userVO;

@Repository
public class userDAOimpl implements userDAO{
	
	@Inject
	private SqlSession sql;
	
	private static String namespace = "userMapper";

	

	@Override
	public void Join(userVO vo) throws Exception {
		sql.insert(namespace + ".Join", vo);
	}



	@Override
	public userVO Login(userVO vo) throws Exception {
		return sql.selectOne(namespace+".Login", vo);
	}

}
