package com.jt.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.service.HttpClientService;
import com.jt.common.vo.HttpResult;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;

@Service
public class UserService {
	@Autowired
	private HttpClientService client;
	public int check(String userName) throws Exception {
		String url="http://sso.jt.com/user/check/"+userName;
		String exists=client.doGet(url);
		return Integer.parseInt(exists);
	}
	public int doRegist(User user) throws Exception {
		//属性 除了type id 都是非空post提交给后台sso
		String url="http://sso.jt.com/user/register";
		//doPost(URL,MAP)
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("userName", user.getUserName());
		param.put("userPassword", user.getUserPassword());
		param.put("userNickname", user.getUserNickname());
		param.put("userEmail", user.getUserEmail());
		HttpResult result = client.doPost(url, param);
		String exists=result.getBody();//""1""
		return Integer.parseInt(exists);
	}
	public String doLogin(User user) throws Exception {
		//按照接口文件,实现http的请求发送 u p
		String url="http://sso.jt.com/user/login";
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("u", user.getUserName());
		param.put("p", user.getUserPassword());
		String ticket=client.doPost(url, param).getBody();
		//"" "e10dk303k3j3k,2m3lk23j"
		return ticket;
	}

}
