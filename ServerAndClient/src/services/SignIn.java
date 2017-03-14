package services;

import DAO.Account;
import overall.*;

public class SignIn {
	public static boolean sign(String accountname ,String password) {
		boolean bFlag = false;
		try {
			bFlag = End.DBp.hasAccount(accountname,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bFlag;
	}
}