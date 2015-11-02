package com.edaisong.api_http.service.inter;

import java.util.List;

import javax.ws.rs.Consumes; 
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.edaisong.entity.common.HttpResultModel; 
import com.edaisong.entity.req.MyGrabOrderCReq;
import com.edaisong.entity.resp.MyGrabOrderCResp;



@Path("/graborder")
@Consumes("application/json")//当前方法接收的参数类型
@Produces("application/json; charset=utf-8")//当前类的所有方法都返回json格式的数据
public interface IGrabOrderHttpService {
	
	@POST
	@Path("/getmygraborderc")
	HttpResultModel<List<MyGrabOrderCResp>> getMyGrabOrderC(MyGrabOrderCReq myGrabOrderCReq);
	
}
