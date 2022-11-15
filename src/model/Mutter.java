package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mutter implements Serializable {
	private int id;
	private String userName;
	private String text;
	private String date;
	
	public Mutter() {}
	public Mutter(String userName, String text) {
		this.userName = userName;
		this.text = text;
		
		//つぶやき日時を設定する処理
		var d = LocalDateTime.now();
		var f = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		this.date = f.format(d);
	}
	public Mutter(int id, String userName, String text, String date) {
		this.id = id;
		this.userName = userName;
		this.text = text;
		this.date = date;
	}
	
	public int getId() {
		return id;
	}
	public String getUserName() {
		return userName;
	}
	public String getText() {
		return text;
	}
	public String getDate() {
		return date;
	}
	
}
