package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jt.web.pojo.Product;
import com.jt.web.service.SearchService;

@Controller
public class SearchController {
	
	@Autowired
	private SearchService search;
	
	//搜索数据
	@RequestMapping("search")
	public String search(String q,
			@RequestParam(defaultValue="1") Integer page,
			Model model){
		//定义了查询的条件结构,查询productName
		List<Product> pList=search.search(q,page);
		model.addAttribute("products", pList);
		model.addAttribute("query",q);
		return "search";
	}
}

















