package com.edaisong.entity.req;

import com.edaisong.entity.common.RequestBase;
/**
 * 交易明细列表 请求参数
 * 2015年8月4日10:23:51
 * 茹化肖
 * 
 * */
public class TransDetailReq extends RequestBase {

	private String startDate;
	private String endDate;
	private String TransType;
	private String NumType;
	private String NumString;
	private int currentPage;
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * 交易类型 
	 *  1：发布订单  订单号
	 *  2：取消订单  订单号
	 *  6：系统奖励  空
	 *  8：订单菜品费 订单号 
	 *  9：充值  流水
	 *  11 手续费) 流水
	 * */
	public String getTransType() {
		return TransType;
	}
	public void setTransType(String transType) {
		TransType = transType;
	}
	/**
	 * 单号类型,1订单号 2 流水单号
	 * */
	public String getNumType() {
		return NumType;
	}
	public void setNumType(String numType) {
		NumType = numType;
	}
	/**
	 *订单号/流水单号 
	 */
	public String getNumString() {
		return NumString;
	}
	public void setNumString(String numString) {
		NumString = numString;
	}
	
}
