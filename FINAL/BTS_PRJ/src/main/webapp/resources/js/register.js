
var token;
var header;
window.onload=function(){
	token = $('meta[name="_csrf"]').attr('th:content');
	header = $('meta[name="_csrf_header"]').attr('th:content');
}

var getIdchk = /^[a-z0-9]{5,14}$/;
var getPwchk = /^(?=.*[a-zA-Z!@#$%^*+=-])(?=.*[0-9]).{8,20}$/;
var getNickchk = /^[ㄱ-ㅎ가-힣A-Za-z0-9!@#$%^*+=-]{1,14}$/;
var getEmailchk = /^[a-z0-9A-Z@.]{1,30}$/;
var idConfirm = false;
var pwConfirm = false;
var emailConfirm = false;
var mailConfirm = false;
var nickConfirm = false;
var email = " ";



function idChk(){		
	var id = $("input[name=member_id]").val();
	idConfirm = false;
	
	if(id.length < 5 || id.length > 14){
		$("#idck").css('color', 'red');
		$("#idck").html("id는 5~14 글자로 입력해주세요");
		$("input[name=member_id]").focus();
		idConfirm = false;
		return false;
	}		
	if(!getIdchk.test(id)){
		$("#idck").css('color', 'red');
		$("#idck").html("공백, 특수문자, 한글, 대문자는 사용불가능합니다.");
		$("input[name=member_id]").focus();
		idConfirm = false;
		return false;
	}
	
		  
	$.ajax({
		type: "POST",
		url: "/bts/user/idchk",
		dataType : "JSON",
		data : "id="+id,
		beforeSend : function(xhr){
			xhr.setRequestHeader(header, token);            
			},
		success : function(data){				
			if(data == 1){
				$("#idck").css('color', 'red');
				$("#idck").html("중복된 id 입니다");
				$("input[name=member_id]").focus();
				idConfirm = false;
				return false;
			}else{
				$("#idck").css('color', 'blue');
				$("#idck").html("사용가능한 id 입니다");
				$("input[name=member_pw]").focus();
				idConfirm = true;
				return false;
			}
			
		},
		error: function(data, status, error){
			console.log(data);
			console.log(status);
			console.log(error);
			console.log("통신실패");
		}
	});
}

function pwChk(){
	var pw = $("input[name=member_pw]").val();
	pwConfirm = false;
	
	if(!getPwchk.test(pw)){
		$("#pwck").css('color', 'red');
		$("#pwck").html("문자와 숫자를 혼합하여 8~20자로 입력하세요");
		$("input[name=member_pw]").focus();
		pwConfirm = false;
		return false;
	}else{
		$("#pwck").css('color', 'blue');
		$("#pwck").html("사용가능한 password 입니다.");
		$("input[name=member_email]").focus();
		pwConfirm = true;
		return false;			
	}
	
}


function mailChk(){
	
	var email = $("#email").val();
	
	if($("#email").val() == ""){
		$("#emailck").css('color', 'red');
		$("#emailck").html("email을 입력해주세요");
		$("#email").focus();
		mailConfirm = false;
		return false;
	}
	
	if(!getEmailchk.test($("#email").val())){
		$("#emailck").css('color', 'red');
		$("#emailck").html("형식에 맞게 입력해주세요 ex) admin@gmail.com");
		$("#email").focus();
		mailConfirm = false;
		return false;
	}
	
	if($("#email").val().indexOf("@") == -1 || $("#email").val().indexOf(".") == -1 ){
		$("#emailck").css('color', 'red');
		$("#emailck").html("형식에 맞게 입력해주세요 ex) admin@gmail.com");
		$("#email").focus();
		mailConfirm = false;
		return false;
	}
	
	$.ajax({
		type: "POST",
		url: "/bts/user/emailck",
		dataType : "JSON",
		data : "email="+email,
		beforeSend : function(xhr){
			xhr.setRequestHeader(header, token);            
			},
		success : function(data){				
			if(data == 1){
				$("#emailck").css('color', 'red');
				$("#emailck").html("중복된 email 입니다");
				$("#email").focus();
				mailConfirm = false;
				return false;
			}else{
				$("#emailck").css('color', 'blue');
				$("#emailck").html("사용가능한 email 입니다.");
				mailConfirm = true;
				$("input[name=member_nickname]").focus();
				return false;
			}
			
		},
		error: function(data, status, error){
			console.log(data);
			console.log(status);
			console.log(error);
			console.log("통신실패");
		}
	});
	
	
	
}



function emailChk(){
	
	
	
	if(mailConfirm == false){
		alert("email을 형식에 맞게 입력해주세요");
		$("#email").focus();
		emailConfirm = false;
		return false;
	}else{
		email = $("#email").val();
		window.open("/bts/user/emailchkpage","emailChkPage","width=500, height=150, left=500, top=250", "resizeble=no", "alwaysReised=yes");
	}
	
}


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
	if(!idConfirm){
		alert("id를 형식에 맞게 입력해주세요");
		$("input[name=member_id]").focus();
		return false;
	}
	if(!pwConfirm){
		alert("pw를 형식에 맞게 입력해주세요");
		$("input[name=member_pw]").focus();
		return false;
	}
	if(!nickConfirm){
		alert("닉네임을 형식에 맞게 입력해주세요");
		$("input[name=member_nickname]").focus();
		return false;
	}
	if(!emailConfirm){
		alert("email 인증을 완료해주세요");
		return false;
	}
	
	alert("회원가입 성공!");
	return true;
}