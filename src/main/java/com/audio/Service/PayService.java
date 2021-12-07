package com.audio.Service;

import com.audio.VO.PayVO;
import com.audio.VO.userVO;

public interface PayService {
	public void insertPay(PayVO vo) throws Exception;

	public int firstPayUpdate(userVO vo) throws Exception;
	
	public int payUpdate(userVO vo) throws Exception;	

}
