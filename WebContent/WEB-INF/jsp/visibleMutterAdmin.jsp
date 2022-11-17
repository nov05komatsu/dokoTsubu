<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>どこつぶ管理者ページ</title>
</head>
<body> 
<c:choose>
	<c:when test="${empty mutterList}">
		<p style="color:red">現在非表示のつぶやきはありません</p>
		<a href="/dokoTsubu/Admin"><button>戻る</button></a>
	</c:when>
	<c:otherwise>
		<p>どのつぶやきを表示させますか？</p>
		<form action="/dokoTsubu/VisibleMutterAdmin" method="post">
			<c:forEach var="mutter" items="${mutterList}">
				<p><input type="checkbox" name="list" value="<c:out value="${mutter.id}"/>">
				<c:out value="${mutter.date}"/>
				<c:out value="${mutter.userName }"/>:<c:out value="${mutter.text}"/></p>
			</c:forEach>
		<input type="submit" value="表示させる"><br>
		</form>
		<br>
		<a href="/dokoTsubu/Admin"><button>キャンセル</button></a>
	</c:otherwise>
</c:choose>
</body>
</html>