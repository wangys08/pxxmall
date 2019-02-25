package com.jt.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//feignClient注解实现接口类的底层方法,
//注解的参数value表示当前接口所有方法调用的服务名称
//restTemplate.getForObject("http://service-hi/hi?name=name" String)
@FeignClient("service-hi")
public interface HiService {
	//为了让底层template对象正确拼接方法的参数值,需要springmvc的注解帮忙
	@RequestMapping(value="hi",method=RequestMethod.GET)
	String sayHi(@RequestParam(value="name")String name);
	
}
