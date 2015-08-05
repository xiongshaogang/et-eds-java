package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.BusinessOptionLog;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessDetailModel;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.req.BusinessLoginReq;
import com.edaisong.entity.domain.BusinessModifyModel;
import com.edaisong.entity.req.PagedBusinessReq;
import com.edaisong.entity.resp.BusinessLoginResp;



public interface IBusinessService {
	PagedResponse<BusinessModel> getBusinessList(PagedBusinessReq req);
	
	/**
	 * 商家登录
	 * @param req
	 * @return
	 */
	public BusinessLoginResp login(BusinessLoginReq req);
	BusinessDetailModel getBusinessDetailByID(int businessID);
	List<BusinessOptionLog> getOpLogByBusinessID(int businessID);
	int modifyBusiness(BusinessModifyModel detailModel);
	void addLoginLog(String phoneNo, String description, boolean isSuccess);
}
