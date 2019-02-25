package com.jt.web.service;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.web.pojo.Product;

@Service
public class SearchService {
	@Autowired
	private TransportClient client;
	public List<Product> search(String q,Integer page) {
		//利用q封装一个matchQuery对象,查询结果
		//使用分页结果 10条
		//构造一个matchquery对象
		MatchQueryBuilder query = QueryBuilders.matchQuery("product_name", q).
		operator(Operator.OR);
		//java编程思想-->java,编,程,思,想
		int start=(page-1)*5;
		SearchResponse response = client.prepareSearch("easymall").setQuery(query).
		setFrom(start).setSize(5).get();
		//从response中遍历
		SearchHits hits = response.getHits();
		System.out.println("供收到总数据:"+hits.getTotalHits());
		//封装返回对象
		List<Product> pList=new ArrayList<Product>();
		
		for (SearchHit hit : hits) {
			//商品id 商品名称,price image
			Product prod=new Product();
			prod.setProductId(hit.getId());
			prod.setProductName(hit.getSource().get("product_name")+"");
			prod.setProductPrice(Double.parseDouble(hit.getSource().get("product_price")+""));
			prod.setProductImgurl(hit.getSource().get("product_imgurl")+"");
			pList.add(prod);
		}
		return pList;
	}

}
