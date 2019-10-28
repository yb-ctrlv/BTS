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
<link rel="stylesheet" href="/bts/resources/css/infopwcheck.css">
<link rel="stylesheet" href="/bts/resources/css/bootstrap.css">
</head>

<body>
	<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="user" />
	<div id="mainContainer">
        <h4 class="formtitle">${user.member_id }님! 보안을 위해 <br>비밀번호를 한번더 확인합니다.</h4>
        <form class="form-signin" action="/bts/member/infoupdateform" method="post" onsubmit="return submitChk();">
        <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		<input type="hidden" name="member_no" value="${user.member_no }" />
        <input type="hidden" name="member_id" value="${user.member_id }">
            <div class="input-control">
                <label for="">패스워드를 입력해주세요</label>
                <input type="password" class="form-control" name="member_pw" placeholder="Password">
                <span id="errorMsg"></span>
            </div>
            <br>
            <div class="input-control">
                    <input type="submit" class="btn btn-info" value="확인">
                    <input type="button" class="btn btn-default" value="취소" onclick="location.href='/bts/user/main'">
            </div>
        </form>

    </div>
	</sec:authorize>
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>
<script type="text/javascript" src="/bts/resources/js/infopwcheck.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="/bts/resources/js/bootstrap.min.js"></script>
</body>
</html>