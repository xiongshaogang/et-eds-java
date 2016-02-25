package com.edaisong.admin.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.entity.GroupBusiness;
import com.edaisong.entity.GroupBusinessLog;
import com.edaisong.entity.GroupBusinessRecharge;
import com.edaisong.entity.domain.GroupBusinessModel;
import com.edaisong.entity.req.PagedGroupBusinessReq;
import com.edaisong.admin.common.UserContext;
import com.edaisong.api.service.impl.BusinessService;
import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.api.service.inter.IGroupBusinessBindOptionLogService;
import com.edaisong.api.service.inter.IGroupBusinessLogService;
import com.edaisong.api.service.inter.IGroupBusinessRelationService;
import com.edaisong.api.service.inter.IGroupBusinessService;
import com.edaisong.core.enums.BindOptType;
import com.edaisong.core.security.MD5Util;
import com.edaisong.core.util.ExcelUtils;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.StringUtils;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.domain.BusinessBalanceRecordModel;
import com.edaisong.entity.domain.BusinessDetailModel;
import com.edaisong.entity.domain.BusinessRechargeDetailModel;
import com.edaisong.entity.domain.GroupBusinessBalanceRecord;
import com.edaisong.entity.domain.GroupBusinessBindOptionLogModel;
import com.edaisong.entity.domain.GroupBusinessRelationModel;
import com.edaisong.entity.req.BusinessBindOptionReq;
import com.edaisong.entity.req.BussinessBalanceQueryReq;
import com.edaisong.entity.req.GroupBusinessReq;
import com.edaisong.entity.req.PagedGroupBussinessBalanceReq;
import com.edaisong.entity.req.PagedBizBindsReq;
import com.edaisong.entity.req.PagedBusinessBindLogReq;
import com.edaisong.entity.req.PagedTransDetailReq;

/**
 * 集团商户controller
 * @author  pengyi 
 * @date 2015年9月9日 上午10:20:39
 * @version 1.0
 * @parameter
 * @since
 */
@Controller
@RequestMapping("groupbusiness")
public class GroupBusinessController {
	
	@Autowired
	private IGroupBusinessRelationService groupBusinessRelationService;
	@Autowired
	private IGroupBusinessBindOptionLogService groupBusinessBindOptionLogService;
	@Autowired
	 private IGroupBusinessService  groupBusinessService;
	@Autowired
	private IGroupBusinessLogService  groupBusinessLogService;
	@Autowired
	private IBusinessService businessService;
	
