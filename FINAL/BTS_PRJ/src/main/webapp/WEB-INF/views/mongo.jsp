<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardlist</title>
<style type="text/css">
td {
text-align :center;
border: 1px solid rgba(0,0,0,0.1);

}
th{
color : white;

}
tr{
border-bottom: 1px solid rgba(255,255,255,0.5);
}

tr:hover{
	cursor: pointer;
	background-color: #fff;
}
</style>
</head>
<body style="background-color : rgba(200,200,200,0.8);">

	<h1 style = "text-align: center; width : 60%; margin : 20px auto; border : 1px solid rgba(0,0,0,0.5); border-radius: 20px;
		background-color: #eee;" >책 목록</h1>

	<table style="text-align: cneter; border-radius: 20px; border : 1px solid rgba(0,0,0,0.5);
		background-color: #eee; width : 60%; margin: 0 auto;">
	<col width="50px"/>
	<col width="80px"/>
	<col width="400px"/>
	<col width="80px"/>

		<c:choose>
			<c:when test="${empty list }">

				<tr>
					<td colspan="4">------------작성된 글이 존재하지 않습니다------------</td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr style="background-color: gray; border-bottom: 1px solid black; text-align: cneter;
					border-radius: 20px;">
					<th style="border-top-left-radius: 20px;">RANK</th>
					<th>BOOK</th>
					<th>TITLE</th>
					<th style="border-top-right-radius: 20px;">VALUE</th>
				</tr>
				<c:forEach items="${list }" var="dto">
					<tr class="trtr" style="text-align: cneter;">
						<td style = "font-size: 20px;">${dto.rank }</td>
						<td>
							<img alt="" src="${dto.src }" width = "80px" height = "120px">
						</td>
						<td>${dto.title }</td>
						<td>${dto.value }</td>
					</tr>
				</c:forEach>
			</c:otherwise>
					
		</c:choose>
	</table>
</body>
</html>