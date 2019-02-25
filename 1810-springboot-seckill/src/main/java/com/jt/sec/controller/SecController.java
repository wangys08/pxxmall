package com.jt.sec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.sec.service.SecService;
import com.jt.set.pojo.Seckill;

@Controller
public class SecController {
	//查询所有seckill表格数据返回给前台
	@Autowired
	private SecService secService;
	@RequestMapping("seckill/list")
	@ResponseBody
	public List<Seckill> querySecAll(){
		return secService.queryAll();
	}
	
	@RequestMapping("detail/{seckillId}")
	@ResponseBody
	public Seckill queryOne(@PathVariable 
			Long seckillId){
		return secService.queryOne(seckillId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
