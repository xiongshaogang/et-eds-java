package com.edaisong.entity.req;


import java.math.BigDecimal;
import java.util.Date;

import com.edaisong.entity.common.PagedRequestBase;
import com.edaisong.entity.common.RequestBase;

public class ClienterBalanceRecordReq extends PagedRequestBase{
	
	public Integer getClienterId() {
		return clienterId;
	}
	public void setClienterId(Integer clienterId) {
		this.clienterId = clienterId;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	private Integer clienterId;
	private int businessId;
}

