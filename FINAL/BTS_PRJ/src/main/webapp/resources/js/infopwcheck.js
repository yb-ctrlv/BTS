
var token;
var header;
window.onload=function(){
	token = $('meta[name="_csrf"]').attr('th:content');
	header = $('meta[name="_csrf_header"]').attr('th:content');
}
	function submitChk(){
		var submitConfirm = false;
		if($("input[name=member_pw]").val() == ""){
			$("#errorMsg").css('color', 'red');
			$("#errorMsg").html("패스워드를 입력해주세요!");
		}else{
			$.ajax({
				type: "POST",
				async: false,
				url: "pwchk?member_id="+$("input[name=member_id]").val()
						+"&member_pw="+$("input[name=member_pw]").val(),
				dataType : "JSON",				
				beforeSend : function(xhr){
					xhr.setRequestHeader(header, token);
				},
				success : function(data){
					if(data == false){
						$("#errorMsg").css('color', 'red');
						$("#errorMsg").html("패스워드가 틀렸습니다!");
					}else{
						submitConfirm = true;
					}
				},
				error: function(){
					console.log("통신실패");
				}
			});
		}
		if(submitConfirm == true){
			return true;
		}else{
			return false;
		}
		
	}
