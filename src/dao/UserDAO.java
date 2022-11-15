package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDAO {
	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/docoTsubu";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public List<User> findAll() {
		List<User> userList = new ArrayList<>();
		
		// データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "SELECT ID, NAME, PASS FROM USERLIST";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();
			
			// 結果をArrayListに格納する
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				String pass = rs.getString("PASS");
				User user = new User(id, name, pass);
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return userList;
	}
	
	public boolean create(User user) {
		// データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			
			String sql = "INSERT INTO USERLIST (NAME, PASS) VALUES(?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// INSERT文中の?に使用する値を設定し、SQLを完成させる
			pStmt.setString(1, user.getName());
			pStmt.setString(2, user.getPass());
			
			// INSERT文を実行する
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// できれば戻り値を真偽値にして使いたい
	public void createTable(User user) {
		
		try(Connection conn = DriverManager.getConnection(
				JDBC_URL, DB_USER, DB_PASS)){
			String sql = "SELECT id, name FROM userlist WHERE name LIKE ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user.getName());
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String sql2 = "CREATE TABLE USER_ID_" + id
						+ "_GOODLIST ("
						+ "USER_ID int NOT NULL,"
						+ "MUTTER_ID int PRIMARY KEY,"
						+ "DATE varchar(40) NOT NULL"
						+ ")";
				PreparedStatement pStmt2 = conn.prepareStatement(sql2);
				pStmt2.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkTable() {
		
		// データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "select id, name, pass from userlist";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// executeUpdate=0の場合、管理ユーザーを作成
			int result = pStmt.executeUpdate();
			if(result == 0) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
