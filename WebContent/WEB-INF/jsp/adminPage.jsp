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
<p><c:out value="${loginUser.name}"/>でログイン中</p>
<a href="/dokoTsubu/Logout">ログアウト</a>
<a href="/dokoTsubu/RemoveMutterAdmin"><button>つぶやきを削除する</button></a>
<c:if test="${not empty errorMsg}">${errorMsg}</c:if>
<!-- <c:if test="${not empty buildedText}">${buildedText}</c:if> -->
<p style="font-size:1.5rem">ユーザーリスト</p>
<table>
<tr>
<th>ID</th>
<th>ユーザ名</th>
<th>パスワード</th>
</tr>
<c:if test="${not empty userList}">
	<c:forEach var="user" items="${userList}">
		<tr>
			<td><c:out value="${user.id}"/></td>
			<td><c:out value="${user.name}"/></td>
			<td><c:out value="${user.pass}"/></td>
		</tr>
	</c:forEach>
</c:if>
</table>
<p style="font-size:1.5rem">つぶやきリスト(非表示を含む)</p>
<c:choose>
<c:when test="${not empty mutterList}">
	<ul>
	<c:forEach var="mutter" items="${mutterList}">
		<li>
		<c:out value="${mutter.date} ${mutter.userName} ${mutter.date} good:${mutter.good}"/>
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