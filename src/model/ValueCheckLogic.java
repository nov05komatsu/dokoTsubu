package model;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ValueCheckLogic {
	
	public ArrayList<String> execute(String name, String pass) {
		Pattern ptn = Pattern.compile("[0-9a-zA-Z]{8,}");
		ArrayList<String> eMessage = new ArrayList<>();
		
		if(name == null || name == "") {
			eMessage.add("名前が入力されていません。");
		}
		
		if(pass == null) {
			eMessage.add("パスワードが入力されていません");
		} else if (!ptn.matcher(pass).matches()) {
			eMessage.add("パスワードは半角英数字8文字以上で登録してください");
		}
		return eMessage;
	}
}
