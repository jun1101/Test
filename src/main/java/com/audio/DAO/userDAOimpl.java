package com.audio.DAO;

import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.audio.VO.userVO;

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



	@Override
	public void setKakaoConnection(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		sql.insert("userMapper.setKakaoConnection",paramMap);
		
	}



	@Override
	public Map<String, Object> kakaoConnectionCheck(Map<String, Object> paramMap) throws Exception {
		return sql.selectOne("userMapper.kakaoConnectionCheck",paramMap);
	
	}



	@Override
	public Map<String, Object> naverConnectionCheck(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne("userMapper.naverConnectionCheck",paramMap);
	}



	@Override
	public void setnaverConnection(Map<String, Object> apiJson) throws Exception {
		sql.insert("userMapper.setnaverConnection",apiJson);
		
	}



	



	@Override
	public userVO googleConnectionCheck(userVO vo) throws Exception {
		return sql.selectOne("userMapper.googleConnectionCheck",vo);
	}



	@Override
	public void setgoogleConnection(userVO vo) throws Exception {
		sql.insert("userMapper.setgoogleConnection", vo);
		
	}
	@Override
	public int firstPayUpdate(userVO vo) throws Exception {
		// TODO Auto-generated method stub
		return sql.update("userMapper.firstPayUpdate",vo);
	}



	@Override
	public int idChk(userVO vo) throws Exception {
		int result = sql.selectOne("userMapper.idChk", vo);
		return result;
	}



	@Override
	public int loginChk(userVO vo) throws Exception {
		int result = sql.selectOne("userMapper.loginChk", vo);
		return result;
	}

}
