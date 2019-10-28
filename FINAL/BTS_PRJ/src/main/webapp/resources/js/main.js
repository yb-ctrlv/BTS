
$(document).ready(function() {
	
	var roominfomsg = $("#roominfomsg").val();
	if(roominfomsg != null && roominfomsg != ''){
		alert(roominfomsg); 
		location.href = "/bts/user/main";
	}
		
	$(".dropdownli>a").click(function() {
		var submenu = $(this).next("ul");
		if (submenu.is(":visible")) {
			submenu.slideUp();
		} else {
			submenu.slideDown();
		}
	})
	$(".dropdownli2>a").click(function() {

		var submenu2 = $(this).next("ul");
		if (submenu2.is(":visible")) {
			submenu2.slideUp();
		} else {
			submenu2.slideDown();
		}
	})
	$(".dropdownli3>a").click(function() {
		var submenu3 = $(this).next("ul");
		if (submenu3.is(":visible")) {
			submenu3.slideUp();
		} else {
			submenu3.slideDown();
		}
	})
	$(".dropdownli2li>a").click(function() {
		$(".chattingdown").each(function(index, item) {
			$(this).slideUp();
		})
		if ($(this).next("div").is(":visible")) {
			$(this).next("div").slideUp();
		} else {
			$(this).next("div").slideDown();
			var element = document.getElementById("chatarea");
			element.scrollTop = element.scrollHeight;
		}
	})
	
	$(".searchbardivbtn").click(function(){
		var gsWin = window.open("about:blank","list","width=800px,hegiht=400");
	})
})
/*
function search(){
	var gsWin = window.open("about:blank","list","width=500,hegiht=500");
	var frm = $("#searchform");
	alert(frm);
	frm.action ='/bts/user/mongo';
	frm.target ="list";
	frm.method ="post";
	frm.submit();
}*/
	

