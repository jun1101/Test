package com.audio.Service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.audio.DAO.PayDAO;
import com.audio.VO.PayVO;
import com.audio.VO.userVO;

@Service
public class PayServiceimpl implements PayService{
	
	@Inject
	PayDAO dao;

	@Override
	public void insertPay(PayVO vo) throws Exception {
		dao.insertPay(vo);
	}

	@Override
	public int firstPayUpdate(userVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.firstPayUpdate(vo);
	}

	@Override
	public int payUpdate(userVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.payUpdate(vo);
	}

}
