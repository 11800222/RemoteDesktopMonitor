package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//数据库中的数据对象






import javax.swing.JOptionPane;

import overall.End;
import overall.XMLUtil;


// 实现数据库连接接口的真实数据库连接，这里是access的真实数据库连接。用的数据文件是account.accdb。


// 数据库操作(增，找）类-的接口
interface IDBoperation {
	public boolean hasAccount(String accountname,String password) throws Exception;

	public Account setAccount(String aname,Account account) throws Exception;
}

// 实现-数据库操作(增，找）类的接口-的类。虽然数据库操作需要连接数据库，但这个类并不默认连接某个数据库，而是可以在构造函数中通过connection指定要连接的数据库。
class DBoperation implements IDBoperation {
	private Connection conn = null;
	private PreparedStatement pstmt = null;

	public DBoperation(Connection conn) {
		this.conn = conn;
	}

	public boolean hasAccount(String accountname,String password) throws Exception {
		ResultSet rs = null;
		boolean bFlag = false;
		String sql = "select account from student  where account=? and password=?";
		try {
			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setString(1, accountname);
			this.pstmt.setString(2, password);
			rs = this.pstmt.executeQuery();
			if (rs.next()) {
				bFlag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
		}

		return bFlag;
	}

	public Account setAccount(String aname,Account account) throws Exception {
		ResultSet rs = null;
		String sql = "select * from student where account=?";
		try {
			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setString(1, aname);
			rs = this.pstmt.executeQuery();
			if (rs.next()) {
			account.setAccount(aname);
			account.setStuname(rs.getString("stuname"));
			account.setMajor(rs.getString("major"));
			account.setClassname(rs.getString("classname"));
			account.setLoginInTime(rs.getString("loginInTime"));
			account.setIp(rs.getString("loginInip"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (this.pstmt != null) {
				this.pstmt.close();
			}
		}
		return account;
	}
}

// 数据库代理类，包装整合数据库连接类与数据库操作类。

public class DBproxy implements IDBoperation {
	private IDatabaseConnection dbc = null;
	private IDBoperation dbo = null;

	public DBproxy() {
		try {
		//	this.dbc = new DataBaseConnectionToMysql();
			this.dbc= (IDatabaseConnection) XMLUtil.getBean("DB");
			this.dbo = new DBoperation(this.dbc.getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean hasAccount(String accountname,String password) throws Exception {
		boolean bFlag = false;
		try {
			bFlag = this.dbo.hasAccount(accountname,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bFlag;
	}
	public Account setAccount(String aname,Account account) throws Exception {
		 Account paccount =null;
		try {
			paccount = this.dbo.setAccount(aname,account);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paccount;
	}
}
