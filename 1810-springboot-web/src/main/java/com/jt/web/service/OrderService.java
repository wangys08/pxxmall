package com.jt.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.jt.common.service.HttpClientService;
import com.jt.common.util.ObjectUtil;
import com.jt.web.pojo.Order;

@Service
public class OrderService {
	@Autowired
	private HttpClientService client;
	public List<Order> queryOrders(String userId) {
		String url="http://order.jt.com/order/query/"+userId;
		try{
			String dataJson=client.doGet(url);
			//转化成jsonNode
			JsonNode data=ObjectUtil.mapper.readTree(dataJson);
			List<Order> orderList=null;
			if(data.isArray()&&data.size()>0){
				orderList=ObjectUtil.mapper.readValue(
						data.traverse(), 
						ObjectUtil.mapper.getTypeFactory().constructCollectionType
						(List.class, Order.class));	
			}
			return orderList;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}
	public void addOrder(Order order) throws Exception {
		String url="http://order.jt.com/order/save";
		//按照order与web接口定义,需要传递一个orderJson的字符串
		//client的doPostJson
		String orderJson=ObjectUtil.mapper.writeValueAsString(order);
		client.doPostJson(url, orderJson);
	}

}
