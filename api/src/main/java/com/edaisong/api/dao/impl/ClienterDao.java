package com.edaisong.api.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IClienterDao;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.StringUtils;
import com.edaisong.entity.Clienter;
import com.edaisong.entity.ClienterBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessClientersModel;
import com.edaisong.entity.domain.ClienterBindInfoModel;
import com.edaisong.entity.domain.ClienterModel;
import com.edaisong.entity.req.ClienterOptionReq;
import com.edaisong.entity.req.PagedClienterReq;
import com.edaisong.entity.req.PagedBusinessClientersReq;
import com.edaisong.entity.req.PagedClienterSearchReq;




@Repository
public class ClienterDao extends DaoBase implements IClienterDao {

	@Override
	public int updateByPrimaryKeySelective(Clienter record) {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", record.getId());
		paramMap.put("status", record.getStatus());	
		//其它的暂时没有写
		return getMasterSqlSessionUtil()
				.update("com.edaisong.api.dao.inter.IClienterDao.updateByPrimaryKeySelective",
						paramMap);
	}

	@Override
	public int updateByPrimaryKey(Clienter record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int updateMoneyById(ClienterOptionReq record) {
	    
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("clienterId", record.getClienterId());
		paramMap.put("optName", record.getOptName());			
		paramMap.put("remark", record.getRemark());	
		paramMap.put("rechargeAmount", record.getRechargeAmount().doubleValue());
		paramMap.put("recordType", 8);	
		paramMap.put("withwardId", 0);	
		
		return getMasterSqlSessionUtil()
				.update("com.edaisong.api.dao.inter.IClienterDao.updateMoneyById",
						paramMap);
	}	
	
	@Override
	public PagedResponse<ClienterModel> query(PagedClienterReq req) {
		PagedResponse<ClienterModel> model = getReadOnlySqlSessionUtil()
				.selectPageList(
						"com.edaisong.api.dao.inter.IClienterDao.query",
						req);
		return model;	
	}

	/**
	 * 获得商家下的所有骑士 ,包含骑士已接单的数量
	 * @author pengyi
	 * @date 20150901
	 */
	@Override
	public PagedResponse<BusinessClientersModel> getBusinessClienters(PagedBusinessClientersReq req) {
		Map<String, Object> map = new HashMap<String, Object>();				
		int PageSize = 15;
		int currentPage = req.getCurrentPage();
		map.put("workStatus", req.getWorkStatus());
		map.put("search", req.getSearch());
		map.put("businessId", req.getBusinessId());
		map.put("TotalRecord", 0);
		map.put("TotalPage", 0);
		map.put("PageSize", PageSize);
		map.put("currentPage", currentPage);
		List<BusinessClientersModel> list = getReadOnlySqlSessionUtil()
				.selectList("com.edaisong.api.dao.inter.IClienterDao.getBusinessClienters",
						map);
		
		PagedResponse<BusinessClientersModel> resp = new PagedResponse<BusinessClientersModel>();		
		resp.setResultList(list);
		resp.setPageSize(PageSize);
		resp.setCurrentPage(currentPage);
		resp.setTotalRecord(ParseHelper.ToInt(map.get("TotalRecord"), 0));
		resp.setTotalPage(ParseHelper.ToInt(map.get("TotalPage"), 0));
		return resp;
	}

    /**
     * 更新骑士余额
     * @param amount
     * @param clienterId
     *  @Date 20150831
	 * @param business
     */
	@Override
	public int updateCAccountBalance(Double amount, int clienterId) {
		Map<String, Object> parasMap = new HashMap();
		parasMap.put("Money", amount);
		parasMap.put("Id", clienterId);
		return getMasterSqlSessionUtil()
				.update("com.edaisong.api.dao.inter.IClienterDao.updateCAccountBalance",
						parasMap);
	}

	 /**
     * 更新骑士余额
     * @param amount
     * @param clienterId
     *  @Date 20150831
	 * @param business
     */
	@Override
	public int updateCAllowWithdrawPrice(Double amount, int clienterId) {
		Map<String, Object> parasMap = new HashMap();
		parasMap.put("Money", amount);
		parasMap.put("Id", clienterId);
		return getMasterSqlSessionUtil()
				.update("com.edaisong.api.dao.inter.IClienterDao.updateCAllowWithdrawPrice",
						parasMap);
	}
	/**
	 * 更新骑士余额和可提现金额
	 * @author CaoHeYang
	 * @param amount
	 * @param clienterId
	 * @date 20150831
	 * @return
	 */
	@Override
	public int updateCBalanceAndWithdraw(Double amount, int clienterId) {
		Map<String, Object> parasMap = new HashMap();
		parasMap.put("Money", amount);
		parasMap.put("Id", clienterId);
		return getMasterSqlSessionUtil()
				.update("com.edaisong.api.dao.inter.IClienterDao.updateCBalanceAndWithdraw",
						parasMap);
	}

	@Override
	public boolean updateClienterIsBind(int clienterId, int isBind) {
		Map<String, Object> parasMap = new HashMap();
		parasMap.put("clienterId", clienterId);
		parasMap.put("isBind", isBind);
		return getMasterSqlSessionUtil()
				.update("com.edaisong.api.dao.inter.IClienterDao.updateClienterIsBind",
						parasMap) > 0;
	}

	/**
	 * 获得骑士和绑定信息列表
	 * @author pengyi
	 * @date 20150901
	 */
	@Override
	public PagedResponse<ClienterBindInfoModel> getClienterBindInfoList(PagedClienterSearchReq req) {
		return getReadOnlySqlSessionUtil()
				.selectPageList("com.edaisong.api.dao.inter.IClienterDao.getClienterBindInfoList",
						req);
	}
}
