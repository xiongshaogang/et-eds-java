package com.edaisong.entity.req;

import javax.xml.bind.annotation.XmlRootElement;

import com.edaisong.entity.common.RequestBase;
@XmlRootElement(name = "testServiceReq")  
public class TestServiceReq extends RequestBase{
private	int recordType;
public int getRecordType() {
	return recordType;
}
public void setRecordType(int recordType) {
	this.recordType = recordType;
}
public String getOperateTime() {
	return operateTime;
}
public void setOperateTime(String operateTime) {
	this.operateTime = operateTime;
}
private String operateTime;

}
