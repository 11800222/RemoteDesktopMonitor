package DAO;

import java.io.Serializable;

public  class Account implements Serializable{
	private String account;
	private String password;
    private String stuname;
	private String major;
	private String classname;
	private String loginInTime;
	private String ip;
	
	
	public String getStuname() {
		return stuname;
	}



	public void setStuname(String stuname) {
		this.stuname = stuname;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Account(){
		
	}
	public Account(String account,String password){
		this.account=account;
		this.password=password;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getLoginInTime() {
		return loginInTime;
	}
	public void setLoginInTime(String loginInTime) {
		this.loginInTime = loginInTime;
	}
	
}
