package com.edaisong.api.dal.dao.inter;

import java.util.List;

import com.edaisong.entity.BusinessBalanceRecord;


public interface IBusinessBalanceRecord {
	  public List<BusinessBalanceRecord> selectBusinessBalanceByID(int RecordType,String OperateTime);
	  public int addBusinessBalance(BusinessBalanceRecord record);
	  public int updateBusinessBalance(BusinessBalanceRecord record);
	  public int deleteBusinessBalance(int id);
}
