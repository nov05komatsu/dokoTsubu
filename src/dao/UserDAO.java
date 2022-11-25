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
	
	// すべてのユーザーを探す
	public List<User> findAll() {
		List<User> userList = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "SELECT ID, NAME, PASS FROM USERLIST";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();
			
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
	
	// 指定したユーザーを探す
	public User findUser(String name, String pass) {
		User user = null;
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "SELECT id, name, pass FROM userlist WHERE name LIKE ? AND pass LIKE ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, name);
			pStmt.setString(2, pass);
			
			// MEMO:executeUpdateでうまく動いてくれなかった
			// ResultSetはnullにならないらしいのでnext()を使用して分岐
			// ResultSetが0行の場合、.next()はfalseを返すはずなので
			// SQL文の実行結果が0件であれば、Userインスタンスは生成されずnullのまま
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				user = new User(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("pass")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	// 新規ユーザーを生成する
	public boolean create(User user) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			
			String sql = "INSERT INTO USERLIST (NAME, PASS) VALUES(?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, user.getName());
			pStmt.setString(2, user.getPass());
			
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
	
}
