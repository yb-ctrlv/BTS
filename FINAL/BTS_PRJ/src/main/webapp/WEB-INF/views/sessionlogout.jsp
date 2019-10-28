<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="_csrf" th:content="${_csrf.token}"/>
<meta name="_csrf_header" th:content="${_csrf.headerName}"/>
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>
<script type="text/javascript">

	$(function(){
		sessionLogout();		
	});
	
	function sessionLogout(){
		$.ajax({
			type: "POST",
			url: "/bts/logout",
			dataType : "HTML",				
			beforeSend : function(xhr){
				xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
			},
			success : function(data){
				console.log("통신 성공");
				location.href="../user/logoutpage?id=${id }&pw=${pw }";
			},
			error: function(){
				console.log("통신 실패");
			}
		});
	}

</script>
</head>	
<body>


</body>
</html>