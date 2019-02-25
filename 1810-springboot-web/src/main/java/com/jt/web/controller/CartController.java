package com.jt.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.web.pojo.Cart;
import com.jt.web.pojo.User;
import com.jt.web.service.CartService;
@Controller
public class CartController {
	@Autowired
	private CartService cartService;
	//查寻我的购物车
	@RequestMapping("cart/mycart")
	public String queryMyCart(Model model,HttpServletRequest request){
		User user=(User)request.getAttribute("user");
		String userId=user.getUserId();
		//登录状态
		List<Cart> cList=
		cartService.queryMyCart(userId);
		//携带数据到页面
		model.addAttribute("cartList",cList);
		return "cart";
	}
	@RequestMapping("cart/addCart/{productId}/{num}")
	public String saveCart(@PathVariable String productId,
			@PathVariable Integer num,HttpServletRequest request) throws Exception{
		User user=(User)request.getAttribute("user");
		String userId=user.getUserId();
		cartService.saveCart(userId,productId,num);
		return "redirect:/cart/mycart";
	}
	@RequestMapping("cart/editCart/{productId}/{num}")
	public String updateNum(@PathVariable String productId,
			@PathVariable Integer num,HttpServletRequest request) throws Exception{
		User user=(User)request.getAttribute("user");
		String userId=user.getUserId();
		cartService.updateNum(userId,productId,num);
		return "redirect:/cart/mycart";
	}
	@RequestMapping("cart/deleteCart/{productId}")
	public String deleteCart(@PathVariable String productId,
			HttpServletRequest request) throws Exception{
		User user=(User)request.getAttribute("user");
		String userId=user.getUserId();
		cartService.deleteCart(productId,userId);
		return "redirect:/cart/mycart";
	}
	
}












































