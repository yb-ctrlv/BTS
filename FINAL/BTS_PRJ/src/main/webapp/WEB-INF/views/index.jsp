<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	
</script>
<link href="/bts/resources/css/index.css" rel="stylesheet"	type="text/css">
</head>
<body>
	<div class="wholediv">
		<div class="divmainlogo">
			<div class="logoletter">
				<p>BTS</p>
			</div>
		</div>
		<div class="wrapbuttondiv">
			<div class="buttondiv">
				<div class="button1" onclick="location.href='/bts/loginform'">
					<div class="buttonLetter">
						<p>LOGIN</p>
					</div>
				</div>

				<div class="button2" onclick="location.href='/bts/user/main'">
					<div class="buttonLetter">
						<p>MAIN</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>