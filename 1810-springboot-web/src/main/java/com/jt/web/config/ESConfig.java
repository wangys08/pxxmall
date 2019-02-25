package com.jt.web.config;

import java.net.InetAddress;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ESConfig {
	
	@Value("${cluster-name}")
	private String name;
	@Value("${cluster-nodes}")
	private String nodes;
	
	//初始化方法
	@Bean
	public TransportClient init(){
		Settings setting=Settings.builder()
				.put("cluster.name", name).build();
		//配置对象Setting,测试使用默认empty
		TransportClient client=
				new PreBuiltTransportClient(setting);
		try{//连接指定集群名称的集群 elasticsearch
			//如果是集群模式,需要将所有的主节点信息传递添加
			String[] hostAndPort=nodes.split(",");
			for (String node : hostAndPort) {
				String host=node.split(":")[0];
				int port=Integer.parseInt(node.split(":")[1]);
				client.addTransportAddress(
						new InetSocketTransportAddress(
						InetAddress.getByName(host),port));
			}
			}catch(Exception e){
			e.printStackTrace();
			
		}
		return client;
		
		
}
}
