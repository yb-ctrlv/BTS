function allChk(bool) {
	if (bool == true) {
		$("input[name=member_no]").prop("checked", true);
	} else {
		$("input[name=member_no]").prop("checked", false);
	}
}

$(function() {
	$("input[name=member_no]").click(function() {
		if ($("input[name=member_no]:checked").length == $("input[name=member_no]").length) {
			$("input[name=all]").prop("checked", true);
		} else {
			$("input[name=all]").prop("checked", false);
		}
	});
});
	
function enable(){
	if($("#multiChk input:checked").length == 0){
		alert("하나 이상 체크해주세요!");
		return false;
	}else{
		var enableConfirm = confirm("해당 계정들을 활성화 하시겠습니까?");
		if(enableConfirm == true){
			$("#multiChk").attr("action", "/multienable");
			$("#multiChk").submit();
		}else{
			return false;
		}
	}
}
	
function disable(){
	if($("#multiChk input:checked").length == 0){
		alert("하나 이상 체크해주세요!");
		return false;
	}else{
		var disableConfirm = confirm("해당 계정들을 비활성화 하시겠습니까?");
		if(disableConfirm == true){
			$("#multiChk").attr("action", "/multidisable");
			$("#multiChk").submit();
		}else{
			return false;
		}
	}
}


$(function(){
	$("input[name=disableOne]").click(function(){
		var disableOneConfirm = confirm("해당 계정을 비활성화 하시겠습니까?");
		if(disableOneConfirm == true){
			$(this).parent().siblings().eq(0).find("*").prop("checked", true);
			$("#multiChk").attr("action", "/multidisable");
			$("#multiChk").submit();
		}else{
			return false;
		}
	});
	
	$("input[name=enableOne]").click(function(){
		var enableOneConfirm = confirm("해당 계정을 활성화 하시겠습니까?");
		if(enableOneConfirm == true){
			$(this).parent().siblings().eq(0).find("*").prop("checked", true);
			$("#multiChk").attr("action", "/multienable");
			$("#multiChk").submit();
		}else{
			return false;
		}
	});
	
});


