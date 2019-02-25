package com.jt.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.util.CookieUtils;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;
import com.jt.web.service.UserService;


@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("user_ajax/checkUserName")
	@ResponseBody
	public SysResult check(String userName) throws Exception{
		int exists=userService.check(userName);//exists=1/0
		SysResult result=new SysResult();
		result.setStatus(exists);
		return result;
	}
	
	//注册提交
	@RequestMapping("user_ajax/regist")
	@ResponseBody
	public SysResult doRegist(User user) throws Exception{
		//service业务层处理缺少的数据
		SysResult result=new SysResult();
		int success=userService.doRegist(user);
		if(success==1){
			//新建对象
			result.setStatus(1);
			result.setMsg("注册成功");
			return result;
		}else{
			result.setStatus(0);
			result.setMsg("注册失败");
			return result;
		}
	}
	
	//登录流程
	@RequestMapping("user_ajax/login")
	@ResponseBody
	public SysResult doLogin(User user,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		SysResult result=new SysResult();
		//所有的逻辑都交给service处理
		String ticket=userService.doLogin(user);
		//判断,成功200与失败的逻辑201
		if(StringUtils.isNotEmpty(ticket)){
			//说明登录成功了
			//响应中添加cookie,将ticket放到一个"JT_TICKET"
			CookieUtils.setCookie(request, response, 
					"JT_TICKET", ticket);
			result.setStatus(1);
			return result;
		}else{//失败
			result.setStatus(201);
			return result;
		}
	}
	/*
	@RequestMapping("/user_ajax/logout")
	public String doLogout(HttpSession session){
		session.removeAttribute("userName");
		session.removeAttribute("userId");
		return "index";
	}*/
}





















