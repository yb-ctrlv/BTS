<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>임시 메인입니다.</h1>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="user" />
	<form action='<c:url value='/logout'/>' method="post">
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		<input type="submit" value="LOGOUT">
	</form>
	<c:choose>
		<c:when test="${fn:length(user.member_id) > 15 }">
			<a href="/bts/member/snsinfoupdateform">내정보 수정</a>
		</c:when>
		<c:otherwise>
			<a href="/bts/member/infopwcheck">내정보 수정</a>
		</c:otherwise>
	</c:choose>
	<br>-----------------------------------------친구목록--------------------------------------
	<ul id="friendlist">
		
	</ul>
	<br>-----------------------------------------채팅목록--------------------------------------
	<ul id="chatlist">
					
	</ul>
	
</sec:authorize>
<sec:authorize access="isAnonymous()">
	<a href="/bts/user/loginform">로그인</a>
	<a href="/bts/user/registerform">회원가입</a>
	<a href="/bts/user/findid">아이디찾기</a>
	<a href="/bts/user/fidepw">비밀번호찾기</a>
</sec:authorize>

</body>
</html>