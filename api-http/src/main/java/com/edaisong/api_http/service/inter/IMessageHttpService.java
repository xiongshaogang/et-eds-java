package com.edaisong.api_http.service.inter;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.edaisong.api_http.entity.ResultModel;
import com.edaisong.entity.common.RecordType;
import com.edaisong.entity.resp.MessageResp;

/**
 * 通用模块 
 * @author CaoHeYang
 * @date 20150909
 */
@Path("/message")
@Consumes("application/json")//当前方法接收的参数类型
@Produces("application/json")//当前类的所有方法都返回json格式的数据
public interface IMessageHttpService {
	/**
	 * B端商户登陆后获取顶端未读公告
	 * @author CaoHeYang
	 * @date 20150909
	 * @param data 
	 * @return
	 */
	@POST
	@Path("/newmessageb")
	 public ResultModel<MessageResp> newMessageB(String data);
	/**
	 * C端商户登陆后获取顶端未读公告
	 * @author CaoHeYang
	 * @date 20150909
	 * @param data 
	 * @return
	 */
	@POST
	@Path("/newmessagec")
	 public ResultModel<MessageResp> newMessageC(String data);
}
