<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<meta name="_csrf" th:content="${_csrf.token}"/>
<meta name="_csrf_header" th:content="${_csrf.headerName}"/>
</head>
<link rel="stylesheet"  href="/bts/css/bootstrap.css" type="text/css">
<link rel="stylesheet"  href="/bts/css/login.css" type="text/css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

	
<body>
<div class="container">
    <form class="form-signin" action='/bts/login' method="post">
        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
      <h2 class="form-signin-heading text-center">BTS LOGIN PAGE</h2>
      <label for="inputEmail" class="sr-only">Email address</label>
      <input type="text" id="inputId" name="id" class="form-control" placeholder="ID" required autofocus>
      <label for="inputPassword" class="sr-only">Password</label>
      <input type="password" id="inputPassword" name="pw" class="form-control" placeholder="Password" required>
      <div>
        <div class="checkbox">
          <label>
            <input type="checkbox" id="idSaveCheck" value="remember-me"> Remember me
          </label>
        </div>
        <div class="text-center">
          <p><a href="/bts/user/findid">Email for findId</a></p>
          <p><a href="/bts/user/findpw">Email for findPw</a></p>
        </div>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
      <button class="btn btn-default btn-lg btn-block" onclick="location.href='/bts/user/registerform'" type="button">회원가입</button>
      <div>
		<a id="kakao-login-btn" class="center-block" href="javascript:kakaoLogin();">
			<img src="//mud-kage.kakao.com/14/dn/btqbjxsO6vP/KPiGpdnsubSq3a0PHEGUK1/o.jpg" width="300"/>
		</a>
      </div>
      <div id="naver_id_login"></div>
    </form>

    <form id="snsLogin" action='/bts/login' method="post">
      <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
      <input type="hidden" id="snsId" name="id" />
      <input type="hidden" id="snsPw" name="pw" />	
</form>

  </div>


   
  <script src=" https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"> </script>
  <script src="/bts/js/bootstrap.min.js"></script>
  <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
  <script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
  <script type="text/javascript" src="/bts/resources/js/loginform.js"></script>
</body>
</html>