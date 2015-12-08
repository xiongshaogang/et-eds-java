package com.edaisong.api_http.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 

import com.edaisong.api.service.inter.IBusinessClienterRelationService;
import com.edaisong.api.service.inter.IClienterService;
import com.edaisong.api_http.service.inter.IClienterHttpService;
import com.edaisong.core.enums.OrderGrabEnum;
import com.edaisong.core.enums.VehicleEnum;
import com.edaisong.core.enums.returnenums.HttpReturnRnums;
import com.edaisong.core.enums.returnenums.RemoveRelationReturnEnum;
import com.edaisong.entity.Clienter;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.domain.MyOrderHadFinishCModel;
import com.edaisong.entity.domain.QueryOrder;
import com.edaisong.entity.req.ClienterBindOptionReq;
import com.edaisong.entity.req.HadFinishOrderReq;
import com.edaisong.entity.req.ModifyVehicleReq;
import com.edaisong.entity.req.MyBusinessReq;
import com.edaisong.entity.req.UserStatusReq;
import com.edaisong.entity.resp.ClienterUserStatusResp;
import com.edaisong.entity.resp.HadFinishOrderResp;
import com.edaisong.entity.resp.MyBusinessResp;
import com.edaisong.entity.resp.MyOrderGrabCResp;
@Service
public class ClienterHttpService implements IClienterHttpService {

	@Autowired
	private IBusinessClienterRelationService businessClienterRelationService;
	@Autowired
	private IClienterService iClienterService;
	/*
	 * 获取我的商户
	 * wangchao
	 */
	@Override
	public HttpResultModel<MyBusinessResp> getMyBusiness(
			MyBusinessReq myBusinessReq) {
		HttpResultModel<MyBusinessResp> result = new HttpResultModel<MyBusinessResp>();
		
		if(myBusinessReq.getClienterId()<=0){
			result.setStatus(RemoveRelationReturnEnum.ClienterIdError.value());
			result.setMessage(RemoveRelationReturnEnum.ClienterIdError.desc());
			return result;
		}
		result.setStatus(HttpReturnRnums.Success.value());
		result.setMessage(HttpReturnRnums.Success.desc());
		MyBusinessResp listMyBusinessResp = new MyBusinessResp();
		listMyBusinessResp = iClienterService.getMyBusiness(myBusinessReq);
		result.setResult(listMyBusinessResp);
		return result;
	}
	/*
	 * 骑士解除绑定 商户
	 * wangchao
	 */
	@Override
	public HttpResultModel<Object> unbindMyBusiness(
			ClienterBindOptionReq req) {
		HttpResultModel<Object> res = new HttpResultModel<Object>(); 
		res.setStatus(HttpReturnRnums.Success.value());
		res.setMessage(HttpReturnRnums.Success.desc());
		 if (req.getBusinessId() <= 0) {
				return res.setStatus(RemoveRelationReturnEnum.BusinessIdError.value()).setMessage(RemoveRelationReturnEnum.BusinessIdError.desc());
		 }
		 if (req.getClienterId() <= 0) {
				return res.setStatus(RemoveRelationReturnEnum.ClienterIdError.value()).setMessage(RemoveRelationReturnEnum.ClienterIdError.desc());
	     }
		 if (req.getRemark()==null||req.getRemark().isEmpty()||req.getRemark().length()<5||req.getRemark().length()>100) {
				return res.setStatus(RemoveRelationReturnEnum.RemarkError.value()).setMessage(RemoveRelationReturnEnum.RemarkError.desc());
	     }
		 if(req.getOptName() == null || req.getOptName().isEmpty()){
			 return res.setStatus(RemoveRelationReturnEnum.RemarkError.value()).setMessage(RemoveRelationReturnEnum.RemarkError.desc());
		 }
		req.setOptId(req.getClienterId());
        req.setInsertTime(new Date());  
        req.setIsBind(0);  //解除绑定
        req.setAuditStatus(2);
        boolean b= businessClienterRelationService.modifyClienterBind(req);
        if(!b){
        	res.setStatus(HttpReturnRnums.Fail.value());
    		res.setMessage(HttpReturnRnums.Fail.desc());
        }
	    return res; 
	}
	@Override
	public HttpResultModel<Object> getUserStatus(UserStatusReq req) {
		HttpResultModel<Object> res = new HttpResultModel<Object>(); 
		res.setStatus(HttpReturnRnums.Success.value());
		res.setMessage(HttpReturnRnums.Success.desc());
		ClienterUserStatusResp clienter = iClienterService.getUserStatus(req);
		clienter.setVehicleId(VehicleEnum.getEnum(clienter.getVehicleName()).value());
		res.setResult(clienter);		
		return res;
	}
	@Override
	public HttpResultModel<Object> modifyVehicle(ModifyVehicleReq req) {
		HttpResultModel<Object> res = new HttpResultModel<Object>(); 
		res.setStatus(HttpReturnRnums.Success.value());
		res.setMessage(HttpReturnRnums.Success.desc());
		req.setVehicleName(VehicleEnum.getEnum(req.getVehicleId()).desc());
		int modifyResult = iClienterService.modifyVehicle(req);
		if(modifyResult <=0 ){
			res.setStatus(HttpReturnRnums.Fail.value());
			res.setMessage(HttpReturnRnums.Fail.desc());
		}
		return res;
	} 
}
