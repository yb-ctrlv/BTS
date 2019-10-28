<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet"  href="/bts/css/bootstrap.css" type="text/css">
<link rel="stylesheet"  href="/bts/css/makeroomform.css" type="text/css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/bts/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/bts/js/makeroomform.js"></script>
</head>
<body>

	<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="user" />	
	<form action="/bts/member/makeroom" method="post" onsubmit="return submitChk();"> 
	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
	<input type="hidden" name="room_master" value="${user.member_nickname }">
		
		<div class="container">
			<h1>방 개설하기</h1><br>
			<label>Room Name</label>
			<input type="text" class="form-control" name="room_name" maxlength="10" placeholder="방 제목을 입력하세요" required="required"><br>
			<label>Room Password</label>
	       	<input type="password" class="form-control" name="room_password" maxlength="10" placeholder="Password 입력시 비공개방으로 생성됩니다"><br>
	       	<label>Room Image</label>
	       	<div id="galleryWrap">
				<p>
					<img alt="이전그림" id="leftArrow" class="arrowImg" src="/bts/resources/images/leftArrow.png"/>
					<img alt="갤러리 그림" src="/bts/resources/images/broadcastingImage01.png" id="gallery"/>
					<img alt="다음그림" id="rightArrow" class="arrowImg" src="/bts/resources/images/rightArrow.png"/>
				</p>
			</div>
	       	<input type="hidden" id="room_image" name="room_image" value="/bts/resources/images/broadcastingImage01.png" />
	      	<label>Room Maximum</label><br>
	       	<label class="radio-inline">
				<input type="radio" name="inlineRadioOptions" value="2" checked="checked"> 2명
			</label>
			<label class="radio-inline">
				<input type="radio" name="inlineRadioOptions" value="3"> 3명
			</label>
			<label class="radio-inline">
				<input type="radio" name="inlineRadioOptions" value="4"> 4명
			</label>
			<label class="radio-inline">
				<input type="radio" name="inlineRadioOptions" value="5"> 5명
			</label><br>
			<label class="radio-inline">
				<input type="radio" name="inlineRadioOptions" value="6"> 6명
			</label>
			<label class="radio-inline">
				<input type="radio" name="inlineRadioOptions" value="7"> 7명
			</label>
			<label class="radio-inline">
				<input type="radio" name="inlineRadioOptions" value="8"> 8명
			</label>
			<label class="radio-inline">
				<input type="radio" name="inlineRadioOptions" value="9"> 9명
			</label><br>
			<input type="hidden" id="room_maxval" name="room_maxval" value="2" />
	     	<div id="btnDiv">
	     		<input type="submit" class="btn btn-primary" value="생성하기">
	     		<input type="button" class="btn btn-secondery" value="돌아가기" onclick="location.href='/bts/user/main'" >
	     	</div>
     	</div>
	</form>
	</sec:authorize>
		
		


</body>
</html>