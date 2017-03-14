package zTest;

import java.io.File;
import java.util.Observer;

import DAO.DBproxy;
import DAO.DataBaseConnectionToAccess;
import DAO.DataBaseConnectionToMysql;
import overall.BuildClient;
import overall.BuildServer;
import overall.Builder;
import overall.Diector;
import overall.End;
import overall.XMLUtil;
import thread.GetFileFromEveryone;
import thread.SendFileEveryone;

public class Test {

	public static void main(String[] args) {
		try {
	
     Diector d=new Diector();
     d.setBuilder((Builder)XMLUtil.getBean("Server"));
     d.construct();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
	