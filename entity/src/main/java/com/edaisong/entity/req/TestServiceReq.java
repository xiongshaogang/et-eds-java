package com.edaisong.entity.req;

import com.edaisong.entity.common.RequestBase;

public class TestServiceReq extends RequestBase{
private	int RecordType;
private String OperateTime;
public int getRecordType() {
	return RecordType;
}
public void setRecordType(int recordType) {
	RecordType = recordType;
}
public String getOperateTime() {
	return OperateTime;
}
public void setOperateTime(String operateTime) {
	OperateTime = operateTime;
}
}
