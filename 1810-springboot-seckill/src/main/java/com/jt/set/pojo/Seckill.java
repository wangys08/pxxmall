package com.jt.set.pojo;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="seckill")
public class Seckill {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long seckillId;
	private String name;
	private Long initialPrice;
	private Long seckillPrice;
	private String sellPoint;
	private Integer number;
	private Date createTime;
	private Date startTime;
	private Date endTime;
	
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Long getSeckillId() {
		return seckillId;
	}
	public void setSeckillId(Long seckillId) {
		this.seckillId = seckillId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getInitialPrice() {
		return initialPrice;
	}
	public void setInitialPrice(Long initialPrice) {
		this.initialPrice = initialPrice;
	}
	public Long getSeckillPrice() {
		return seckillPrice;
	}
	public void setSeckillPrice(Long seckillPrice) {
		this.seckillPrice = seckillPrice;
	}
	public String getSellPoint() {
		return sellPoint;
	}
	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}
