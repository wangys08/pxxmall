package com.jt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {
	
	//测试 访问hi请求地址,返回当前工程的端口
	@Value("${server.port}")
	private String port;
	
	@RequestMapping("hi")
	@ResponseBody
	public String sayHi(String name){
		return "hello "+name+",I am "+port;
	}
}
