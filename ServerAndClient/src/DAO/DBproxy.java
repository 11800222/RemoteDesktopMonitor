package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//���ݿ��е����ݶ���






import javax.swing.JOptionPane;

import overall.End;
import overall.XMLUtil;


// ʵ�����ݿ����ӽӿڵ���ʵ���ݿ����ӣ�������access����ʵ���ݿ����ӡ��õ������ļ���account.accdb��


// ���ݿ����(�����ң���-�Ľӿ�
interface IDBoperation {
	public boolean hasAccount(String accountname,String password) throws Exception;

	public Account setAccount(String aname,Account account) throws Exception;
}

// ʵ��-���ݿ����(�����ң���Ľӿ�-���ࡣ��Ȼ���ݿ������Ҫ�������ݿ⣬������ಢ��Ĭ������ĳ�����ݿ⣬���ǿ����ڹ��캯����ͨ��connectionָ��Ҫ���ӵ����ݿ⡣
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

// ���ݿ�����࣬��װ�������ݿ������������ݿ�����ࡣ

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
