package com.jt.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.jt.common.service.HttpClientService;
import com.jt.common.util.ObjectUtil;
import com.jt.web.pojo.Seckill;

@Service	
public class SecService {
	@Autowired
	private HttpClientService client;
	public List<Seckill> queryAll() throws Exception {
		//按照接口文件,发起项seckill系统的访问,获取结果json
		String url="http://sec.jt.com/seckill/list";
		String dataJson=client.doGet(url);
		//利用JsonNode转化json的list结构为list对象
		JsonNode data = ObjectUtil.mapper.readTree(dataJson);
		List<Seckill> secList=null;
		if(data.isArray()&&data.size()>0){
			secList=ObjectUtil.mapper.readValue
					(data.traverse(), ObjectUtil.mapper.getTypeFactory()
							.constructCollectionType
							(List.class, Seckill.class));
		}
		
		return secList;
	}
	public Seckill queryOne(Long seckillId) throws Exception {
		//对照接口实现访问
		String url="http://sec.jt.com/detail/"+seckillId;
		String secJson=client.doGet(url);
		
		return ObjectUtil.mapper.readValue(secJson, Seckill.class);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
