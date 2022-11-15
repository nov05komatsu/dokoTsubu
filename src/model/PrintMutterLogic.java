package model;
import java.util.List;


// 現在使ってません!!!
public class PrintMutterLogic {
	// 通常の表示用
	public String execute(List<Mutter> mutterList) {
		// つぶやき情報を表示するための前処理
		StringBuilder builder = new StringBuilder();
		if(mutterList != null){
			for(Mutter mutter : mutterList){
				builder.append("<p>")
				.append(mutter.getDate()).append("\n")
				.append(mutter.getUserName())
				.append(":").append(mutter.getText())
				.append("</p>");
			}
		} else {
			builder.append("<p>現在つぶやきはありません</p>");
		}
		return builder.toString();
	}
	
	// ユーザー別の表示用
	public String execute(User loginUser, List<Mutter> mutterList) {
		StringBuilder builder = new StringBuilder();
		for(Mutter mutter : mutterList) {
			if(loginUser.getName().equals(mutter.getUserName())) {
				builder.append("<p><input type=\"checkbox\" name=\"list\" value=\"")
				.append(mutter.getId()).append("\">").append(mutter.getDate()).append("\n")
				.append(mutter.getUserName()).append(":").append(mutter.getText()).append("</p>");
			}
		}
		if(builder.toString().length() == 0) {
			builder
				.append("<p style=\"color:red\">つぶやきはありません</p>\n")
				.append("<a href=\"/dokoTsubu/Main\"><button>戻る</button></a>\n");
		} else {
			// insertの順番に注意!!
			builder
				.insert(0, "<form action=\"/dokoTsubu/RemoveMutter\" method=\"post\">\n")
				.insert(0, "<p>どのつぶやきを削除しますか？</p>\n");
			builder
				.append("<input type=\"submit\" value=\"削除する\" >\n")
				.append("</form>\n")
				.append("<br>\n")
				.append("<a href=\"/dokoTsubu/Main\"><button>キャンセル</button></a>\n");
		}
		return builder.toString();
	}
}
