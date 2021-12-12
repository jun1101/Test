package com.audio.DAO;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.audio.VO.PayVO;
import com.audio.VO.userVO;
@Repository
public class PayDAOimpl implements PayDAO {
	
	@Inject
	private SqlSession sql;
	
	


	@Override
	public void insertPay(PayVO vo) throws Exception {
		sql.insert("payMapper.insertPay",vo);
		
		
	}

	@Override
	public int firstPayUpdate(userVO vo) throws Exception {
		// TODO Auto-generated method stub
		return sql.update("userMapper.firstPayUpdate",vo);
	}

	@Override
	public int payUpdate(userVO vo) throws Exception {
		// TODO Auto-generated method stub
		return sql.update("userMapper.payUpdate", vo);
	}

}
