package com.edaisong.admin.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;







import com.edaisong.api.service.inter.IPublicProvinceCityService;
import com.edaisong.entity.domain.*;
import com.edaisong.entity.resp.ModifyOpenCityResp;

@Controller
@RequestMapping("opencitymanager")
public class OpenCityManagerController {
	
	/**
	 * @author CaoHeYang
	 */
	@Autowired
    private IPublicProvinceCityService publicProvinceCityService;
	
	@RequestMapping("list")
	public ModelAndView index(String cityname,HttpServletRequest request,HttpServletResponse response) {
		
		List<OpenCityModel> citys=new ArrayList<OpenCityModel>();
	    if (cityname!=null&&!cityname.isEmpty()) {
		   citys=  publicProvinceCityService.getOpenCityList(cityname);
		}
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理员");
		model.addObject("currenttitle", "开通城市管理");
		model.addObject("listData",citys);
		model.addObject("cityname",cityname);
		model.addObject("viewPath", "opencitymanager/opencitymanager");
		model.addObject("listData", citys);
		return model;
	}
	
	/**
	 * 修改绑定城市   20150721
	 * @author CaoHeYang
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("modifyopencity")
	@ResponseBody
	 public ModifyOpenCityResp ModifyOpenCity(HttpServletRequest request,HttpServletResponse response)
     {
		String openCityCodeList=request.getParameter("openCityCodeList"); //开放城市
		String closeCityCodeList=request.getParameter("closeCityCodeList"); //关闭城市

		ModifyOpenCityResp resp= publicProvinceCityService.ModifyOpenCityByCode(openCityCodeList,closeCityCodeList);
        return resp;
     }
}