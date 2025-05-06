package connectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConnectionDatabase {
	private final static String Driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private final static String URL = "jdbc:sqlserver://localhost:60834;encrypt=false;databaseName=Empresa";
	private final static String user = "sa";
	private final static String password = "Senailab03";



	public static Connection getConnection() {
		try {
			Class.forName(Driver);
			return DriverManager.getConnection(URL, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro de conexão: " + e);
		}
	}


	public static void closeConnection(Connection con) {

		try {
			if(con != null) {
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new  RuntimeException("Erro ao fechar: "+ e);
		}

	}
	
	public static void closeConnection(Connection con, PreparedStatement stmt) {
		closeConnection(con);
		
		try {
			if(stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new  RuntimeException("Erro ao fechar: "+ e);
		}
		
	}
	
	public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
		closeConnection(con, stmt);
		
		try {
			if(rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new  RuntimeException("Erro ao fechar: "+ e);
		}
		
	}

}
