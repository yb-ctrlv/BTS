<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta name="_csrf" th:content="${_csrf.token}"/>
<meta name="_csrf_header" th:content="${_csrf.headerName}"/>
<link rel="stylesheet"  href="/bts/css/bootstrap.css" type="text/css">
<link rel="stylesheet"  href="/bts/css/register.css" type="text/css">
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>
<script type="text/javascript">

	var getPwchk = /^(?=.*[a-zA-Z!@#$%^*+=-])(?=.*[0-9]).{8,20}$/;
	var getNickchk = /^[ㄱ-ㅎ가-힣A-Za-z0-9!@#$%^*+=-]{1,14}$/;
	var pwConfirm = false;
	var nickConfirm = true;
	
	function pwChk(){
		var pw = $("input[name=member_pw]").val();
		pwConfirm = false;
		
		if(!getPwchk.test(pw)){
			$("#pwck").css('color', 'red');
			$("#pwck").html("문자와 숫자를 혼합하여 8~20자로 입력하세요");
			$("input[name=member_pw]").focus();
			pwConfirm = false;
			return false;
		}else{
			$("#pwck").css('color', 'blue');
			$("#pwck").html("사용가능한 password 입니다.");
			pwConfirm = true;
			return false;			
		}
		
	}
	
	function nickChk(){
		var nick = $("input[name=member_nickname]").val();
		nickConfirm = false;
		
		if(!getNickchk.test(nick)){
			$("#nickck").css('color', 'red');
			$("#nickck").html("1~14글자, 공백은 사용불가능합니다.");
			$("input[name=member_nickname]").focus();
			nickConfirm = false;
			return false;
		}else{
			$("#nickck").css('color', 'blue');
			$("#nickck").html("사용가능한 닉네임 입니다.");
			nickConfirm = true;
			return false;
		}
	}
	
	function submitChk(){
		if(!pwConfirm){
			alert("pw를 형식에 맞게 입력해주세요");
			$("input[name=member_pw]").focus();
			return false;
		}
		if(!nickConfirm){
			alert("닉네임을 형식에 맞게 입력해주세요");
			$("input[name=member_nickname]").focus();
			return false;
		}
		
		alert("정보가 수정되었습니다!");
		return true;
	}


</script>
</head>

<body>
	<div id="mainContainer">
		<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal" var="user" />
		<form class="form-signin" action="/bts/member/infoupdateres" method="post" onsubmit="return submitChk();">
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
			<input type="hidden" name="member_no" value="${user.member_no }">
			<div class="input-control">
            	<label>ID</label>
           	 	<input type="text" class="form-control" name="member_id" value="${user.member_id }" readonly="readonly">
           		<span id="idck">ID는 변경하실 수 없습니다</span>
            </div>
            <div class="input-control">
                <label>PASSWORD</label>
                <input type="password" class="form-control" name="member_pw" onchange="pwChk();" placeholder="사용할 Password를 입력해주세요">
                <span id="pwck">변경하실 PASSWORD를 입력해주세요</span>
            </div>
            <div class="input-control" id="emailInput">
                <label>EMAIL</label>
                <input type="text" class="form-control" name="memeber_email" value="${user.member_email }" readonly="readonly">
                <span id="emailck">EMAIL은 변경하실 수 없습니다</span>
            </div>
            <div class="input-control">
                <label>NICKNAME</label>
                <input type="text" class="form-control" name="member_nickname" onchange="nickChk();" value="${user.member_nickname }">
                <span id="nickck">변경하실 NICKNAME을 입력하세요</span>
            </div>
            <div class="input-control">
                <label></label>
                <input type="submit" class="btn btn-info btn-lg" value="정보수정">
                <input type="button" class="btn btn-info btn-lg" onclick="location.href='/bts/user/main'" value="메인으로">
            </div>
		</form>
		</sec:authorize>
	</div>
</body>
</html>