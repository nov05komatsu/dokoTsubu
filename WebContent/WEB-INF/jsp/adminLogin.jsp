<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<h1>管理者ログイン</h1>
<c:if test="${not empty eMessage}">
	<c:forEach var="ems" items="${eMessage}">
		<p style="color:red"><c:out value="${ems}"/></p>
	</c:forEach>
</c:if>
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