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
<script src="/dokoTsubu/js/postMutter.js"></script>
</head>
<body>
<p><c:out value="${loginUser.name}" />さん、ログイン中</p>
<a href="/dokoTsubu/Logout">ログアウト</a>
<p><a href="/dokoTsubu/Main">更新</a></p>
<div class="postMutterBox">
	<input id="postMutterText" type="text" name="text">
	<button id="postMutterButton">つぶやく</button>
</div>
<br>
<a href="/dokoTsubu/RemoveMutter"><button>つぶやきを削除する</button></a>
<c:if test="${not empty errorMsg}">${errorMsg}</c:if>
<!-- <c:if test="${not empty buildedText}">${buildedText}</c:if> -->
<c:choose>
<c:when test="${not empty mutterList}">
<form action="#">
	<ul class="mutterList">
	<c:forEach var="mutter" items="${mutterList}">
		<li>
		<c:out value="${mutter.date} ${mutter.userName} ${mutter.text}"/>
        <button type="submit" value="<c:out value="${mutter.id}"/>">good</button>
        <c:out value="${mutter.good}"/>        
		</li>
	</c:forEach>
	</ul>
</form>
</c:when>
<c:otherwise>
	<p>現在つぶやきはありません</p>
</c:otherwise>
</c:choose>
</body>
</html>