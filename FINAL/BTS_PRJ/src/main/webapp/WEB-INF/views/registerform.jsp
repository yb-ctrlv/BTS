<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="_csrf" th:content="${_csrf.token}"/>
<meta name="_csrf_header" th:content="${_csrf.headerName}"/>
<link rel="stylesheet"  href="/bts/css/bootstrap.css" type="text/css">
<link rel="stylesheet"  href="/bts/css/register.css" type="text/css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>
<script type="text/javascript" src="/bts/resources/js/register.js">

</script>
</head>
<body>
    <div id="mainContainer">
        <h2 class="formtitle">BTS 회원가입</h2>
        <form class="form-signin" action="/bts/user/register" method="post" onsubmit="return submitChk();">
        <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
            <div class="input-control">
                <label></label>
                <input type="text" class="form-control" name="member_id" onchange="idChk();" placeholder="사용할 ID를 입력해주세요">
                <span id="idck">　</span>
            </div>
            <div class="input-control">
                <label></label>
                <input type="password" class="form-control" name="member_pw" onchange="pwChk();" placeholder="사용할 Password를 입력해주세요">
                <span id="pwck">　</span>
            </div>
            <div class="input-control" id="emailInput">
                <label></label>
                <input type="text" class="form-control" id="email" onchange="mailChk();" placeholder="사용할 Email을 입력해주세요">
                <a class="btn btn-info" id="emailBtn" onclick="emailChk();">인증</a> 
                <span id="emailck">　</span>
                <input type="hidden" name="member_email" />
            </div>
            <div class="input-control">
                <label></label>
                <input type="text" class="form-control" name="member_nickname" onchange="nickChk();" placeholder="사용할 Nickname을 입력해주세요">
                <span id="nickck">　</span>
            </div>
            <div class="input-control">
                <label></label>
                <input type="submit" class="btn btn-info btn-lg" id="submit" value="가입하기">
            </div>
        </form>
	</div>
<script src=" https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"> </script>
<script src="/bts/js/bootstrap.min.js"></script>
</body>
</html>