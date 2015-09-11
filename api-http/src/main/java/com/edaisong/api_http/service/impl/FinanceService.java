package com.edaisong.api_http.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IBusinessFinanceAccountService;
import com.edaisong.api.service.inter.IClienterFinanceAccountService;
import com.edaisong.api.service.inter.IClienterService;
import com.edaisong.api_http.entity.ResultModel;
import com.edaisong.api_http.service.inter.IFinanceService;
import com.edaisong.core.enums.CardBindC;
import com.edaisong.core.enums.CardModifyC;
import com.edaisong.core.enums.PayType;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.StringUtils;
import com.edaisong.entity.Clienter;
import com.edaisong.entity.ClienterFinanceAccount;
import com.edaisong.entity.req.CardBindAlipayReq;
import com.edaisong.entity.req.CardModifyAlipayReq;

/**
 *
 * @author  pengyi 
 * @date 2015年9月10日 下午4:22:56
 * @version 1.0
 * @parameter
 * @since
 */
@Service
public class FinanceService implements IFinanceService{

	@Autowired
	private IClienterFinanceAccountService clienterFinanceAccountService;
	@Autowired
	private IClienterService clienterService;
	/**
	 * c端绑定支付宝 add by pengyi 20150911
	 */
	@Override
	public ResultModel<Object> cardBindAlipayC(CardBindAlipayReq req) {
		CardBindC checkRet = checkCardBindAlipayC(req);
		ResultModel<Object> result = new ResultModel<Object>();
		if(checkRet != CardBindC.Success){
			result.setMessage(checkRet.desc());
			result.setStatus(checkRet.value());
			return result;
		}
		Clienter clienter = clienterService.selectByPrimaryKey(req.getUserId());
		if(clienter == null) {
			result.setMessage(CardBindC.NoClienter.desc());
			result.setStatus(CardBindC.NoClienter.value());
			return result;
		}
		Date now = new Date();
		ClienterFinanceAccount record = new ClienterFinanceAccount();
		record.setAccountno(ParseHelper.encrypt(req.getAccount()));
		record.setAccounttype((short)PayType.ZhiFuBao.value());
		record.setBelongtype((short)0);
		record.setClienterid(req.getUserId());
		record.setCreateby(req.getCreateBy());
		record.setCreatetime(now);
		record.setIsenable(true);
		clienterFinanceAccountService.insertSelective(record);
		result.setMessage(CardBindC.Success.desc());
		result.setStatus(CardBindC.Success.value());
		return result;
	}

	@Override
	public ResultModel<Object> cardModifyAlipayC(CardModifyAlipayReq req) {
		CardModifyC checkRet = checkCardModifyAlipayC(req);
		ResultModel<Object> result = new ResultModel<Object>();
		if(checkRet != CardModifyC.Success){
			result.setMessage(checkRet.desc());
			result.setStatus(checkRet.value());
			return result;
		}
		Clienter clienter = clienterService.selectByPrimaryKey(req.getUserId());
		if(clienter == null) {
			result.setMessage(CardModifyC.NoClienter.desc());
			result.setStatus(CardModifyC.NoClienter.value());
			return result;
		}
		Date now = new Date();
		ClienterFinanceAccount record = clienterFinanceAccountService.selectByPrimaryKey(req.getId());
		if(record == null){
			result.setMessage(CardModifyC.NoAccount.desc());
			result.setStatus(CardModifyC.NoAccount.value());
			return result;
		}
		String encryptAccount = ParseHelper.encrypt(req.getAccount());
		//check data has modify or not
		if(record.getAccountno().equals(encryptAccount) && record.getClienterid()==req.getUserId() &&
				record.getTruename().equals(req.getTrueName())){
			result.setMessage(CardModifyC.NoModify.desc());
			result.setStatus(CardModifyC.NoModify.value());
			return result;
		}
		//record.setId(req.getId());
		record.setAccountno(ParseHelper.encrypt(req.getAccount()));
		record.setAccounttype((short)PayType.ZhiFuBao.value());
		record.setBelongtype((short)0);
		record.setClienterid(req.getUserId());
		record.setUpdateby(req.getCreateBy());
		record.setUpdatetime(now);
		record.setIsenable(true);
		clienterFinanceAccountService.updateByPrimaryKeySelective(record);
		result.setMessage(CardModifyC.Success.desc());
		result.setStatus(CardModifyC.Success.value());
		return result;
	}

	/**
	 * 骑士绑定支付宝功能有效性验证
	 * @author pengyi
	 * @date 2015年9月11日 上午10:44:33
	 * @version 1.0
	 * @param req
	 * @return
	 */
	private CardBindC checkCardBindAlipayC(CardBindAlipayReq req){
		if(StringUtils.isEmpty(req.getTrueName())){
			return CardBindC.NameError;
		}
		if(StringUtils.isEmpty(req.getCreateBy())){
			return CardBindC.NoCreateBy;
		}
		if(StringUtils.isEmpty(req.getAccount())){
			return CardBindC.AccountNotSame;
		}
		if(req.getAccount().equals(req.getAccount2())){
			return CardBindC.AccountNotSame;
		}
		if(clienterFinanceAccountService.getCountByClientId(req.getUserId(), PayType.ZhiFuBao.value()) > 0){
			return CardBindC.Exists;
		}
		return CardBindC.Success;
	}
	
	/**
	 * 骑士修改绑定支付宝功能有效性验证
	 * @author pengyi
	 * @date 2015年9月11日 上午10:30:51
	 * @version 1.0
	 * @param req
	 * @return
	 */
	private CardModifyC checkCardModifyAlipayC(CardBindAlipayReq req){
		if(StringUtils.isEmpty(req.getTrueName())){
			return CardModifyC.NameError;
		}
		if(StringUtils.isEmpty(req.getCreateBy())){
			return CardModifyC.NoCreateBy;
		}
		if(StringUtils.isEmpty(req.getAccount())){
			return CardModifyC.AccountNotSame;
		}
		if(req.getAccount().equals(req.getAccount2())){
			return CardModifyC.AccountNotSame;
		}
		if(clienterFinanceAccountService.getCountByClientId(req.getUserId(), PayType.ZhiFuBao.value()) <= 0){
			return CardModifyC.NotExists;
		}
		return CardModifyC.Success;
	}
}
