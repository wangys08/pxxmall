package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HiController {
	//注入restTemplate完成服务的调用
	@Autowired
	private RestTemplate template;
	@RequestMapping("hi")
	public String sayHi(String name){
		//ribbon返回 hello name 不具备说hello的能力
		//请求传递到服务之后,要求service-hi的服务提供者帮助
		//ribbon完成sayHi的方法
		//想办法,调用servie-hi完成本次客户端响应
		//利用template对象访问ekurea注册中心的service-hi服务
		String result=template.getForObject("http://service-hi/hi?name="+name, String.class);
		//url:访问服务的地址
		//resposeType:封装返回数据类型 
		//result hello name i am 8091/8092
		/*ResponseEntity<String> response = template.getForEntity("http://service-hi/hi?name="+name, String.class);
		response.getBody();*/
		
		return "RIBBON:"+result;
		
	}
}
