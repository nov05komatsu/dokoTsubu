package model;

import java.util.ArrayList;
//import java.util.regex.Pattern;

public class ValueCheckLogic {
	
	public ArrayList<String> execute(String name, String pass) {
//		Pattern ptn = Pattern.compile("[0-9a-zA-Z]{8,}");
		String ptn = "[0-9a-zA-Z]{8,}";
		ArrayList<String> eMessage = new ArrayList<>();
		
		if(name == null || name.equals("")) {
			eMessage.add("名前が入力されていません。");
		}
		
		if(pass == null || pass.equals("")) {
			eMessage.add("パスワードが入力されていません");
//		} else if (!ptn.matcher(pass).matches()) {
		} else if (!pass.matches(ptn)) {
			eMessage.add("パスワードは半角英数字8文字以上で登録してください");
		}
		return eMessage;
	}
	
	public ArrayList<String> loginValueCheck(String name, String pass) {
		ArrayList<String> eMessage = new ArrayList<>();
		
		if(name == null || name.equals("")) {
			eMessage.add("名前が入力されていません。");
		}
		
		if(pass == null || pass.equals("")) {
			eMessage.add("パスワードが入力されていません");
		}
		
		return eMessage;
	}
}
