package services;

import DAO.Account;

public class information {
public static String list(Account account){
		return " ѧ���� : "+account.getStuname()+"\n"+
				" רҵ : "+account.getMajor()+"\n"+
			   " �༶ : "+account.getClassname()+"\n"+
				" �ϴε�¼ʱ�� : "+account.getLoginInTime()+"\n"+
			   " �ϴε�¼ip : "+account.getIp();
	}
}

