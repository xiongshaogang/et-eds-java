package com.edaisong.business.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.impl.OrderService;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.req.CancelOrderBusinessReq;
import com.edaisong.entity.req.GroupReq;
import com.edaisong.entity.req.OrderDetailBusinessReq;
import com.edaisong.entity.req.OrderReq;
import com.edaisong.entity.req.PagedOrderSearchReq;
import com.edaisong.entity.resp.CancelOrderBusinessResp;
import com.edaisong.entity.resp.OrderResp;
import com.edaisong.api.service.inter.IClienterService;
import com.edaisong.entity.resp.BusinessLoginResp;

@Controller
@RequestMapping("order")
public class OrderController {
	@Autowired
	IOrderService orderService;
	 /**
		 * 订单列表页面 
		 * @author zhaohailong
		 * @Date 20150806
		 * @return
		 */
		@RequestMapping("list2")
		public ModelAndView list(){
			ModelAndView model = new ModelAndView("order/list");
			return model;
		}
		
		/**
		 * 订单列表页面 
		 * @author zhaohailong
		 * @Date 20150806
		 * @return
		 */
		@RequestMapping("list")
		public ModelAndView listdo(PagedOrderSearchReq searchWebReq){
			PagedResponse<OrderListModel> resp = orderService.getOrders(searchWebReq);
			ModelAndView view = new ModelAndView("order/listdo");
			view.addObject("listData", resp);
			return view;
		}
	/**
	 * 订单详情 
	 * @author CaoHeYang
	 * @Date 20150805
	 * @return
	 */
	@RequestMapping(value = "detail", method = { RequestMethod.GET })
	public  ModelAndView detail(OrderDetailBusinessReq req) {
		ModelAndView model = new ModelAndView("");
		model.addObject("subtitle", "订单中心");
		model.addObject("currenttitle", "订单详情");
		model.addObject("viewPath", "order/detail");
		model.addObject("datas",orderService.getOrderDetailBusiness(req));
		return model;
	}
	
	/**
	 * 商户后台取消订单
	 * @author CaoHeYang
	 * @Date 20150805
	 * @return
	 */
	@RequestMapping(value = "canelorder", method = { RequestMethod.POST })
	@ResponseBody
	public CancelOrderBusinessResp login(@RequestBody CancelOrderBusinessReq req) {
		CancelOrderBusinessResp resp=orderService.cancelOrderBusiness(req);
		return resp;
	}
	
	
	
	/**
	 * 商户后台发布订单
	 * @author 胡灵波
	 * @Date 2015年8月6日 13:37:00
	 * @return
	 */
	@RequestMapping(value = "publish")
	@ResponseBody
	public OrderResp publish() {
		OrderResp resp=new OrderResp();
		OrderReq req=new OrderReq();
		resp=orderService.AddOrder(req);
		
		return resp;
	}
}
