package com.jt.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.web.pojo.Seckill;
import com.jt.web.service.SecService;

@Controller
public class SecController {
	@Autowired
	private SecService secService;
	//查询所有秒杀商品
	@RequestMapping("seckill/start")
	public String queryAllSec(Model model) throws Exception{
		List<Seckill> secList=secService.queryAll();
		model.addAttribute("list", secList);
		return "list";
	}
	@RequestMapping("seckill/{seckillId}/detail")
	public String queryOne(@PathVariable Long 
			seckillId,Model model) throws Exception{
		Seckill sec=secService.queryOne(seckillId);
		model.addAttribute("seckill", sec);
		return "detail";//渲染到detail.jsp
	}
	@RequestMapping("/seckill/time/now")
	@ResponseBody
	public Date nowTime(){
		return new Date();
	}
}
























