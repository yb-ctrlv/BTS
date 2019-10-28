jQuery.fn.serializeObject = function() {
	var obj = null;
	try {
		if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM") {
			var arr = this.serializeArray();
			if (arr) {
				obj = {};
				jQuery.each(arr, function() {
					obj[this.name] = this.value;
				});
			}
		}
	} catch (e) {
		alert(e.message);
	} finally {
	}
	return obj;
}
var wsUri = "wss://192.168.10.9:444/bts/user/websocket";
var span;
var websocket;
function init() {
	var token = $('meta[name="_csrf"]').attr('th:content');
	var header = $('meta[name="_csrf_header"]').attr('th:content');
	var protocol = {
		token : token,
		header : header,
	}
	span = document.getElementById('MSGcount');
	websocket = new WebSocket(wsUri);

	websocket.onopen = function(evt) {

		onOpen(evt)

	};

	websocket.onmessage = function(evt) {

		onMessage(evt)

	};

	websocket.onerror = function(evt) {

		onError(evt)

	};

}

function onOpen(evt) {
	// 오픈 이벤트
}

function onMessage(evt) {
	// 메세지 올경우 function
	var data = JSON.parse(evt.data);
	var type = data.type;
	if (type == 'count') {
		$('#msgCount').html(data.count);
	} else if (type == 'peers') {
		var peers = data.peers;
		var res = '';
		var friendNicks = $('.friendNick');
		for(var j= 0; j< $(friendNicks).length; j++){
				$(friendNicks[j]).next().html('미접속');
		}
		for (var i = 0; i < peers.length; i++) {
			for(var j= 0; j< $(friendNicks).length; j++){
				var nick = $(friendNicks[j]).html();
				if(nick == peers[i].member_nickname){
					$(friendNicks[j]).next().html('접속중');
				}
			}
			res += '<li><a href="#"><span class="text">'
					+ peers[i].member_nickname + '</span></a></li>'
		}
		$('#peersUl').html(res);

	} else if (type == 'msg') {
		var chatroom = data.chatroom_no;
		var msg = data.msg;
		$('div [title=' + chatroom + ']').append(
				'<div class="receiveTag">' + data.nickname + '</div>'
						+ '<p class="receiveMsg">' + data.msg + '</p>'
						+ '<div class="receiveTag">' + data.regdate + '</div>')
	}
}

function onError(evt) {

}

function addfriend(el){
	var nickname = $(el).prev().html();
	var mymember_no = $("#myMember_no").val();
	var friendul = $('.dropdown');
	$.ajax({
		type: "POST",
		async: false,
		url: "addfriend",
		data: {
			'member_no' : mymember_no,
			'nickname' : nickname,
		},
		dataType : "JSON",				
		beforeSend : function(xhr){
			xhr.setRequestHeader(header, token);
		},
		success : function(data){
			$(friendul).append(
					'<li class="friend"><a href="#"><span class="friendNick">'+nickname+'</span><span class="online">미접속</span></a></li>'
			);
		},
		error: function(){
			alert('친구추가실패!')
		}
	});
}
function addchatroom(el){
	var mymember_no = $("#myMember_no").val();
	var nickname = $(el).prev().prev().html();
}

function doSend(form) {
	// formdata를 data형식으로 받는다..?
	// websocket.send(message);
	var formdata = $(form).serializeObject();
	$(form).children('.chatinput').val('');// input 초기화
	if (formdata.msg == null || formdata.msg == '') {
		console.log('input값 없음!');
		return false;
	}
	var now = new Date();
	var hour = now.getHours();
	var minute = now.getMinutes();
	var str = hour + '시 ' + minute + '분';
	$(form).prev('.chatarea').append(
			$('<p class="sendMsg">' + formdata.msg + '</p>'
					+ '<div class="sendMsgDate">' + str + '</div>'))
	websocket.send(JSON.stringify(formdata));
	var element = document.getElementById("chatarea");
	element.scrollTop = element.scrollHeight;

	return false;
}

$(function(){
	init();
})