package com.audio.DAO;

import com.audio.VO.PayVO;
import com.audio.VO.userVO;

public interface PayDAO {
public void insertPay(PayVO vo) throws Exception;
	
	public int firstPayUpdate(userVO vo) throws Exception;
	
	public int payUpdate(userVO vo) throws Exception;
}
