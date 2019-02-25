package com.jt.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.service.HttpClientService;
import com.jt.common.util.ObjectUtil;
import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.HttpResult;
import com.jt.common.vo.Page;
import com.jt.web.pojo.Product;

@Service
public class ProductService {
	@Autowired
	private HttpClientService client;
	//httpClient访问后台工程获取数据
	public Page queryPList(Integer currentPage, Integer rows) throws Exception{
		
		//查询分页数据
		//前台service只需要将page rows传递给后台,实现封装page对象
		/*CloseableHttpClient client = HttpClients.createDefault();
		String url="http://manage.jt.com/product/page?currentPage="+
		currentPage+"&rows="+rows;
		//生成get的request对象
		HttpGet request=new HttpGet(url);
		CloseableHttpResponse response = client.execute(request);
		String pageJson=EntityUtils.
				toString(response.getEntity());
		//pageJson转化成page对象
		Page page=ObjectUtil.mapper.readValue(pageJson, Page.class);
		*/
		String url="http://manage.jt.com/product/page?currentPage="+
				currentPage+"&rows="+rows;
		//doGet封装了把响应体转化成String的过程
		String pageJson=client.doGet(url);
		Page page=ObjectUtil.mapper.readValue(pageJson, Page.class);
		return page;
	}

	public Product queryPById(String productId) throws Exception {
		String url="http://manage.jt.com/product/findProductById/"+productId;
		//调用get请求,获取后台返回的ProductJson字符串
		String productJson = client.doGet(url);
		Product product=ObjectUtil.mapper.
				readValue(productJson, Product.class);
		return product;
	}

	public int saveProduct(Product product) {
		//将参数Product对象发送给后台工程进行存储工作
		//如果成功返回的是1,如果存储失败返回0
		//调用doPost,将product数据封装到map对象中,后台接收参数,Product接收
		//手动拼接参数
		try{
			String url="http://manage.jt.com/product/save";
			Map<String,Object> param=new HashMap<String,Object>();
			param.put("productName", product.getProductName());
			param.put("productPrice", product.getProductPrice());
			param.put("productDescription", product.getProductDescription());
			//param.put("productId", product.getProductId());//id如果是空的,转化成参数key=value
			param.put("productNum", product.getProductNum());
			param.put("productCategory", product.getProductCategory());
			param.put("productImgurl", product.getProductImgurl());
			HttpResult httpResult = client.doPost(url, param);
			String result=httpResult.getBody();
			if("1".equals(result)){
				return 1;
			}else{
				return 0;
			}
			
		}catch(Exception e){
			return 0;
		}
		
		
	}

	public EasyUIResult queryProduct(Integer page, Integer rows) throws Exception {
		//EasyUIResult要totalnum productList
		String url="http://manage.jt.com/product/query";
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("page", page);
		param.put("rows", rows);
		String ERJson = client.doGet(url, param);
		return ObjectUtil.mapper.readValue(ERJson, EasyUIResult.class);
	}
	
	
}
