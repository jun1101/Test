package com.audio.VO;

import java.util.Date;

public class userVO {

	private int num_id;
	private String mem_id;
	private String mem_pass;
	private String mem_name;
	private String mem_phone;
	private String mem_kakao;
	private String mem_google;
	private String mem_naver;
	private Date mem_day;
	private int month;
	private String userrole;
	
	
	
	
	
	
	public String getUserrole() {
		return userrole;
	}
	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}
	public int getNum_id() {
		return num_id;
	}
	public void setNum_id(int num_id) {
		this.num_id = num_id;
	}
	public String getMem_naver() {
		return mem_naver;
	}
	public void setMem_naver(String mem_naver) {
		this.mem_naver = mem_naver;
	}
	public String getMem_kakao() {
		return mem_kakao;
	}
	public void setMem_kakao(String mem_kakao) {
		this.mem_kakao = mem_kakao;
	}
	public String getMem_google() {
		return mem_google;
	}
	public void setMem_google(String mem_google) {
		this.mem_google = mem_google;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_pass() {
		return mem_pass;
	}
	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	
	public String getMem_phone() {
		return mem_phone;
	}
	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}
	public Date getMem_day() {
		return mem_day;
	}
	public void setMem_day(Date mem_day) {
		this.mem_day = mem_day;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	
	
}
