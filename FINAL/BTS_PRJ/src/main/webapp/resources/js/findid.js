
var token;
var header;
window.onload=function(){
	token = $('meta[name="_csrf"]').attr('th:content');
	header = $('meta[name="_csrf_header"]').attr('th:content');
}


function emailChk(){
	var email = $("#email").val();
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
				$.ajax({
					type: "POST",
					url: "/bts/user/findidforemail",
					dataType: "JSON",
					data : "email="+email,
					beforeSend : function(xhr){
						xhr.setRequestHeader(header, token);            
						},
					success : function(data){
						console.log("통신성공");
					},
					error: function(data, status, error){
						console.log(data);
						console.log(status);
						console.log(error);
						console.log("통신실패");
					}
				});
				
				alert("등록된 EMAIL로 가입한 ID를 보내드렸습니다!");
				location.href="/bts/user/loginform/";
				return false;
			}else{
				alert("등록된 EMAIL이 없습니다!");
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



