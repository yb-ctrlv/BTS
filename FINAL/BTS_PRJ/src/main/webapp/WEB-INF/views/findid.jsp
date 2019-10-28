<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="_csrf" th:content="${_csrf.token}" />
<meta name="_csrf_header" th:content="${_csrf.headerName}" />
<link rel="stylesheet"  href="/bts/css/bootstrap.css" type="text/css">
<link rel="stylesheet"  href="/bts/css/register.css" type="text/css">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/bts/resources/js/findid.js"></script>


<body>
	<div id="mainContainer">
	    <h2 class="formtitle">ID 찾기</h2>
       	<h3 class="formtitle">사이트 가입시 등록한 EMAIL을 입력해주세요</h3>
        <div class="input-control" id="emailInput">
    	    <label></label>
        	<input type="text" class="form-control" id="email" onchange="mailChk();" placeholder="Email을 입력해주세요">
            <a class="btn btn-info" id="emailBtn" onclick="emailChk();">인증</a> 
            <input type="hidden" name="member_email" />
        </div>
	</div>
	
	
<script src=" https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"> </script>
<script src="/bts/js/bootstrap.min.js"></script>

</body>
</html>