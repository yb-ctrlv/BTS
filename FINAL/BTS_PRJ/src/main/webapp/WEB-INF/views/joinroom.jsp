<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>
<script type="text/javascript">

	$(function(){
		var nickname = $("#nickname").val();
		var room_maxval = $("#room_maxval").val();
		var room_addr = $("#room_addr").val();
		
		var uri = "https://192.168.10.9:3000/joinRoom?nickname="+nickname+
				  "&room_maxval="+room_maxval+"&room_addr="+room_addr+"&unkown=";
		
		//popupFunc(uri, "webrtc");
		location.href = uri;
		
	});
	
	/*
	function popupFunc(uri, popupName) {
		  var screenW = screen.availWidth;  // 스크린 가로사이즈
		  var screenH = screen.availHeight; // 스크린 세로사이즈

		 window.open(uri, popupName,'width='+ screenW +',height='+ screenH, 'resizable=no', 'scrollbars=no');
	}
	*/


	

	
</script>
</head>
<body>

	<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="user" />	
		<input type="hidden" id="nickname" value="${user.member_nickname }">
		<input type="hidden" id="room_addr" value="${room_addr }" />
		<input type="hidden" id="room_maxval" value="${room_maxval }" />
	</sec:authorize>
	



</body>
</html>