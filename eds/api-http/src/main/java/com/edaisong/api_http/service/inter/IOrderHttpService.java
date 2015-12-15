package com.edaisong.api_http.service.inter;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.edaisong.entity.OrderChild;
import com.edaisong.entity.OrderDetail;
import com.edaisong.entity.OrderDraft;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.domain.InStoreTask;
import com.edaisong.entity.domain.QueryOrder;
import com.edaisong.entity.resp.MyOrderGrabDetailCResp;
import com.edaisong.entity.req.OrderBlancePayReq;
import com.edaisong.entity.req.OrderChildCancelReq;
import com.edaisong.entity.req.OrderDetailReq;
import com.edaisong.entity.req.OrderDraftReq;
import com.edaisong.entity.req.OrderDraftGetReq;
import com.edaisong.entity.req.OrderDraftReturnReq;
import com.edaisong.entity.req.OrderGrabReq;
import com.edaisong.entity.req.OrderPushReq;
import com.edaisong.entity.req.OrderReq;
import com.edaisong.entity.req.InStoreTaskReq;
import com.edaisong.entity.req.OrderStatisticsBReq;
import com.edaisong.entity.req.OrderStatisticsCReq;
import com.edaisong.entity.req.QueryOrderReq;
import com.edaisong.entity.req.OrderGrabDetailCReq;
import com.edaisong.entity.req.QueryShanSongOrderReq;
import com.edaisong.entity.resp.OrderBlancePayResp;
import com.edaisong.entity.resp.OrderDetailResp;
import com.edaisong.entity.resp.OrderDraftResp;
import com.edaisong.entity.resp.OrderGrabResp;
import com.edaisong.entity.resp.OrderResp;
import com.edaisong.entity.resp.OrderStatisticsBResp;
import com.edaisong.entity.resp.OrderTipDetailResp;
import com.edaisong.entity.resp.QueryOrderBResp;
import com.edaisong.entity.resp.OrderStatisticsCResp;
import com.edaisong.entity.resp.QueryOrderCResp;

/**
 * 订单模块
 * 
 * @author CaoHeYang
 * @date 20150910
 */
@Path("/order")
@Consumes("application/json")
// 当前方法接收的参数类型
@Produces("application/json; charset=utf-8")
// 当前类的所有方法都返回json格式的数据
public interface IOrderHttpService {
	
	/**
	 * 线上测试方法
	 * 窦海超
	 * 2015年9月22日 11:57:16
	 * */
	@POST
	@Path("testval")
	public String testVal();
	
	
	/**
	 * 发布订单 
	 * @author 胡灵波
	 * @date 2015年10月30日 11:29:00
	 * @version 1.0
	 * @param req
	 * @return
	 */
	@POST
	@Path("/push")
	public HttpResultModel<OrderResp> Push(OrderReq req);	

	/**  
	 * 发布订单  快单模式
	 * @author 胡灵波
	 * @date 2015年10月30日 11:29:00
	 * @version 1.0
	 * @param req
	 * @return
	 */
	@POST
	@Path("/cancelOrderChild")
	public HttpResultModel<OrderGrabResp> CancelOrderChild(OrderChildCancelReq  req);	
	

	// region 闪送模式
	/**
	 * 发布订单(闪送模式) 
	 * @author 胡灵波
	 * @date 2015年11月24日 13:24:18
	 * @version 1.0
	 * @param req
	 * @return
	 */
	@POST
	@Path("/flashpush")
	public HttpResultModel<OrderResp> FlashPush(OrderDraftReq req);
	/**
	 * 发布订单 余额支付  闪送模式
	 * @author 胡灵波
	 * @date 2015年12月14日 11:21:34
	 * @version 1.0
	 * @param req
	 * @return
	 */
	@POST
	@Path("/orderbalancepay")
	public HttpResultModel<OrderBlancePayResp> OrderBalancePay(OrderBlancePayReq req);
	/**
	 * 获取订单详情(闪送模式) 
	 * @author 胡灵波
	 * @date 2015年11月27日 11:45:36
	 * @version 1.0
	 * @param req
	 * @return
	 */
	@POST
	@Path("/getorderdetails")
	public HttpResultModel<OrderDetailResp> GetOrderDetails(OrderDetailReq req);
	
