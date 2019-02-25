package com.jt.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.web.pojo.Cart;
import com.jt.web.pojo.Order;
import com.jt.web.pojo.User;
import com.jt.web.service.CartService;
import com.jt.web.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	//查询我的订单
	@RequestMapping("/order/list")
	public String queryOrderList(Model model,HttpServletRequest request){
		String userId=((User)request.getAttribute("user")).getUserId();
		List<Order> orderList=orderService.
		queryOrders(userId);
		model.addAttribute("orderList", orderList);
		return "order_list";
	}
	@Autowired
	private CartService cartService;
	@RequestMapping("/order/order-cart")
	public String goOrderAdd(Model model,HttpServletRequest request){
		String userId=((User)request.getAttribute("user")).getUserId();
		List<Cart> cartList=cartService.queryMyCart(userId);
		model.addAttribute("cartList",cartList);
		return "order_add";
	}
	@RequestMapping("/order/addOrder")
	public String addOrder(Order order,HttpServletRequest request) throws Exception{
		//获取userId
		String userId=((User)request.getAttribute("user")).getUserId();
		//调用业务层方法，将数据补充，存储
		order.setUserId(userId);
		orderService.addOrder(order);
		//忽略判断成功，可以使用数据库返回值1/0判断是否成功
		//重定向到查询订单
		return "redirect:/order/list";
	}
	
	
	 
	/*//新增订单第一步，查询用户购物车，转向订单收集信息的页面order-add.jsp
	@RequestMapping("/order/order-cart")
	public String goOrderAdd(Model model,HttpSession session){
		String userId=(String) session.getAttribute("userId");
		List<Cart> cartList=orderService.queryCart(userId);
		model.addAttribute("cartList",cartList);
		return "order_add";
	}
	
	//新增订单,按照接口文件内容
	@RequestMapping("/order/addOrder")
	public String addOrder(Order order,HttpSession session){
		//获取userId
		String userId=(String) session.getAttribute("userId");
		//调用业务层方法，将数据补充，存储
		order.setUserId(userId);
		orderService.addOrder(order);
		//忽略判断成功，可以使用数据库返回值1/0判断是否成功
		//重定向到查询订单
		return "redirect:/order/list";
	}*/
}	





















