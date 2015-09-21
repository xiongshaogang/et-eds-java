package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IDeliveryCompanyDao;
import com.edaisong.api.dao.inter.IPublicProvinceCityDao;
import com.edaisong.api.service.inter.IDeliveryCompanyService;
import com.edaisong.entity.DeliveryCompany;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.DeliveryStatistics;
import com.edaisong.entity.req.PagedDeliveryStatisticsReq;

/*
 * 获取物流公司
 */
@Service
public class DeliveryCompanyService implements IDeliveryCompanyService {
	@Autowired
	private IDeliveryCompanyDao deliveryCompDao;

	/**
	 * 获取配送公司列表 2015年7月29日 13:14:41
	 * */
	@Override
	public List<DeliveryCompany> getDeliveryCompanyList(){
		return deliveryCompDao.getDeliveryCompanyList();		
	}

	@Override
	public PagedResponse<DeliveryStatistics> getStatisticsList(
			PagedDeliveryStatisticsReq search) {
			return deliveryCompDao.getStatisticsList(search);
	}
}
