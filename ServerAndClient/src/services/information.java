package services;

import DAO.Account;

public class information {
public static String list(Account account){
		return " 学生名 : "+account.getStuname()+"\n"+
				" 专业 : "+account.getMajor()+"\n"+
			   " 班级 : "+account.getClassname()+"\n"+
				" 上次登录时间 : "+account.getLoginInTime()+"\n"+
			   " 上次登录ip : "+account.getIp();
	}
}

