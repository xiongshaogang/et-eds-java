package com.edaisong.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BusinessBalanceRecord {

	private String id;
	private String BusinessId;
	private BigDecimal Amount;
	private int Status;
	private BigDecimal Balance;
	private int RecordType;
	private String Operator;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusinessId() {
		return BusinessId;
	}

	public void setBusinessId(String businessId) {
		BusinessId = businessId;
	}

	public BigDecimal getAmount() {
		return Amount;
	}

	public void setAmount(BigDecimal amount) {
		Amount = amount;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public BigDecimal getBalance() {
		return Balance;
	}

	public void setBalance(BigDecimal balance) {
		Balance = balance;
	}

	public int getRecordType() {
		return RecordType;
	}

	public void setRecordType(int recordType) {
		RecordType = recordType;
	}

	public String getOperator() {
		return Operator;
	}

	public void setOperator(String operator) {
		Operator = operator;
	}

	public Date getOperateTime() {
		return OperateTime;
	}

	public void setOperateTime(Date operateTime) {
		OperateTime = operateTime;
	}

	public String getWithwardId() {
		return WithwardId;
	}

	public void setWithwardId(String withwardId) {
		WithwardId = withwardId;
	}

	public String getRelationNo() {
		return RelationNo;
	}

	public void setRelationNo(String relationNo) {
		RelationNo = relationNo;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	private Date OperateTime;
	private String WithwardId;
	private String RelationNo;
	private String Remark;

}
