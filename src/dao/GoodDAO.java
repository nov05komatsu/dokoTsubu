package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GoodDAO {
	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/docoTsubu";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public boolean checkGoodList(int id) {
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "SELECT mutter_id FROM goodlist";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				int mutter_id = rs.getInt("mutter_id");
				if(id == mutter_id) {
					return false;
				}
			}
			String sql01 = "SELECT good FROM mutterlist WHERE id = ?";
			PreparedStatement pStmt01 = conn.prepareStatement(sql01);
			pStmt01.setInt(1, id);
			
			String sql02 = "UPDATE mutterlist SET good = ? WHERE id = ?";
			PreparedStatement pStmt02 = conn.prepareStatement(sql02);
			pStmt02.setInt(2, id);
			
			rs = pStmt.executeQuery();
			while(rs.next()) {
				int good = rs.getInt("good");
				pStmt02.setInt(1, (good+1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
