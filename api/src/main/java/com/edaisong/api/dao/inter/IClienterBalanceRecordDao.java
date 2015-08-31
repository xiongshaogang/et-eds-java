package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.ClienterBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.ClienterBalanceRecordReq;

public interface IClienterBalanceRecordDao {
    int deleteByPrimaryKey(Long id);

    int insert(ClienterBalanceRecord record);

    int insertSelective(ClienterBalanceRecord record);

    ClienterBalanceRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClienterBalanceRecord record);

    int updateByPrimaryKey(ClienterBalanceRecord record);
    
    
    PagedResponse<ClienterBalanceRecord> query(ClienterBalanceRecordReq req) ;
    
  /**
   * 根据订单获取对象
   * @author CaoHeYang
   * @param id
   * @date 20150831
   * @return
   */
     ClienterBalanceRecord getByOrderId(long id);
}