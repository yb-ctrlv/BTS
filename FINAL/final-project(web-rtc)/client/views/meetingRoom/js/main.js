//나가기 버튼 눌렀을때
    function exitRoom(){
       
        location.href="https://192.168.10.9:444/bts/user/main";
       
        /*
        $.ajax({
            type: "GET",
            url: "https://121.140.94.175:443/bts/member/outroom",
            dataType: "jsonp",
            async: false,
            data: {'room_addr': Request('room_addr') },
            success : function(data){
            },
            error:function(request,status,error){
                location.href="https://121.140.94.175:443/bts/user/main";
            }
        });
        */
    }

//강제로 창 종료시
/*
    $(window).on("unload", function(e) {
        $.ajax({
            type: "GET",
            url: "https://121.140.94.175:443/bts/member/outroom",
            dataType: "jsonp",
            async: false,
            data: {'room_addr': Request('room_addr') },
            success : function(data){
                self.close();
            },
            error:function(request,status,error){
                self.close();
            }
        });
    });
*/

//강제로 탭을 닫을때
    $(window).on("beforeunload", function() { 
        $.ajax({
            type: "GET",
            url: "https://192.168.10.9:444/bts/member/outroom",
            dataType: "jsonp",
            async: false,
            data: {'room_addr': RoomUrl },
            success : function(data){
                self.close();
            },
            error:function(request,status,error){
                self.close();
            }
        });
    })

    
// ......................................................
// ..................RTCMultiConnection Code.............
// ......................................................

var connection = new RTCMultiConnection();

let RoomUrl = $('#room_addr').val();
const socket = io();
const userId = $('#nickname').val();
const room_maxval = $('#room_maxval').val();
const chatArea = $('#chatArea');
const messageButton = $('#b');
// by default, socket.io server is assumed to be deployed on your own URL
connection.socketURL = 'https://rtcmulticonnection.herokuapp.com:443/';

connection.extra = {
    fullName : userId,
    joinedAt : (new Date).toLocaleString(),
    username : 'Your UserName',
    whatever : true 
  };

connection.session = {
    audio: true,
    video: true
};

