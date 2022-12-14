<% // EL式とアクションタグを利用 %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="/dokoTsubu/js/good.js"></script>
</head>

<body>
<p class="userName"><c:out value="${loginUser.name}" />さん、ログイン中</p>
<a href="/dokoTsubu/Logout">ログアウト</a>
<p><a href="/dokoTsubu/Main">更新</a></p>
<form action="/dokoTsubu/Main" method="post">
	<input type="text" name="text">
	<input type="submit" value="つぶやく">
</form><br>
<a href="/dokoTsubu/RemoveMutter"><button>つぶやきを削除する</button></a>

<% //スコープにエラーメッセージがあれば表示する %>
<c:if test="${not empty errorMsg}">${errorMsg}</c:if>

<% //つぶやきリストが空でなければ表示 %>
<c:choose>
<c:when test="${not empty mutterList}">
	<ul class="mutterList">
	<c:forEach var="mutter" items="${mutterList}">
		<li>
		<c:out value="${mutter.date} ${mutter.userName} ${mutter.text}"/>
        <button class="goodButton" type="submit" value="<c:out value="${mutter.id}"/>">good</button>
        <span class="goodSum"><c:out value="${mutter.good}"/></span>        
		</li>
	</c:forEach>
	</ul>
</c:when>
<c:otherwise>
	<p>現在つぶやきはありません</p>
</c:otherwise>
</c:choose>
</body>
</html>