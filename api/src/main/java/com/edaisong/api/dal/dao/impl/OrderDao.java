package com.edaisong.api.dal.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.edaisong.api.dal.dao.inter.IOrderDao;
import com.edaisong.entity.Account;
import com.edaisong.entity.Order;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessOrderSummaryModel;
import com.edaisong.entity.domain.OrderDetailBusiness;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.domain.OrderMapDetail;
import com.edaisong.entity.req.OrderDetailBusinessReq;
import com.edaisong.entity.req.PagedOrderSearchReq;

@Repository
public class OrderDao extends DaoBase implements IOrderDao {	
	
	@Override
	public int insert(Order record)
	{
		return getMasterSqlSessionUtil().insert(
				"com.edaisong.api.dal.dao.inter.IOrderDao.insertSelective", record);
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
				"com.edaisong.api.dal.dao.inter.IOrderDao.GetOrders", search);
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
	public OrderMapDetail getOrderMapDetail(long orderId) {
		OrderMapDetail result = getReadOnlySqlSessionUtil().selectOne(
				"com.edaisong.api.dal.dao.inter.IOrderDao.getOrderMapDetail",
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
						"com.edaisong.api.dal.dao.inter.IOrderDao.getOrderDetailBusiness",
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
						"com.edaisong.api.dal.dao.inter.IOrderDao.getOneByCriteria",
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
						"com.edaisong.api.dal.dao.inter.IOrderDao.cancelOrderBusiness",
						order);
		return res;
	}

	@Override
	public BusinessOrderSummaryModel getBusinessOrderSummary(int businessId) {
		// TODO Auto-generated method stub
		return getMasterSqlSessionUtil().selectOne(
				"com.edaisong.api.dal.dao.inter.IOrderDao.getBusinessOrderSummary", 
				businessId);
	}
}