	/*
	 * 集团商户管理
	 * WangChao
	 */
	@RequestMapping("list")
	public ModelAndView list(){ 
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "集团");
		model.addObject("currenttitle", "集团管理"); 
		model.addObject("viewPath", "groupbusiness/list");
		return model;
	}
	/*
	 * 集团管理列表
	 * WangChao
	 */
	@RequestMapping("listdo")
	public ModelAndView listdo(PagedGroupBusinessReq req) {	 
		PagedResponse<GroupBusinessModel> resp = groupBusinessService.getPageList(req); 
		ModelAndView model = new ModelAndView("groupbusiness/listdo");
		model.addObject("listData", resp);
		return model;
	}	
	/*
	 * 添加集团商户
	 * WangChao
	 */
	@RequestMapping("addgroupbusiness")
	@ResponseBody
	public ResponseBase addGroupBusiness(GroupBusinessReq bgm, HttpServletRequest request){
		ResponseBase response = new ResponseBase();
		//判断集团商户是否存在
		GroupBusinessReq gbr1 = new GroupBusinessReq();
		gbr1.setgroupBusinessName(bgm.getbusinessGroupName());
	 	GroupBusinessModel gbm1 = groupBusinessService.getSingle(gbr1);
		if(gbm1!=null && gbm1.getGroupbusiname()!=null){
			response.setMessage("集团名称已存在");
			response.setResponseCode(0);
			return response;
		}
		//判断登陆名是否存在
		GroupBusinessReq gbr2 = new GroupBusinessReq();
		gbr2.setloginName(bgm.getloginName());
	 	GroupBusinessModel gbm2 = groupBusinessService.getSingle(gbr2);
		if(gbm2!=null && gbm2.getGroupbusiname()!=null){ 
			response.setMessage("此登录账号已经存在");
			response.setResponseCode(0);
			return response;
		}
		GroupBusiness groupBusiness = new GroupBusiness();
		groupBusiness.setGroupbusiname(bgm.getbusinessGroupName().trim());
		groupBusiness.setLoginname(bgm.getloginName().trim());
		groupBusiness.setPassword(MD5Util.MD5(bgm.getpassWord().trim()));
		groupBusiness.setIsAllowOverdraft(bgm.getIsAllowOverdraft());
		groupBusiness.setCreatename(UserContext.getCurrentContext(request).getLoginName());
		int result=groupBusinessService.addGroupBusiness(groupBusiness);
		if(result<=0){
			response.setMessage("添加失败");
			response.setResponseCode(0);
			return response;
		}
		response.setMessage("添加成功");
		response.setResponseCode(1);
		return response;
	}
	/*
	 * 修改集团商户
	 * WangChao
	 */
	@RequestMapping("modifygroupbusiness")
	@ResponseBody
	public ResponseBase modifyGroupBusiness(GroupBusinessReq bgm, HttpServletRequest request){
		ResponseBase response = new ResponseBase();
		//判断集团商户是否存在
		GroupBusinessReq gbr1 = new GroupBusinessReq();
		gbr1.setgroupBusinessName(bgm.getbusinessGroupName());
	 	GroupBusinessModel gbm1 = groupBusinessService.getSingle(gbr1);
		if(gbm1!=null && gbm1.getGroupbusiname()!=null){
			if(gbm1.getId() != bgm.getId()){
				response.setMessage("集团名称已存在");
				response.setResponseCode(0);
				return response;
			}
		}
		//判断登陆名是否存在
		GroupBusinessReq gbr2 = new GroupBusinessReq();
		gbr2.setloginName(bgm.getloginName());
	 	GroupBusinessModel gbm2 = groupBusinessService.getSingle(gbr2);
		if(gbm2!=null &&gbm2.getGroupbusiname()!=null){ 
			if(gbm2.getId() != bgm.getId()){
				response.setMessage("此登录账号已经存在");
				response.setResponseCode(0);
				return response;
			}
		} 
		GroupBusiness groupBusiness = new GroupBusiness();
		groupBusiness.setGroupbusiname(bgm.getbusinessGroupName().trim());
		groupBusiness.setLoginname(bgm.getloginName().trim());
		if(!bgm.getpassWord().trim().equals("")){
			groupBusiness.setPassword(MD5Util.MD5(bgm.getpassWord().trim()));
		}else{
			groupBusiness.setPassword("");
		}
		
		groupBusiness.setId(bgm.getId());
		groupBusiness.setIsAllowOverdraft(bgm.getIsAllowOverdraft());
		groupBusiness.setModifyname(UserContext.getCurrentContext(request).getLoginName());
		int result=groupBusinessService.modifyGroupBusiness(groupBusiness);
		if(result<=0){
			response.setMessage("修改失败");
			response.setResponseCode(0);
			return response;
		}
		response.setMessage("修改成功");
		response.setResponseCode(1);
		return response;
	}
	/*
	 * 获取商户操作日志
	 * WangChao
	 */
	@RequestMapping("getgroupbusinesslog")
	@ResponseBody
	public ResponseBase getGroupBusinessLog(int id){
		ResponseBase response = new ResponseBase(); 
		StringBuilder sb = new StringBuilder();
		List<GroupBusinessLog> groupBusinessLogList =groupBusinessLogService.getList(id);
		if(groupBusinessLogList!=null && groupBusinessLogList.size()>0){ 
			sb.append("<table style='border-collapse: collapse;border:none;margin:0'><th style='border: #D6D6D6 1px solid'>时间</th><th style='border: #D6D6D6 1px solid'>操作</th>");
			for (int i = 0; i < groupBusinessLogList.size(); i++) {
		 
			sb.append("<tr style='border: #D6D6D6 1px solid'><td style='border: #D6D6D6 1px solid'>").append(ParseHelper.ToDateString( groupBusinessLogList.get(i).getOpttime())).append("</td><td>").append(groupBusinessLogList.get(i).getOptname()+groupBusinessLogList.get(i).getRemark()).append("</td></tr>");
			}
		sb.append("</table>");
		}
		response.setMessage(sb.toString());
		return response; 
	} 
	/**
	 * 绑定列表(分页)
	 * @author pengyi
	 * @date 2015年9月9日 下午5:35:31
	 * @version 1.0
	 * @param groupBusinessId
	 * @return
	 */
	@RequestMapping("businessbindlist")
	public ModelAndView businessBindList(Integer groupBusinessId) throws Exception{
		GroupBusinessReq req = new GroupBusinessReq();
		groupBusinessId = groupBusinessId == null ? 0 : groupBusinessId;
		req.setId(groupBusinessId);
		GroupBusinessModel detail = groupBusinessService.getSingle(req);
		if (detail == null) {
			throw new Exception("没找到groupBusinessId为" + groupBusinessId + "的详细信息");
		}
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "集团");
		model.addObject("currenttitle", "集团门店管理");
		model.addObject("viewPath", "groupbusiness/businessbindlist");
		model.addObject("detail", detail);
		return model;
	}
	
	@RequestMapping("businessbindlistdo")
	public ModelAndView businessBindListdo(PagedBizBindsReq req){
		PagedResponse<GroupBusinessRelationModel> resp = groupBusinessRelationService.getBusinessBindList(req);
		ModelAndView model = new ModelAndView("groupbusiness/businessbindlistdo");
		model.addObject("listData", resp);
		return model;
	}
	
	/**
	 * 绑定日志列表(分页)
	 * @author pengyi
	 * @date 2015年9月9日 下午5:35:16
	 * @version 1.0
	 * @param groupBusinessId
	 * @return
	 */
	@RequestMapping("businessbindloglist")
	public ModelAndView businessBindLogList(Integer groupBusinessId) throws Exception{
		GroupBusinessReq req = new GroupBusinessReq();
		groupBusinessId = groupBusinessId == null ? 0 : groupBusinessId;
		req.setId(groupBusinessId);
		GroupBusinessModel detail = groupBusinessService.getSingle(req);
		if (detail == null) {
			throw new Exception("没找到groupBusinessId为" + groupBusinessId + "的详细信息");
		}
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "集团");
		model.addObject("currenttitle", "绑定记录查询");
		model.addObject("viewPath", "groupbusiness/businessbindloglist");
		model.addObject("detail", detail);
		return model;
	}
	
	@RequestMapping("businessbindloglistdo")
	public ModelAndView businessBindLogListdo(PagedBusinessBindLogReq req){
		PagedResponse<GroupBusinessBindOptionLogModel> resp = groupBusinessBindOptionLogService.getBusinessBindLogList(req);
		ModelAndView model = new ModelAndView("groupbusiness/businessbindloglistdo");
		model.addObject("listData", resp);
		return model;
	}
	
	/**
	 * 门店列表(绑定门店用)
	 * @author pengyi
	 * @date 2015年9月10日 上午10:13:12
	 * @version 1.0
	 * @param groupBusinessId
	 * @return
	 */
	@RequestMapping("businesslist")
	public ModelAndView businessList(Integer groupBusinessId) throws Exception{
		GroupBusinessReq req = new GroupBusinessReq();
		groupBusinessId = groupBusinessId == null ? 0 : groupBusinessId;
		req.setId(groupBusinessId);
		GroupBusinessModel detail = groupBusinessService.getSingle(req);
		if (detail == null) {
			throw new Exception("没找到groupBusinessId为" + groupBusinessId + "的详细信息");
		}
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "集团");
		model.addObject("currenttitle", "绑定门店");
		model.addObject("viewPath", "groupbusiness/businesslist");
		model.addObject("detail", detail);
		return model;
	}
	
	@RequestMapping("businesslistdo")
	public ModelAndView businessListdo(PagedBizBindsReq req){
		PagedResponse<GroupBusinessRelationModel> resp = groupBusinessRelationService.getBusinessList(req);
		ModelAndView model = new ModelAndView("groupbusiness/businesslistdo");
		model.addObject("listData", resp);
		return model;
	}
	
	@RequestMapping("removebusinessbind")
	@ResponseBody
	public ResponseBase removeBusinessBind(BusinessBindOptionReq req, HttpServletRequest request) {
		ResponseBase response = new ResponseBase();
		req.setOptId(UserContext.getCurrentContext(request).getId());
		req.setOptType((short)BindOptType.RemoveBind.value());
		req.setOptName(UserContext.getCurrentContext(request).getLoginName());
		req.setRemark("解除绑定");
		if (groupBusinessRelationService.removeBusinessBind(req)) {
			response.setResponseCode(1);
			response.setMessage("绑定成功");
			return response;
		}
		response.setMessage("绑定失败");
		return response;
	}
	/**
	 * 集团商户添加绑定
	 * @param req
	 * @param request
	 * @return
	 */
	@RequestMapping("addbusinessbind")
	@ResponseBody
	public ResponseBase addBusinessBind(BusinessBindOptionReq req, HttpServletRequest request) {
		ResponseBase response = new ResponseBase();
		req.setOptId(UserContext.getCurrentContext(request).getId());
		req.setOptType((short)BindOptType.Bind.value());
		req.setOptName(UserContext.getCurrentContext(request).getLoginName());
		req.setRemark("添加绑定");
		BusinessDetailModel model= businessService.getBusinessDetailByID(req.getBusinessId());
		if (groupBusinessRelationService.checkHaveBind(req)) {//判断绑定关系是否存在
			response.setMessage("此条绑定关系已存在！");
		}
		else if(model.getStatus()!=Byte.valueOf((byte)1))//判断商户是否通过审核
		{
			response.setMessage("商家未审核通过！");
		}
		else{
			//添加绑定
			if (groupBusinessRelationService.addBusinessBind(req)) {
				response.setMessage("绑定成功");
				response.setResponseCode(1);
			}
		}
		return response;
	}
	
	/*
	 * 集团收支管理
	 * WangChao
	 */
	@RequestMapping("balancerecordlist")
	public ModelAndView balancerecordlist(Integer groupBusinessId) throws Exception { 
		
		GroupBusinessReq groupBusinessReq = new GroupBusinessReq();
		groupBusinessReq.setId(groupBusinessId);
		//获取该集团信息
		GroupBusinessModel groupBusinessModel= groupBusinessService.getSingle(groupBusinessReq); 
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "集团");
		model.addObject("currenttitle", "收支记录");
		model.addObject("groupBusinessModel", groupBusinessModel);  
		model.addObject("viewPath", "groupbusiness/balancerecordlist");
		return model;
	}
	/*
	 * 集团收支列表
	 * WangChao
	 */
	@RequestMapping("balancerecordlistdo")
	public ModelAndView balancerecordlistdo(PagedGroupBussinessBalanceReq req) {	 
		
		PagedResponse<GroupBusinessBalanceRecord> resp = groupBusinessService.getGroupBusinessRecord(req); 
		ModelAndView model = new ModelAndView("groupbusiness/balancerecordlistdo");
		model.addObject("listData", resp);
		return model;
	}
	
	@RequestMapping("exportgroupbusinessbalancerecord")
	public void exportGroupBusinessBalanceRecord(int groupbusinessId, String recordType, String startDate, String endDate,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		PagedGroupBussinessBalanceReq req = new PagedGroupBussinessBalanceReq();
		req.setEndDate(endDate);
		req.setStartDate(startDate);
		req.setRecordType(recordType);
		req.setGroupBusinessId(groupbusinessId);
		req.setCurrentPage(1);
		req.setPageSize(Integer.MAX_VALUE);
		List<GroupBusinessBalanceRecord> records = groupBusinessService.getGroupBusinessBalanceRecordForExport(req);
		// 导出数据
		String filename = "集团收支记录%s";
		if (!StringUtils.isEmpty(req.getStartDate()) && !StringUtils.isEmpty(req.getEndDate())) {
			filename = String.format(filename, req.getStartDate() + "~" + req.getEndDate());
		}
		 if (records!=null&&records.size()>0) {
			 exportGroupBusinessBalanceRecord(filename, records,request, response);
		}

		return;
	}
	
	/**
	 * 导出集团收支记录excel二进制数据
	 * 
	 * @author WangChao
	 * @date 20150902
	 * @param fileName
	 * @param records
	 * @return
	 * @throws Exception
	 */
	private void exportGroupBusinessBalanceRecord(String fileName, List<GroupBusinessBalanceRecord> records,
				HttpServletRequest request,HttpServletResponse response)
			throws Exception {  
		LinkedHashMap<String,String> columnTitiles=new LinkedHashMap<String,String>();
		columnTitiles.put("交易类型", "recordtypeString");
		columnTitiles.put("任务单号/交易流水号", "relationno");
		columnTitiles.put("商铺名称", "businessname");
		columnTitiles.put("收支金额", "groupamount");
		columnTitiles.put("集团余额", "groupafterbalance");
		columnTitiles.put("状态", "statusString");
		columnTitiles.put("交易日期", "operatetime");
		columnTitiles.put("操作人", "operator");
		columnTitiles.put("备注", "remark");
		ExcelUtils.export2Excel(fileName,"集团收支记录",columnTitiles,records,request,response);
	}
	
	@RequestMapping("rechargedetail")
	@ResponseBody
	public GroupBusinessRecharge rechargedetail(String orderNo) {  
			return groupBusinessService.getRechargeDetail(orderNo); 
	}
	
}
