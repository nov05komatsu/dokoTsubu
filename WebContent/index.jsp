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
<h1>どこつぶへようこそ</h1>
<c:if test="${not empty eMessage}">
	<c:forEach var="ems" items="${eMessage}">
		<p style="color:red"><c:out value="${ems}"/></p>
	</c:forEach>
</c:if>
<form action="/dokoTsubu/Login" method="post">
	<ul>
		<li>ユーザー名：<input type="text" name="name"></li>
		<li>パスワード：<input type="text" name="pass"></li>
	</ul>
	<input type="submit" value="ログイン">
</form>
<h2><a href="/dokoTsubu/Register">新規会員登録はこちらから</a></h2>
<a style="font-size:.7rem" href="/dokoTsubu/Admin">管理者ログイン</a>
<jsp:include page="/footer.jsp" />
</body>
</html>