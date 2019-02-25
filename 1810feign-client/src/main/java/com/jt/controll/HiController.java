package com.jt.controll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.service.HiService;

@Controller
public class HiController {
	@Autowired
	private HiService hiService;
	
	@RequestMapping("hi")
	@ResponseBody
	public String sayHi(String name){
		String result=hiService.sayHi(name);
		return "FEIGN:"+result;
	}
	
	
	
	
	
	
	
}
