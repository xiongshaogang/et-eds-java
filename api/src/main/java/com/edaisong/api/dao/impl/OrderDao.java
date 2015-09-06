package com.edaisong.api.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IOrderDao;
import com.edaisong.entity.Account;
import com.edaisong.entity.Order;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusiPubOrderTimeStatisticsModel;
import com.edaisong.entity.domain.BusinessOrderSummaryModel;
import com.edaisong.entity.domain.OrderDetailBusiness;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.domain.OrderMapDetail;
import com.edaisong.entity.req.OrderDetailBusinessReq;
import com.edaisong.entity.req.OrderOtherSearch;
import com.edaisong.entity.req.PagedCustomerSearchReq;
import com.edaisong.entity.req.PagedOrderSearchReq;

@Repository
public class OrderDao extends DaoBase implements IOrderDao {	
	
	@Override
	public int insert(Order record)
	{
		return getMasterSqlSessionUtil().insert(
				"com.edaisong.api.dao.inter.IOrderDao.insertSelective", record);
	}
	
	/**
	 * 后台订单列表页面
	 * 
	 * @author CaoHeYang
	 * @Date 20150728
	 * @param search
	 *            查询条件实体
	 * @return
	 */
	@Override
	public PagedResponse<OrderListModel> getOrders(PagedOrderSearchReq search) {

		PagedResponse<OrderListModel> result = new PagedResponse<OrderListModel>();
		result = getReadOnlySqlSessionUtil().selectPageList(
				"com.edaisong.api.dao.inter.IOrderDao.GetOrders", search);
		return result;
	}

	/**
	 * 根据orderID获取订单地图数据
	 * 
	 * @param orderId
	 * @author CaoHeYang
	 * @Date 20150730
	 * @return
	 */
	public OrderMapDetail getOrderMapDetail(int orderId) {
		OrderMapDetail result = getReadOnlySqlSessionUtil().selectOne(
				"com.edaisong.api.dao.inter.IOrderDao.getOrderMapDetail",
				orderId);
		return result;
	}

	/**
	 * 商家后台 订单详情页面基础数据
	 * 
	 * @param para
	 *            查询条件
	 * @author CaoHeYang
	 * @Date 20150804
	 * @return
	 */
	public OrderDetailBusiness getOrderDetailBusiness(
			OrderDetailBusinessReq para) {
		OrderDetailBusiness result = getReadOnlySqlSessionUtil()
				.selectOne(
						"com.edaisong.api.dao.inter.IOrderDao.getOrderDetailBusiness",
						para);
		return result;
	}

	/**
	 * 以自身的字段作为查询条件 查询order 的基础数据 可扩展
	 * 
	 * @param order
	 *            条件实体
	 * @author CaoHeYang
	 * @Date 20150804
	 * @return
	 */
	@Override
	public Order getOneByCriteria(Order order) {
		Order result = getReadOnlySqlSessionUtil()
				.selectOne(
						"com.edaisong.api.dao.inter.IOrderDao.getOneByCriteria",
						order);
		return result;
	}


	/**
	 * 商户取消订单功能
	 * 
	 * @author CaoHeYang
	 * @Date 20150804
	 * @param order
	 * @return
	 */
	@Override
	public int cancelOrderBusiness(Order order) {
		int res = getMasterSqlSessionUtil()
				.update(
						"com.edaisong.api.dao.inter.IOrderDao.cancelOrderBusiness",
						order);
		return res;
	}

	@Override
	public BusinessOrderSummaryModel getBusinessOrderSummary(int businessId) {
		return getReadOnlySqlSessionUtil().selectOne(
				"com.edaisong.api.dao.inter.IOrderDao.getBusinessOrderSummary", 
				businessId);
	}

	@Override
	public List<BusiPubOrderTimeStatisticsModel> getBusiPubOrderTimeStatistics(int businessId,Date startTime,Date endTime) {
		Map<String, Object> paramMap = new HashedMap();
		paramMap.put("businessId", businessId);
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		return getReadOnlySqlSessionUtil().selectList(
				"com.edaisong.api.dao.inter.IOrderDao.getBusiPubOrderTimeStatistics", 
				paramMap);
	}

	@Override
	public PagedResponse<OrderListModel> customerGetOrders(PagedCustomerSearchReq req) {
		return getReadOnlySqlSessionUtil().selectPageList("com.edaisong.api.dao.inter.IOrderDao.customerGetOrders", req);
	}

	 /**
	  *  根据订单号/订单id查订单信息
	  * @author CaoHeYang
	  * @param ordernNo 订单号
	  * @param orderId  订单id
	  * @Date 20150827
	  * @return
	  */
	@Override
	public OrderListModel getOrderByNoId(String ordernNo, int orderId) {
		Map<String, Object> paramMap = new HashedMap();
		paramMap.put("orderNo", ordernNo);  //订单号
		paramMap.put("orderId", orderId);  //订单id
		return getReadOnlySqlSessionUtil().selectOne("com.edaisong.api.dao.inter.IOrderDao.getOrderByNoId", paramMap);
	}
	
	 /**
	  *  根据订单号/订单id查订单信息(写库),
	  *  此处查询使用了 nolock
	  * @author 胡灵波
	  * @param ordernNo 订单号
	  * @param orderId  订单id
	  * @Date 2015年9月1日 14:20:22
	  * @return
	  */
	@Override
	public OrderListModel getOrderWriteByNoId(String ordernNo, int orderId) {
		Map<String, Object> paramMap = new HashedMap();
		paramMap.put("orderNo", ordernNo);  //订单号
		paramMap.put("orderId", orderId);  //订单id
		return getMasterSqlSessionUtil().selectOne("com.edaisong.api.dao.inter.IOrderDao.getOrderByNoId", paramMap);
	}
	/**
	 * update 任意列 根据id 
	 * @author CaoHeYang
	 * @param order
	 * @return
	 */
  public int updateByPrimaryKeySelective(Order order){
	  return getMasterSqlSessionUtil().update("com.edaisong.api.dao.inter.IOrderDao.updateByPrimaryKeySelective", order);
  }
  /**
   * 更新订单真实佣金
   * @author CaoHeYang
   * @param orderOtherSearch
   * @date 20150831
   * @return
   */
	@Override
	public int updateOrderRealCommission(OrderOtherSearch orderOtherSearch) {
		return getMasterSqlSessionUtil().update("com.edaisong.api.dao.inter.IOrderDao.updateOrderRealCommission", orderOtherSearch);
	}
}
