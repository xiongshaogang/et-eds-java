package com.edaisong.api.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IBusinessBalanceRecordDao;
import com.edaisong.api.service.inter.IBusinessBalanceRecordService;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.entity.AccountBillResultModel;
import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.AccountBillModel;
import com.edaisong.entity.domain.BusinessBalanceRecordModel;
import com.edaisong.entity.req.AccountBillBReq;
import com.edaisong.entity.req.BussinessBalanceQueryReq;
import com.edaisong.entity.req.PagedCustomerSearchReq;
import com.edaisong.entity.req.PagedTransDetailReq;
@Service
public class BusinessBalanceRecordService implements IBusinessBalanceRecordService {
	@Autowired
	private IBusinessBalanceRecordDao businessBalanceRecordDao;
	@Override
	public PagedResponse<BusinessBalanceRecord> getTransDetailList(
			PagedTransDetailReq par) {
		return businessBalanceRecordDao.getTransDetailList(par);
	}
	@Override
	public PagedResponse<BusinessBalanceRecord> customerGetTransDetailList(
			PagedCustomerSearchReq par) {
		return businessBalanceRecordDao.customerGetTransDetailList(par);
	}
	@Override
	public double queryBusinessRechargeTotalAmount(BussinessBalanceQueryReq par) throws ParseException {
		return businessBalanceRecordDao.queryBusinessRechargeTotalAmount(par);
	}
	@Override
	public List<BusinessBalanceRecordModel> getBusinessBalanceRecordListForExport(PagedTransDetailReq par) {
		return businessBalanceRecordDao.getBusinessBalanceRecordListForExport(par);
	}
	/**
	 * API 获取商户月账单信息
	 * 茹化肖
	 * 2015年9月10日10:25:04
	 * 
	 * */
	@Override
	public AccountBillResultModel getAccountBillListB(AccountBillBReq par) {
		List<AccountBillModel> list = new ArrayList<AccountBillModel>();
		Map<String, AccountBillModel> map=businessBalanceRecordDao.getAccountBillListB(par);
		String year = par.getMonthInfo().split("-")[0];// 年
		String month = par.getMonthInfo().split("-")[1];// 月
		Integer dayscount = ParseHelper.GetMixDay(year, month);// 共多少天
		Double monthOutMoney=0.0;//月总支出
		Double monthInMoney=0.0;//月总收入
		
		for (int i = 1; i <= dayscount; i++) {//创建当月的每天的数据
			String dayInfo="";
			if(i<10)
			{
				dayInfo=par.getMonthInfo()+"-0"+i;
			}
			else {
				dayInfo= par.getMonthInfo()+"-"+String.valueOf(i);
			}
			//初始化数据
			AccountBillModel model=new AccountBillModel();
			model.setDayInfo(dayInfo);
			model.setInMoney(0.0);
			model.setHasDatas(0);
			model.setOutMoney(0.0);
			if(map.containsKey(dayInfo))
			{
				//map中存在这天的数据 覆盖到list
				model.setInMoney(map.get(dayInfo).getInMoney());
				model.setHasDatas(1);
				model.setOutMoney(map.get(dayInfo).getOutMoney());
				monthInMoney+=map.get(dayInfo).getInMoney();//累计当月收入
				monthOutMoney+=map.get(dayInfo).getOutMoney();//累计当月支出
			}
			list.add(model);
		}
		//返回对象数据
		AccountBillResultModel resultModel=new AccountBillResultModel();
		resultModel.setInMoney(monthInMoney);
		resultModel.setOutMoney(monthOutMoney);
		resultModel.setListDays(list);
		return resultModel;
	}

}
