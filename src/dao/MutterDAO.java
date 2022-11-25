package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;

public class MutterDAO {
	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/docoTsubu";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	//表示可能な全てのつぶやきを取得
	public List<Mutter> findAll() {
		List<Mutter> mutterList = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "SELECT ID, NAME, TEXT, DATE, DEL, GOOD FROM MUTTER WHERE DEL = 0 ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				String text = rs.getString("TEXT");
				String date = rs.getString("DATE");
				int del = rs.getInt("DEL");
				int good = rs.getInt("GOOD");
				Mutter mutter = new Mutter(id, name, text, date, del, good);
				mutterList.add(mutter);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mutterList;
	}
	
	//管理用に全てのつぶやきを取得
	public List<Mutter> findAllAdmin() {
		List<Mutter> mutterList = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "SELECT ID, NAME, TEXT, DATE, DEL, GOOD FROM MUTTER ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				String text = rs.getString("TEXT");
				String date = rs.getString("DATE");
				int del = rs.getInt("DEL");
				int good = rs.getInt("GOOD");
				Mutter mutter = new Mutter(id, name, text, date, del, good);
				mutterList.add(mutter);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mutterList;
	}
	
	//管理用に非表示となっているつぶやきを取得
	public List<Mutter> findInvisible() {
		List<Mutter> mutterList = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "SELECT ID, NAME, TEXT, DATE, DEL, GOOD FROM MUTTER WHERE DEL = 1 ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				String text = rs.getString("TEXT");
				String date = rs.getString("DATE");
				int del = rs.getInt("DEL");
				int good = rs.getInt("GOOD");
				Mutter mutter = new Mutter(id, name, text, date, del, good);
				mutterList.add(mutter);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mutterList;
	}
	
	//つぶやきを投稿する
	public boolean create(Mutter mutter) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "INSERT INTO MUTTER (NAME, TEXT, DATE) VALUES(?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, mutter.getUserName());
			pStmt.setString(2, mutter.getText());
			pStmt.setString(3, mutter.getDate());
			
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
	
	//ユーザーごとのつぶやきを取得する
	public List<Mutter> findUser(String userName) {
		List<Mutter> mutterList = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "SELECT ID, NAME, TEXT, DATE, DEL, GOOD FROM MUTTER WHERE NAME LIKE ? AND DEL = 0 ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userName);
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				String text = rs.getString("TEXT");
				String date = rs.getString("DATE");
				int del = rs.getInt("DEL");
				int good = rs.getInt("GOOD");
				Mutter mutter = new Mutter(id, name, text, date, del, good);
				mutterList.add(mutter);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mutterList;
	}
	
	//つぶやきの非表示処理
	public void removeMutter(String[] ary) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "UPDATE MUTTER SET DEL = 1 WHERE ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//Note:アプリケーションスコープを使ったバージョンの場合remove()は後ろから
			for(int i = 0; i < ary.length; i++) {
				pStmt.setInt(1, Integer.parseInt(ary[i]));
				pStmt.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//非表示になっているつぶやきを可視化する
	public void visibleMutter(String[] ary) {
		// データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "UPDATE MUTTER SET DEL = 0 WHERE ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			for(int i = 0; i < ary.length; i++) {
				pStmt.setInt(1, Integer.parseInt(ary[i]));
				pStmt.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//goodを増減させる処理
	public int goodSum(int mutterId, int good) {
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			
			String sql01 = "SELECT id, good FROM mutter WHERE ID = ?";
			PreparedStatement pStmt01 = conn.prepareStatement(sql01);
			pStmt01.setInt(1, mutterId);
			
			ResultSet rs = pStmt01.executeQuery();
			if(rs.next()) {
				good += rs.getInt("good");
			}
			
			String sql02 = "UPDATE mutter SET GOOD = ? WHERE ID = ?";
			PreparedStatement pStmt02 = conn.prepareStatement(sql02);
			
			pStmt02.setInt(1, good);
			pStmt02.setInt(2, mutterId);
			pStmt02.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return good;
	}
}
