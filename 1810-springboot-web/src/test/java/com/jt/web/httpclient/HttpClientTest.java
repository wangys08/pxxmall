package com.jt.web.httpclient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientTest {
	@Test
	public void test() throws Exception{
		//创建一个发起http请求的对象
		CloseableHttpClient client = 
				HttpClients.createDefault();
		//准备一个请求对象,需要访问的url地址
		String url="http://www.jd.com";
		//根据请求方式 get post 封装请求对象request
		HttpGet request=new HttpGet(url);
		//发起请求,访问京东首页,接收网站服务器的响应对象
		CloseableHttpResponse response = client.execute(request);
		//获取响应体内容文本
		HttpEntity entity = response.getEntity();
		String body=EntityUtils.toString(entity);
		System.out.println(body);
	}
	@Test
	public void test1() throws Exception{
		//创建一个发起http请求的对象
		CloseableHttpClient client = 
				HttpClients.createDefault();
		//准备一个请求对象,需要访问的url地址
		String url="http://manage.jt.com/product/page?currentPage=1&rows=5";
		//根据请求方式 get post 封装请求对象request
		HttpGet request=new HttpGet(url);
		//发起请求,访问京东首页,接收网站服务器的响应对象
		CloseableHttpResponse response = client.execute(request);
		//获取响应体内容文本
		HttpEntity entity = response.getEntity();
		String body=EntityUtils.toString(entity);
		System.out.println(body);
	}
}
