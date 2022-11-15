<% // EL式とアクションタグを利用 %>
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
<p><c:out value="${loginUser.name}" />さん、ログイン中</p>
<a href="/dokoTsubu/Logout">ログアウト</a>
<p><a href="/dokoTsubu/Main">更新</a></p>
<form action="/dokoTsubu/Main" method="post">
<input type="text" name="text">
<input type="submit" value="つぶやく">
</form><br>
<a href="/dokoTsubu/RemoveMutter"><button>つぶやきを削除する</button></a>
<c:if test="${not empty errorMsg}">${errorMsg}</c:if>
<!-- <c:if test="${not empty buildedText}">${buildedText}</c:if> -->
<c:choose>
<c:when test="${not empty mutterList}">
	<c:forEach var="mutter" items="${mutterList}">
		<p><c:out value="${mutter.date}" /> <c:out value="${mutter.userName}"/>:
		<c:out value="${mutter.text}"/></p>
	</c:forEach>
</c:when>
<c:otherwise>
	<p>現在つぶやきはありません</p>
</c:otherwise>
</c:choose>
</body>
</html>