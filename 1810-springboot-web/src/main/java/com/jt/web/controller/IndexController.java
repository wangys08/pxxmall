package com.jt.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	/*
	 * 前台首页默认地址
	 */
	@RequestMapping("/")
	public String goIndex(){
		return "index";
	}
	/*
	 * 后台商品的管理页面
	 */
	@RequestMapping("page/{pageName}")
	public String goManagePage(@PathVariable String pageName){
		return pageName;
		//首页登录地址，注册地址，后台新增商品，修改商品，查询商品全部页面的转向
	}
}






