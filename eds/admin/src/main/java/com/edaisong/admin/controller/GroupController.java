package com.edaisong.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IGroupApiConfigService;
import com.edaisong.api.service.inter.IGroupService;
import com.edaisong.core.enums.returnenums.GroupUpdateStatusReturnEnum;
import com.edaisong.entity.Group;
import com.edaisong.entity.GroupApiConfig;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.domain.GroupApiConfigModel;
import com.edaisong.entity.domain.GroupModel;
import com.edaisong.entity.req.GroupReq;
import com.edaisong.entity.req.PagedGroupReq;


@Controller
@RequestMapping("group")
public class GroupController {
	 @Autowired
	 private IGroupService  groupService;
	 @Autowired
	 private IGroupApiConfigService groupApiConfigService;
	 
	 /**
	  * 第三方平台管理
	  * @update caoheyang
	  * @date 20160115
	  * @param request
	  * @param response
	  * @return
	  */
	@RequestMapping("list")
	public ModelAndView list(){
		GroupReq req=new GroupReq();	
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理员");
		model.addObject("currenttitle", "第三方平台管理");
		model.addObject("viewPath", "group/list");
		return model;		
	}
	
	/**
	 * 查询第三方集团  异步
	 * @author CaoHeYang
	 * @date 20160118
	 * @param req
	 * @return
	 */
	@RequestMapping("selectlist")
	@ResponseBody
	public ModelAndView selectlist(PagedGroupReq req){
		PagedResponse<GroupApiConfigModel> resultList =groupService.getGroupListByPage(req);				
		ModelAndView model = new ModelAndView("group/grouplistdo");
		model.addObject("listData", resultList);
		return model;		
	}	
	
	/**
	 * 
	 * @param group
	 * @return
	 */
	@RequestMapping(value="addgroup",method = RequestMethod.POST)		
	@ResponseBody
	public String addgroup(@ModelAttribute("group") Group group){		
		Group record=group;		
		record.setCreatename("admin");			
		groupService.add(record);	
		return "ok";  
	}
	
	@RequestMapping("updategroup")
	@ResponseBody
	public String updategroup(@ModelAttribute("group") Group group){
		groupService.update(group);			
		return "ok";  
	}
	
    /**
     * 更新启用状态
     * @author CaoHeYang
     * @date 20160118
     * @param record
     * @return
     */
	@RequestMapping(value="updatestatus",method= {RequestMethod.POST})
	@ResponseBody
	public ResponseBase updatestatus(Group record ){
		if (record.getId() == 0)
        {
			return new ResponseBase().setMessage(GroupUpdateStatusReturnEnum.GroupIdError.desc())
					.setResponseCode(GroupUpdateStatusReturnEnum.GroupIdError.value());
        }
        Boolean res = groupService.updateGroupStatus(record);	
		return res?   new ResponseBase(): new ResponseBase().setMessage(GroupUpdateStatusReturnEnum.Error.desc())
				.setResponseCode(GroupUpdateStatusReturnEnum.Error.value());
	}	

	@RequestMapping("addgroupapiconfig")
	@ResponseBody
	public String addgroupapiconfig(@ModelAttribute("groupapiconfig") GroupApiConfig groupapiconfig){
		GroupApiConfig record=groupapiconfig;
		record.setAppsecret("");
		groupApiConfigService.add(record);	
		return "ok";  
	}
	
}
