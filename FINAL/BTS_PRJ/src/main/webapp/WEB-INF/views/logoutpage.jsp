<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>
<script type="text/javascript">
	
	$(function(){
		if($("#login").length){
			$("#login").submit();
		}else{
			location.href="/bts/";
		}	
	});
	
</script>
</head>
<body>

	<c:choose>
		<c:when test="${not empty id}">
			<form id="login" action='/bts/login' method="post">
				<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
				<input type="hidden" name="id" value="${id }" />
				<input type="hidden" name="pw" value="${pw }" />	
			</form>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
	
	


</body>
</html>