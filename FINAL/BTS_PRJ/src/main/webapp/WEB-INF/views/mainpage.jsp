<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="_csrf" th:content="${_csrf.token}"/>
<meta name="_csrf_header" th:content="${_csrf.headerName}"/>
<link href="/bts/resources/css/css.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/bts/resources/js/jquery-3.4.1.js"></script>
<script type="text/javascript" src="/bts/resources/js/main.js"></script>
<sec:authorize access="isAuthenticated()">
	<script type="text/javascript" src="/bts/resources/js/websocket.js"></script>
</sec:authorize>
</head>
<body>

	<div class="wrapper">
		<sec:authentication property="principal" var="user" /> 
		<div class="side-menu">
			<div class="profile-all-wrap">
				<div class="profile-alarm-wrap">
					<div class="profile-alarm">
						<sec:authorize access="isAuthenticated()">
							<sec:authentication property="principal" var="user" />
							<input id="myMember_no" type="hidden" value="${user.member_no }">
							<p><span id="msgCount">0</span>건의 새소식이 있습니다.</p>
						</sec:authorize>
						<sec:authorize access="isAnonymous()">
						<p><a href="/bts/loginform" style="color : black;">로그인 해주세요.</a></p>
						</sec:authorize>
					</div>
				</div>
				<div class="profile-wrap">
					<div class="profile">
						<a>
							<img class="profileImage" src="../images/basicImage.jpg"/>
						</a>
					</div>
				</div>
				<div>
					<sec:authorize access="isAuthenticated()">
						<form action='<c:url value='/logout'/>' method="post">
	   					   <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
	  					   <input class="logoutBtn" type="submit" value="LOGOUT">
	   					</form>
	   				</sec:authorize>
				</div>
			</div>
			<div class="menu">
				<ul>
					<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li><a href="http://192.168.10.9:3030/loginform"><span class="text">관리자페이지</span></a></li>
					</sec:authorize>
					<li><a href="/bts/member/makeroomform"><span class="text">방 개설하기</span></a></li>
					<li><a href="#"><span class="text"> 즐겨찾기</span></a></li>
					<li class = dropdownli>
						<a href="#">
							<span class="text"> 
								내정보 관리하기 
							</span>
						</a>
						<ul class="dropdown">
							<li><a href="/bts/member/infopwcheck"><span class="text"> 내 정보수정</span></a></li>
							<li><a href="#"><span class="text"> 회원 탈퇴</span></a></li>
							<li><a href="#"><span class="text"> 고객지원</span></a></li>
							<li><a href="#"><span class="text"> 캐쉬충전</span></a></li>
						</ul>
					</li>
				</ul>
			</div>
			
			<div class="menu">
				<ul>
					<li class = dropdownli>
						<a href="#">
							<span class="text"> 
								친구목록
							</span>
						</a>
						<ul class="dropdown">
						<c:choose>
							<c:when test="${not empty friendList }">
								<c:forEach items="${friendList }" var="dto">
									<li class="friend"><a href="#"><span class="friendNick">${dto.member_nickname }</span><span class="online">미접속</span></a></li>
								</c:forEach>
							</c:when>
							<c:otherwise>
									<li><a href="#"><span class="text">나는 친구가 없습니다.</span></a></li>
							</c:otherwise>
						</c:choose>
						</ul>
					</li>
					<li class = dropdownli2>
						<a href="#">
							<span class="text"> 
								채팅목록
							</span>
						</a>
						<ul class="dropdownli2ul">
						<c:choose>
							<c:when test="${empty chatinfoList }">
								<li class ="dropdownli2li"><a href="#"><span class="text">채팅방이 없습니다.</span></a></li>
							</c:when>
							<c:otherwise>
								<c:forEach items="${chatinfoList }" var="infodto">
									<li class ="dropdownli2li"><a href="#"><span class="text">${infodto.chat_title }</span></a>
										<div class="chattingdown">
											<div id='chatarea' class="chatarea" style="height : 200px;" title="${infodto.chatroom_no }">
												<c:choose>
													<c:when test="${empty messageList}"></c:when>
													<c:otherwise>
														<c:forEach items="${messageList }" var="roomlist">
															<c:forEach items="${roomlist }" var="msg">
															<c:if test="${msg.chatroom_no eq infodto.chatroom_no }">
																<c:choose>
																	<c:when test="${msg.member_no eq user.member_no }">
																		<p class="sendMsg">${msg.message_content }</p>
																		<div class="sendMsgDate">${msg.message_regdate }</div>
																	</c:when>
																	<c:otherwise>
																		<div class="receiveTag">${msg.message_sender }</div>
																		<p class="receiveMsg">${msg.message_content }</p>
																		<div class="receiveTag">${msg.message_regdate }</div>
																	</c:otherwise>
																</c:choose>
															</c:if>
															</c:forEach>
														</c:forEach>
													</c:otherwise>
												</c:choose>
											</div>
											<form class="formdata" method="post" onsubmit="return doSend(this);">
												<input type="hidden" name="chatroom_no" value="${infodto.chatroom_no }">
												<input type="hidden" name="member_no" value="${infodto.member_no }">
												<input class = "chatinput" type="text" name="msg">
												<input type="submit" >
												<div style = "clear : both;"></div>
											</form>	
										</div>
									</li>
								</c:forEach>
							</c:otherwise>
						</c:choose>
						</ul>
					</li>
					<li class="dropdownli3"><a href="#"><span class="text">접속자목록</span></a>
						<ul id="peersUl" class="dropdownli3ul">
						</ul>
					</li>
				</ul>
			</div>
			<div class="searchbar" style="width : 240px;">
			<div> <p style = "color : white; text-align: center; font-weight: bold; border-top: 1px solid rgba(255,255,255,0.3);">책 검색</p> </div>
				<div class="searchbardiv">
					<form id = "searchform" name="searchform" style="width : 100%;" action="/bts/user/mongo" method="post" target ="list">	
					<div style="width : 100%; height: 40px; float : left;">
					<input type="text" id="search" type="text" name="bookname" placeholder="Search Book" />
					<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
					<button class="searchbardivbtn">
						<img src="/bts/resources/images/search.png" alt="대충 돋보기이미지" width="35px"
									height="35px" onclick="search()" />
					</button>
					</div>
					</form>
				</div>
			</div>
		</div>
		<div class="main-content">
			<div class="topmenu">
				<div class="searchbar-wrap">	
					<div class = "logo">
						<div style = "margin-left: 140px;">
							<div style = "width : 420px; margin : 0 auto;">
							<a href = "#">
								<img alt="btslogo" src="/bts/resources/images/BTS_logo.png" width = "100px" height = "100px">
								<img alt="btslogo" src="/bts/resources/images/BTSLogo2.png" width = "200px">
								<img alt="btslogo" src="/bts/resources/images/BTSLogo4.png" width = "100px">
							</a>
							</div>
						</div>	
					</div>
				</div>
				<!--
				<div class="headerdiv2">
					<div class="headerdiv2div">
						<ul class = "headerdiv2divul" >
							<li class = "headerdiv2divulli" onclick = "location.href='pagemoving.do?command=musicgenre'">
								<a class = "headerdiv2divullia" href ="pagemoving.do?command=musicgenre"> 전체 </a>
							</li>
							<li class = "headerdiv2divulli" onclick = "location.href='pagemoving.do?command=broadcstingtop'">
								<a class = "headerdiv2divullia" href ="pagemoving.do?command=broadcstingtop"> 오픈스터디 </a>
							</li>
							<li class = "headerdiv2divulli" onclick = "location.href='pagemoving.do?command=concerthallmain'">	
								<a class = "headerdiv2divullia" href ="pagemoving.do?command=concerthallmain"> 모의 면접 </a>
								<ul class = "headerdiv2divulliul">
									<li><a href="pagemoving.do?command=concerthallmain"> 신청하기 </a></li>
									<li><a href="pagemoving.do?command=concerthallreservation"> 방송보기 </a></li>
								</ul>
							</li>
							<li class = "headerdiv2divulli" onclick = "location.href='pagemoving.do?command=concerthallmain'">	
								<a class = "headerdiv2divullia" href ="pagemoving.do?command=concerthallmain"> 영상 보기 </a>
							</li>
							<li class = "headerdiv2divulli" 
							style = "border-right : 1px solid; border-right-color:rgba(200,200,200,0.5);"
							onclick = "location.href='pagemoving.do?command=servicecenter'">
								<a class = "headerdiv2divullia" href ="pagemoving.do?command=servicecenter">실시간 강의</a>
							</li>
						</ul>
					</div>
				</div>
				-->
			</div>
			<div class="main-section">
				<div class="room-create" onclick ="roomcreate();">
					<div class ="room-create-letter">
						<p><a href="/bts/member/makeroomform">여러분의 방을 개설해주세요.</a></p>
					</div>
				</div>
				<div class="section-title">방목록</div>
				<div class="card-wrap">
					<c:choose>
						<c:when test="${empty roomlist }">
							<div class="card-content">
								<h1>생성된 방이 없습니다.</h1>
							</div>
						</c:when>
						<c:otherwise>
							<c:forEach items="${roomlist }" var="dto">
								<c:choose>
									<c:when test="${dto.room_nowval < dto.room_maxval }">
										<div class="card">
					                  		<div class="card-content">
					                     		<a href="/bts/member/joinroom?room_no=${dto.room_no }"><img class ="roomImage" alt="방송이미지" src="${dto.room_image }"></a>
					                  		</div>
					                  		<div class="card-info">${dto.room_name } [${dto.room_nowval } / ${dto.room_maxval }]</div>
					               		</div>
									</c:when>
									<c:otherwise>
										<div class="card">
					                  		<div class="card-content">
					                     		<img class ="roomImage" alt="방송이미지" src="${dto.room_image }">
					                  		</div>
					                  		<div class="card-info">${dto.room_name } [${dto.room_nowval } / ${dto.room_maxval }](FULL)</div>
					               		</div>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		
	</div>
<div id="friendDropdown" style="display: none;">
	<ul>
		<li>친구이름</li>
		<li>친구삭제</li>
		<li>1:1채팅하기</li>
	</ul>
</div>
<div id="chatDropdown" style="display:none;">
	<ul>
		<li>채팅방수정</li>
		<li>채팅방나가기</li>
	</ul>
</div>
<div id="userDropdown" style="display: none;">
	<ul>
		<li>1:1채팅</li>
		<li>친구추가</li>
	</ul>
</div>



<c:choose>
	<c:when test="${roominfomsg != null }">
		<input type="hidden" id="roominfomsg" value="${roominfomsg }">
	</c:when>
	<c:otherwise>
		<input type="hidden" id="roominfomsg" value="">
	</c:otherwise>
</c:choose>
</body>
</html>

<!-- <div style="text-align: center; margin-top:100px;background:#3a3a63; border:1px solid white; ">
						<a href="/bts/loginform">로그인을 먼저해주세요</a>
					</div> -->