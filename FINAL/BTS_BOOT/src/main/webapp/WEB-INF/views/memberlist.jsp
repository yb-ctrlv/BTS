<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="_csrf" th:content="${_csrf.token}"/>
<meta name="_csrf_header" th:content="${_csrf.headerName}"/>
<title>Insert title here</title>
<script type="text/javascript" src="/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/js/memberlist.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<link rel="stylesheet"  href="/css/bootstrap.css" type="text/css">
<link rel="stylesheet"  href="/css/memberlist.css" type="text/css">

</head>
<body>
	<h1>BTS 멤버리스트</h1>
	<br/>
	<form action="/memberlist" class="form-inline" role="form" method="post">
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		<input type="hidden" name="page" value="1">
		<c:choose>
			<c:when test="${empty id }">
				<input type="text" name="id" class="form-control" placeholder="아이디를 입력해주세요"  />
			</c:when>
			<c:otherwise>
				<input type="text" name="id" class="form-control" placeholder="아이디를 입력해주세요" value="${id }" />
			</c:otherwise>
		</c:choose>
		<input type="submit" class="btn btn-primary" value="검색" />
		<input type="button" class="btn btn-primary" value="목록" onclick="location.href='/memberlist?page=1'"/>
		<input type="button" class="btn btn-primary" value="관리자페이지로" onclick="location.href='/'"/>
	</form>
	<br />
	<br />
	
	<form action="" method="post" id="multiChk">
	<input type="hidden" name="page" value="${pageNo }">
	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
	<c:if test="${not empty id }">
		<input type="hidden" name="id" value="${id }">
	</c:if>
	<table class="table table-striped">
		<thead>
			<tr>
				<th><input type="checkbox" name="all" onclick="allChk(this.checked);"></th>
				<th>ID</th>
				<th>닉네임</th>
				<th>이메일</th>
				<th>가입날짜</th>
				<th>활성/비활성</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty list }">
					<tr>
						<td colspan="6">----------------가입한 회원이 없습니다---------------</td>
					</tr>
				</c:when>
				<c:otherwise>
				<c:forEach items="${list }" var="dto">
					<tr>
						<td><input type="checkbox" name="member_no" value="${dto.member_no }"></td>
						<td>${dto.member_id }</td>
						<td>${dto.member_nickname }</td>
						<td>${dto.member_email }</td>
						<td><fmt:formatDate value="${dto.member_regdate }" pattern="yyyy-MM-dd" /></td>
						<td>
						<c:choose>
							<c:when test="${dto.member_enable == true}">
								<input type="button" class="btn btn-primary" name="disableOne" value="활성화" />
							</c:when>
							<c:otherwise>
								<input type="button" class="btn" name="enableOne" value="비활성화" />
							</c:otherwise>
						</c:choose>
						<td>
					</tr>				
				</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<br />
	<input type="button" class="btn pull-right" onclick="disable();" value="비활성" />
	<input type="button" class="btn btn-primary pull-right" onclick="enable();" value="활성화" />
	</form>
	
	<c:choose>
		<c:when test="${empty id }">
			<div class="pagination">
				<div class="pagepoint">
					<c:set var="prevPage" value="${absolutePage-blockCount}"></c:set>
						<c:choose>
							<c:when test="${prevPage >0}">
								<a href="memberlist?page=${prevPage }" class="page gradient">◀</a>
							</c:when>
							<c:otherwise>
								<a href="memberlist?page=1" class="page gradient">◀</a>
							</c:otherwise>
						</c:choose>
						<c:forEach begin="${absolutePage }" end="${endPage }" var="i">
							<c:choose>
								<c:when test="${i eq pageNo}">
									<a href="memberlist?page=${i}"><span class="page active">${i}</span></a>
								</c:when>
								<c:otherwise>
									<a href="memberlist?page=${i}" class="page gradient">${i}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					<c:set var="nextPage" value="${absolutePage + blockCount }"></c:set>
						<c:choose>
							<c:when test="${nextPage < totalPage}">
								<a href="memberlist?page=${nextPage }" class="page gradient">▶</a>
							</c:when>
							<c:otherwise>
								<a href="memberlist?page=${totalPage}" class="page gradient">▶</a>
							</c:otherwise>
						</c:choose>
				</div>
			</div>	
		</c:when>
		<c:otherwise>
			<div class="pagination">
				<div class="pagepoint">
					<c:set var="prevPage" value="${absolutePage-blockCount}"></c:set>
						<c:choose>
							<c:when test="${prevPage >0}">
								<a href="memberlist?page=${prevPage }&id=${id }" class="page gradient">◀</a>
							</c:when>
							<c:otherwise>
								<a href="memberlist?page=1&id=${id }" class="page gradient">◀</a>
							</c:otherwise>
						</c:choose>
						<c:forEach begin="${absolutePage }" end="${endPage }" var="i">
							<c:choose>
								<c:when test="${i eq pageNo}">
									<a href="memberlist?page=${i}&id=${id }"><span class="page active">${i}</span></a>
								</c:when>
								<c:otherwise>
									<a href="memberlist?page=${i}&id=${id }" class="page gradient">${i}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					<c:set var="nextPage" value="${absolutePage + blockCount }"></c:set>
						<c:choose>
							<c:when test="${nextPage < totalPage}">
								<a href="memberlist?page=${nextPage }&id=${id }" class="page gradient">▶</a>
							</c:when>
							<c:otherwise>
								<a href="memberlist?page=${totalPage}&id=${id }" class="page gradient">▶</a>
							</c:otherwise>
						</c:choose>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	
	
</body>
</html>