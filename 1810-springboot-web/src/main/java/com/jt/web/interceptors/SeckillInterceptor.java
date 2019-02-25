package com.jt.web.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jt.common.service.HttpClientService;
import com.jt.common.util.CookieUtils;
import com.jt.common.util.ObjectUtil;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;

/**
 * 实现购物车拦截器功能
 * 判断所有请求中是否携带了session的数据
 * userId
 * 有则放行
 * 无重定向到登录页面
 * @author Administrator
 *
 */
public class SeckillInterceptor implements HandlerInterceptor{
	
	/*
	 * 完成判断登录的过程
	 */
	
	@Autowired
	private HttpClientService client;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//获取cookie的值
		String ticket=CookieUtils.getCookieValue(request, "JT_TICKET");
		if(StringUtils.isNotEmpty(ticket)){
			//ticket非空,证明登录过
			//跨域访问sso系统 
			String url="http://sso.jt.com/user/query/"+ticket;
			String jsonData=client.doGet(url);
			//即系sysresult对象
			SysResult result=
			ObjectUtil.mapper.readValue(jsonData, SysResult.class);
			String userJson=(String)result.getData();//userJosn "" "正确数据"
			//"" "null","{"":"","":""}"
			if(StringUtils.isNotEmpty(userJson)&&!("null".equals(userJson))){
				//说明登录每超时10分钟以上
				//解析字符串获取后续内容
				User user=ObjectUtil.mapper.readValue(userJson, User.class);
				request.setAttribute("user", user);
				return true;
			}
		}
		//不满足2个if的条件,说明没登录,转向登录页面,不放行
		response.sendRedirect("/page/login");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
















