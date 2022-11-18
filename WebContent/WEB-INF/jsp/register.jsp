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
<h1>どこつぶ　新規会員登録</h1>
<c:if test="${not empty eMessage}">
	<c:forEach var="ems" items="${eMessage}">
	<p style="color:red"><c:out value="${ems}"/></p>		
	</c:forEach>
</c:if>
<form action="/dokoTsubu/Register" method="post">
<ul>
	<li>ユーザー名:<input type="text" name="name"></li>
	<li>パスワード:<input type="text" name="pass"> 半角英数8文字以上</li>
	<li><input type="submit" value="登録する" ></li>
</ul>
</form>
<a href="/dokoTsubu/">戻る</a>
</body>
</html>