package com.jt.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.jt.common.service.HttpClientService;
import com.jt.common.util.ObjectUtil;
import com.jt.web.pojo.Cart;

@Service
public class CartService {
	@Autowired
	private HttpClientService client;
	public List<Cart> queryMyCart(String userId) {
		try{
			String url="http://cart.jt.com/cart/mycart/"+userId;
			String cartListJson=client.doGet(url);
			//ObjectMapper转化字符串为底层是map对象的JsonNode
			JsonNode data = ObjectUtil.mapper.readTree(cartListJson);
            //准备一个返回的类型对象List<Cart>
			List<Cart> cartList=null;
            if (data.isArray() && data.size() > 0) {
            cartList = ObjectUtil.mapper.
            readValue(data.traverse(),
            		ObjectUtil.mapper.getTypeFactory().
            		constructCollectionType(List.class, 
            				Cart.class));
            }
            return cartList;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}
	public void saveCart(String userId, String productId, Integer num) throws Exception {
		String url="http://cart.jt.com/cart/save";
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("productId", productId);
		param.put("userId", userId);
		param.put("num", num);
		client.doPost(url, param);
	}
	public void updateNum(String userId, String productId, Integer num) throws Exception {
		String url="http://cart.jt.com/cart/update/"+userId+"/"
				+productId+"/"+num;
		client.doGet(url);//0/1没有使用
		
	}
	public void deleteCart(String productId, String userId) throws Exception {
		String url="http://cart.jt.com/cart/delete/"
				+userId+"/"+productId;
		client.doGet(url);
		
	}

}
