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
<p style="color:red"><c:out value="${requestScope.msg}"/></p>
<a href="/dokoTsubu/">ログイン画面へ</a>
</body>
</html>