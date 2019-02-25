package com.jt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.Page;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Product;
import com.jt.web.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	@RequestMapping("product/page")
	public String productQueryList(Integer currentPage,
			Integer rows,Model model) throws Exception{
		//注入一个service，返回一个携带了三个属性的page对象
		//page这个类是一个过渡的数据封装类
		Page page=productService.
				queryPList(currentPage,rows);
		model.addAttribute("page", page);
		return "product_list";
	}
	
	
	@RequestMapping("product/findProductById/{productId}")
	public String queryPById(@PathVariable String productId,
			Model model) throws Exception{
		Product product=productService.queryPById(productId);
		model.addAttribute("product", product);
		return "product_info";
	}
	

	@RequestMapping("product/save")
	@ResponseBody//作用是,当前方法不做controller方法返回
	//后续的框架处理,只将返回数据放到响应体中
	//String --- 响应体中存放字符串 nihao
	//Object --- 响应体中存放当前对象的json字符串
	public SysResult saveProduct(Product product){
		//sysResult中可以看到"status","msg","data"
		SysResult result=new SysResult();
		int success=productService.saveProduct(product);//商品对象缺少了id
		if(success==1){//成功
			result.setStatus(200);
			return result;
		}else{//新增不成功
			result.setStatus(201);
			return result;
		}
	}
	
	//后台准备的商品分页查询
	@RequestMapping("product/query")
	@ResponseBody
	public EasyUIResult queryProduct(Integer page,Integer rows) throws Exception{
		EasyUIResult result=
				productService.queryProduct(page,rows);
		return result;
	}
	
	/*//后台商品的修改
	@RequestMapping("product/update")
	@ResponseBody
	public SysResult updatProduct(Product product){
		SysResult result=new SysResult();
		int success=productService.updatProduct(product);
		if(success==1){//成功
			result.setStatus(200);
			return result;
		}else{//新增不成功
			result.setStatus(201);
			return result;
		}
		
	}*/
}


































