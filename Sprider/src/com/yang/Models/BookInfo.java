package com.yang.Models;

import java.math.BigDecimal;

public class BookInfo implements Comparable{
	private String info;
	private String bookname;
	//评价人数
	private int evaluates;
	//评分
	private double goal;
	public BookInfo(String info,String name,int e,double g){
		this.info = info;
		this.bookname = name;
		this.evaluates = e;
		this.goal = g;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public int getEvaluates() {
		return evaluates;
	}
	public void setEvaluates(int evaluates) {
		this.evaluates = evaluates;
	}
	public double getGoal() {
		return goal;
	}
	public void setGoal(double goal) {
		this.goal = goal;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public int compareTo(Object another) {
		// TODO Auto-generated method stub
		BookInfo other = (BookInfo)another;
		double thisgoal = this.goal;
		double othergoal = other.goal;
		BigDecimal data1 = new BigDecimal(thisgoal); 
		BigDecimal data2 = new BigDecimal(othergoal); 
		return data1.compareTo(data2);
	}
	
}
