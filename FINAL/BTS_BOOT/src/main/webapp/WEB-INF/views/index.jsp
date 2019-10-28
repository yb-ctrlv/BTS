<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="_csrf" th:content="${_csrf.token}"/>
<meta name="_csrf_header" th:content="${_csrf.headerName}"/>
<title>Insert title here</title>
<script type="text/javascript" src="/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/js/d3.min.js"></script>
<script type="text/javascript" src="/js/index.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<link rel="stylesheet"  href="/css/bootstrap.css" type="text/css">
<link rel="stylesheet"  href="/css/index.css" type="text/css">

</head>
<body>
	<h1>BTS 가입현황</h1>
	<a href="/memberlist"></a>
	<div id="chart"></div>
	<br/>
	<div id="chartName"></div>
	<br/>
	<br/>
<sec:authorize access="isAuthenticated()">
	<form action='/logout' method="post">
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		<input type="button" class="btn btn-info" value="멤버관리" onclick="location.href='/memberlist?page=1'">
		<input type="submit" class="btn btn-info" value="LOGOUT">
	</form>
</sec:authorize>


</body>
</html>