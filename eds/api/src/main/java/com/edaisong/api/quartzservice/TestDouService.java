package com.edaisong.api.quartzservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.common.IJobDo;
import com.edaisong.api.dao.inter.ITestDao;

@Service
public class TestDouService  implements IJobDo{

	@Autowired
	ITestDao testDao;

	public void insert(String val) {
		// TODO Auto-generated method stub
		testDao.insert(val);
	}
	@Override
	public void run() {
		testDao.insert("1000");
		
	}

}
