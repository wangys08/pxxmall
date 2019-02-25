package com.jt.sec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.sec.mapper.SecMapper;
import com.jt.set.pojo.Seckill;

@Service
public class SecService {
	@Autowired
	private SecMapper secMapper;
	public List<Seckill> queryAll() {
	
		return secMapper.selectAll();
	}
	public Seckill queryOne(Long seckillId) {
		
		return secMapper.selectByPrimaryKey(seckillId);
	}

}
