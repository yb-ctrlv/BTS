<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta name="_csrf" th:content="${_csrf.token}"/>
<meta name="_csrf_header" th:content="${_csrf.headerName}"/>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>
<script type="text/javascript">

	var nickConfirm = true;
	var getNickchk = /^[ㄱ-ㅎ가-힣A-Za-z0-9!@#$%^*+=-]{1,14}$/;
	
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

	<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="user" />
		<form action="/bts/member/infoupdateres" method="post" onsubmit="return submitChk();">
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
			<input type="hidden" name="member_no" value="${user.member_no }">
			<input type="hidden" name="member_id" value="${user.member_id }">
			<input type="hidden" name="member_pw" value="${user.member_id }">
			<input type="hidden" name="member_email" value="${user.member_email }">
			<table border="1">
				<tbody>
					<tr>
						<th colspan="2">SNS 로그인 회원</th>
					</tr>
					<tr>
						<th>NICKNAME</th>
						<td>
							<input type="text" name="member_nickname" value="${user.member_nickname }"  onchange="nickChk();">
						</td>
					</tr>
					<tr bordercolor="white">
						<td colspan="2" style="color: blue; font-size: 8pt" id="nickck">변경하실 NICKNAME을 입력하세요</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="정보수정">
							<input type="button" onclick="location.href='/bts/user/main'" value="메인으로">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</sec:authorize>
</body>
</html>