	/**
	 * 获取小费详情(闪送模式) 
	 * @author 胡灵波
	 * @date 2015年12月3日 11:57:01
	 * @version 1.0
	 * @param req
	 * @return
	 */
	@POST
	@Path("/getordertipdetails")
	public HttpResultModel<OrderTipDetailResp> GetOrderTipDetails();
	// endregion	
	

	/**
	 * B端任务统计接口
	 * 
	 * @author CaoHeYang
	 * @date 20150910
	 * @param orderStatisticsBReq
	 * @return
	 */
	@POST
	@Path("/orderstatisticsb")
	public HttpResultModel<OrderStatisticsBResp> orderStatisticsB(OrderStatisticsBReq orderStatisticsBReq);

	/**
	 * B端商家首页
	 * 
	 * @author CaoHeYang
	 * @date 20150910
	 * @param  para
	 * @return
	 */
	@POST
	@Path("/queryorderb")
	public HttpResultModel<QueryOrderBResp> queryOrderB(QueryOrderReq para);
	
	/**
	 * C 端我的任务
	 * 
	 * @author CaoHeYang
	 * @date 20150911
	 * @param para
	 */
	@POST
	@Path("/queryorderc")
	HttpResultModel<QueryOrderCResp> queryOrderC(QueryOrderReq para);

	/**
	 * B端已完成任务列表或者配送员配送列表
	 * 
	 * @author CaoHeYang
	 * @date 20150910
	 * @param para
	 * @return
	 */
	@POST
	@Path("/getcompliteorderb")
	public HttpResultModel<List<QueryOrder>> getCompliteOrderB(QueryOrderReq para);

	/**
	 * C端已完成任务列表
	 * 
	 * @author CaoHeYang
	 * @date 20150910
	 * @param para
	 * @return
	 */
	@POST
	@Path("/getcompliteorderc")
	public HttpResultModel<List<QueryOrder>> getCompliteOrderC(QueryOrderReq para);

	/**
	 * C端任务统计接口
	 * 
	 * @author WangXuDan
	 * @date 20150910
	 * @param data
	 */
	@POST
	@Path("/orderstatisticsc")
	public HttpResultModel<OrderStatisticsCResp> orderStatisticsC(OrderStatisticsCReq orderStatisticsCReq);

	/**
	 *  骑士端获取店内任务
	 * @version 3.0  
	 * @author CaoHeYang
	 * @date 20151030
	 * @param para
	 * @return
	 */
	@POST
	@Path("/getinstoretask")
	public HttpResultModel<List<InStoreTask>>  getInStoreTask(InStoreTaskReq para);
	
	
	/**
	 * C端任务统计接口 -- 新版九宫格 
	 * @author wangchao
	 * @param data
	 */
	@POST
	@Path("/ordergrabstatisticsc")
	public HttpResultModel<OrderStatisticsCResp> orderGrabStatisticsC(OrderStatisticsCReq orderStatisticsCReq);
	
	/**
	 * B端任务统计接口 -- 新版九宫格 
	 * 
	 * @author wangchao
	 * @param orderStatisticsBReq
	 * @return
	 */
	@POST
	@Path("/ordergrabstatisticsb")
	public HttpResultModel<OrderStatisticsBResp> orderGrabStatisticsB(OrderStatisticsBReq orderStatisticsBReq);
	
	/**
	 * B端已完成任务列表或者配送员配送列表  -- 新版九宫格 
	 * @author wangchao
	 * @return
	 */
	@POST
	@Path("/getcompliteordergrabb")
	public HttpResultModel<List<QueryOrder>> getCompliteOrderGrabB(QueryOrderReq para);
	/**
	 *  
	 * @author wangchao
	 * @return 闪送B端 商家我的订单
	 */
	@POST
	@Path("/shansongqueryorderb")
	HttpResultModel<QueryOrderBResp> shanSongQueryOrderB(QueryShanSongOrderReq query) ;
}
