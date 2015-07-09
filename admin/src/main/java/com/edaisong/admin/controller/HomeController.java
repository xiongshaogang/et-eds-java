package com.edaisong.admin.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("home")
public class HomeController {
	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		//throw new Exception("这是全局异常啊");
		return new ModelAndView("home");
	}

	@RequestMapping("test")
	public ModelAndView test(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "骑士管理");
		model.addObject("currenttitle", "骑士提现");
		model.addObject("viewPath", "order/list");
		return model;
	}
}
