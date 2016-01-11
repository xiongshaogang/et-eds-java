package com.edaisong.api_http.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.api_http.entity.TestServiceReq;
import com.edaisong.api_http.entity.TestServiceResp;
import com.edaisong.api_http.service.inter.IHttpTestService;
import com.edaisong.core.enums.returnenums.HttpReturnRnums;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.domain.BusinessDetailModel;
import com.edaisong.entity.req.GetPushClienterIdsReq;
import com.edaisong.entity.resp.OrderStatisticsBResp;

@Service
public class HttpTestService implements IHttpTestService {

	@Autowired
	private IBusinessService businessService;
	@Autowired
	private IOrderService order;
	@Override
	public HttpResultModel<BusinessDetailModel> selectByID(TestServiceReq req) {
		//throw new RuntimeException("sdfsds");
		BusinessDetailModel listData = businessService.getBusinessDetailByID(123);
		HttpResultModel<BusinessDetailModel> result=new HttpResultModel<BusinessDetailModel>();
		result.setStatus(HttpReturnRnums.Success.value());
		result.setMessage(HttpReturnRnums.Success.desc());
		result.setResult(listData);
		return result;  


	}

	@Override
	public HttpResultModel<BusinessDetailModel>  getList() {
		BusinessDetailModel listData = businessService.getBusinessDetailByID(123);
		HttpResultModel<BusinessDetailModel> result=new HttpResultModel<BusinessDetailModel>();
		result.setStatus(HttpReturnRnums.Success.value());
		result.setMessage(HttpReturnRnums.Success.desc());
		result.setResult(listData);
		return result;  
	}

	@Override
	public HttpResultModel<BusinessDetailModel> signalrPush() {
		order.asyncShanSongPushOrder(new GetPushClienterIdsReq(33.222222223,
				132.2525252522,
				ParseHelper.ToInt(PropertyUtils.getProperty("ShanSongPushOrderTimeInfo")),
				ParseHelper.ToInt(PropertyUtils.getProperty("ShanSongPushOrderDistanceInfo"))*1000),149002);
		return null;
	}

}
