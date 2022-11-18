<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<h1>管理者ログイン</h1>
<form action="/dokoTsubu/Admin" method="post">
	<ul>
		<li>ユーザー名：<input type="text" name="name"></li>
		<li>パスワード：<input type="text" name="pass"></li>
	</ul>
	<input type="submit" value="ログイン">
</form>
<a href="/dokoTsubu/">戻る</a>
</body>
</html>