connection.sdpConstraints.mandatory = {
    OfferToReceiveAudio: true,
    OfferToReceiveVideo: true
};
    const container = $('#container');
    const joinButton = $('#join');
    const ready = $('#ready');
    function getUserMedia() {
        navigator.getUserMedia(
            {
                audio : true,
                video : {
                    width : 1280,
                    height : 720
                },
            },
            (stream) => {
               localMyStream = stream;
               console.log(localMyStream);               
               document.getElementById('readyVideo').srcObject = localMyStream;
            }, 
            e => {
                console.log(e);
            }
        )
    };

    function setRoomUrl() {
        if (location.hash.length < 2){
            location.hash = '#' + RoomUrl;
        }
    };

    function init(){
        container.css('visibility','hidden');
        setRoomUrl();
        getUserMedia();
        RoomUrl = location.hash.replace(/\/|:|#|%|\.|\[|\]/g, '');
    };
    init();

    joinButton.click(() => {
        ready.hide();
        container.css('visibility','visible');        
        connection.openOrJoin(RoomUrl);
        connection.sessionid = RoomUrl;
        document.getElementById('readyVideo').srcObject = null;
    });


// STAR_FIX_VIDEO_AUTO_PAUSE_ISSUES
var bitrates = 512;
var resolutions = 'Ultra-HD';
var videoConstraints = {};

if (resolutions == 'HD') {
    videoConstraints = {
        width: {
            ideal: 1280
        },
        height: {
            ideal: 720
        },
        frameRate: 30
    };
}

if (resolutions == 'Ultra-HD') {
    videoConstraints = {
        width: {
            ideal: 1920
        },
        height: {
            ideal: 1080
        },
        frameRate: 30
    };
}

connection.mediaConstraints = {
    video: videoConstraints,
    audio: true
};


var CodecsHandler = connection.CodecsHandler;

connection.processSdp = function(sdp) {
    var codecs = 'vp8';
    
    if (codecs.length) {
        sdp = CodecsHandler.preferCodec(sdp, codecs.toLowerCase());
    }

    if (resolutions == 'HD') {
        sdp = CodecsHandler.setApplicationSpecificBandwidth(sdp, {
            audio: 128,
            video: bitrates,
            screen: bitrates
        });

        sdp = CodecsHandler.setVideoBitrates(sdp, {
            min: bitrates * 8 * 1024,
            max: bitrates * 8 * 1024,
        });
    }

    if (resolutions == 'Ultra-HD') {
        sdp = CodecsHandler.setApplicationSpecificBandwidth(sdp, {
            audio: 128,
            video: bitrates,
            screen: bitrates
        });

        sdp = CodecsHandler.setVideoBitrates(sdp, {
            min: bitrates * 8 * 1024,
            max: bitrates * 8 * 1024,
        });
    }

    return sdp;
};
// END_FIX_VIDEO_AUTO_PAUSE_ISSUES

// https://www.rtcmulticonnection.org/docs/iceServers/
// use your own TURN-server here!
connection.iceServers = [{
    'urls': [
        'stun:stun.l.google.com:19302',
        'stun:stun1.l.google.com:19302',
        'stun:stun2.l.google.com:19302',
        'stun:stun.l.google.com:19302?transport=udp',
    ]
}];

connection.videosContainer = document.getElementById('videos-container');

connection.onstream = function(event) {    
    var existing = document.getElementById(event.streamid);
    if(existing && existing.parentNode) {
      existing.parentNode.removeChild(existing);
    }

    event.mediaElement.removeAttribute('src');
    event.mediaElement.removeAttribute('srcObject');
    event.mediaElement.muted = true;
    event.mediaElement.volume = 0;

    var video = document.createElement('video');

    try {
        video.setAttributeNode(document.createAttribute('autoplay'));
        video.setAttributeNode(document.createAttribute('playsinline'));
    } catch (e) {
        video.setAttribute('autoplay', true);
        video.setAttribute('playsinline', true);
    }

    if(event.type === 'local') {
      video.volume = 0;
      try {
          video.setAttributeNode(document.createAttribute('muted'));
      } catch (e) {
          video.setAttribute('muted', true);
      }
    }
    video.srcObject = event.stream;


    var roomSize = 2;

    if(room_maxval > 9){
        roomSize = 4;
    }else if(room_maxval > 4){
        roomSize = 3;
    }


    var width = parseInt(connection.videosContainer.clientWidth / roomSize) - 2;
    var mediaElement = getHTMLMediaElement(video, {
        title: '',
        buttons: ['full-screen'],
        width: width,
        showOnMouseEnter: false
    });

    connection.videosContainer.appendChild(mediaElement);


    var userinfo = document.createElement('div');
    userinfo.className = 'userinfo';
    var nameBar = document.createElement('h3');
    var jointimeBar = document.createElement('h3');
    nameBar.innerHTML = "닉네임 : " + event.extra.fullName;
    jointimeBar.innerHTML = "접속시간 : " + event.extra.joinedAt;
    userinfo.appendChild(nameBar);
    userinfo.appendChild(jointimeBar);
    mediaElement.appendChild(userinfo);

    setTimeout(function() {
        mediaElement.media.play();
    }, 5000);

    mediaElement.id = event.streamid;

    // to keep room-id in cache
    localStorage.setItem(connection.socketMessageEvent, connection.sessionid);

    if(event.type === 'local') {
      connection.socket.on('disconnect', function() {
        if(!connection.getAllParticipants().length) {
          location.reload();
        }
      });
    }
};

connection.onstreamended = function(event) {
    var mediaElement = document.getElementById(event.streamid);
    if (mediaElement) {
        mediaElement.parentNode.removeChild(mediaElement);
    }
};

connection.onMediaError = function(e) {
    if (e.message === 'Concurrent mic process limit.') {
        if (DetectRTC.audioInputDevices.length <= 1) {
            alert('Please select external microphone. Check github issue number 483.');
            return;
        }

        var secondaryMic = DetectRTC.audioInputDevices[1].deviceId;
        connection.mediaConstraints.audio = {
            deviceId: secondaryMic
        };

        connection.join(connection.sessionid);
    }
};

messageButton.click(() => {
    sendMessage();
});

$('#sendmessage').keyup(() => {
    enterKey();
});

function enterKey(){
    if(window.event.keyCode === 13){
        sendMessage();
   }
}

function sendMessage(){
    const msg = $('#sendmessage').val();
    $('#sendmessage').focus();
    if(msg !== ''){
        $('#sendmessage').val("");
        chatArea.append(`<p>${userId} : ${msg}</p>`);
        chatArea.scrollTop(chatArea.prop('scrollHeight'));

        socket.emit('sendMsg',{'id' : userId, 'msg' : msg, 'RoomUrl' : RoomUrl});
    }
};

socket.emit('enter', RoomUrl, userId);
socket.on('join', (RoomUrl, userList, userId) => {
    chatArea.append(`<p>${userId}님이 입장하셨습니다.</p>`);
    chatArea.scrollTop(chatArea.prop('scrollHeight'));
});
socket.on('leave', userId => {
    chatArea.append(`<p>${userId}님이 .</p>`);
    chatArea.scrollTop(chatArea.prop('scrollHeight'));
    onLeave(userId);
});
socket.on('message', data => {
    onmessage(data);
});
socket.on('receive', (userId, msg) => {
    chatArea.append(`<p>${userId} : ${msg}</p>`);
    chatArea.scrollTop(chatArea.prop('scrollHeight'));
});

// ..................................
// ALL below scripts are redundant!!!
// ..................................

// ......................................................
// ......................Handling Room-ID................
// ......................................................

(function() {
    var params = {},
        r = /([^&=]+)=?([^&]*)/g;

    function d(s) {
        return decodeURIComponent(s.replace(/\+/g, ' '));
    }
    var match, search = window.location.search;
    while (match = r.exec(search.substring(1)))
        params[d(match[1])] = d(match[2]);
    window.params = params;
})();

// detect 2G
if(navigator.connection &&
   navigator.connection.type === 'cellular' &&
   navigator.connection.downlinkMax <= 0.115) {
  alert('2G is not supported. Please use a better internet service.');
}






function Request(valuename)
{
    var rtnval;
    var nowAddress = unescape(encodeURIComponent(location.href));
    nowAddress = decodeURIComponent(nowAddress);
    var parameters = new Array();
    parameters = (nowAddress.slice(nowAddress.indexOf("?")+1,nowAddress.length)).split("&");
    for(var i = 0 ; i < parameters.length ; i++){
        if(parameters[i].split("=")[0] == valuename){
            rtnval = parameters[i].split("=")[1];
            if(rtnval == undefined || rtnval == null){
                rtnval = "";
            }
            return rtnval;
        }
    }
}


