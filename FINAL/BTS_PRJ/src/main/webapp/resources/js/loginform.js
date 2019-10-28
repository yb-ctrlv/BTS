
var token;
var header;
window.onload=function(){
	token = $('meta[name="_csrf"]').attr('th:content');
	header = $('meta[name="_csrf_header"]').attr('th:content');
	
	 var key = getCookie("key");
	    $("#inputId").val(key); 
	     
	    if($("#inputId").val() != ""){ // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
	        $("#idSaveCheck").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
	    }
	     
	    $("#idSaveCheck").change(function(){ // 체크박스에 변화가 있다면,
	        if($("#idSaveCheck").is(":checked")){ // ID 저장하기 체크했을 때,
	            setCookie("key", $("#inputId").val(), 7); // 7일 동안 쿠키 보관
	        }else{ // ID 저장하기 체크 해제 시,
	            deleteCookie("key");
	        }
	    });
	     
	    // ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
	    $("#inputId").keyup(function(){ // ID 입력 칸에 ID를 입력할 때,
	        if($("#idSaveCheck").is(":checked")){ // ID 저장하기를 체크한 상태라면,
	            setCookie("key", $("#inputId").val(), 7); // 7일 동안 쿠키 보관
	        }
	    });
}



function setCookie(cookieName, value, exdays){
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + exdays);
    var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
    document.cookie = cookieName + "=" + cookieValue;
}
 
function deleteCookie(cookieName){
    var expireDate = new Date();
    expireDate.setDate(expireDate.getDate() - 1);
    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
}


function getCookie(cookieName) {
    cookieName = cookieName + '=';
    var cookieData = document.cookie;
    var start = cookieData.indexOf(cookieName);
    var cookieValue = '';
    if(start != -1){
        start += cookieName.length;
        var end = cookieData.indexOf(';', start);
        if(end == -1)end = cookieData.length;
        cookieValue = cookieData.substring(start, end);
    }
    return unescape(cookieValue);
}


// 사용할 앱의 JavaScript 키를 설정해 주세요.
    Kakao.init('6dfb2d9db2487284c58fdec964de9d2e');
    // 카카오 로그인 버튼을 생성합니다.
    function kakaoLogin(){
    	Kakao.Auth.loginForm({
    		   success: function(authObj) {
    		     Kakao.API.request({
    		       url: '/v1/user/me',
    		       success: function(res) {
    		             
    		             var member_id = "KAKAOSNSID"+res.id;
    		             var member_pw = "KAKAOSNSID"+res.id;
    		             var member_email = "KAKAOEMAIL"+res.kaccount_email;
    		             var member_nickname = "KAKAONICK"+res.properties['nickname'];
    		             
    		             $.ajax({
    		            	type: "POST",
    		            	url: "snslogin?member_id="+member_id+"&member_pw="+member_pw+"&member_email="+member_email+"&member_nickname="+member_nickname,
    		            	dataType : "Json",
    		            	beforeSend : function(xhr){
    		    				xhr.setRequestHeader(header,token);
    		    			},
    		    			success : function(data){
    		    				$("#snsId").val(member_id);
    		    				$("#snsPw").val(member_pw);
    		    				$("#snsLogin").submit();
    		    			},error : function(data, status, error){
    		    				
    		    				console.log(data);
    		    				console.log(status);
    		    				console.log(error);
    		    				
    		    			}
    		             });
    		           }
    		         });
    		       },
    		       fail: function(error) {
    		         alert(JSON.stringify(error));
    		       }
    		     });    	
    	
    }
   
    
    
  	var naver_id_login = new naver_id_login("3r6MK9J3LROu5h50Wljj", "https://192.168.10.9:444/bts/user/naverlogin");
  	var state = naver_id_login.getUniqState();
  	naver_id_login.setPopup();
  	naver_id_login.setButton("green", 3, 49);
  	naver_id_login.setDomain("https://192.168.10.9:444/bts/user/naverlogin");
  	naver_id_login.setState(state);
  	naver_id_login.init_naver_id_login();
