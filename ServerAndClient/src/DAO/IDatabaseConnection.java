package DAO;

import java.sql.Connection;

public interface IDatabaseConnection {
	public Connection getConnection() throws Exception;
	public boolean closeConnection() throws Exception;
}
