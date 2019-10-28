$(function(){
	var num = 1;
	
	$('#leftArrow').click(function(){
		num--;
		if(num < 1){
			num = 9;			
		}
		document.getElementById("gallery").src = "/bts/resources/images/broadcastingImage0" + num + ".png";
		$('#room_image').val("/bts/resources/images/broadcastingImage0" + num + ".png");
		return false;
	});
	
	
	$('#rightArrow').click(function(){
		num++;
		if(num > 9){
			num = 1;
		}
		document.getElementById("gallery").src = "/bts/resources/images/broadcastingImage0" + num + ".png";
		$('#room_image').val("/bts/resources/images/broadcastingImage0" + num + ".png");
		return false;
	});
	
});


function submitChk(){
	 var radioVal = $('input[name="inlineRadioOptions"]:checked').val();
	 $('#room_maxval').val(radioVal);
	 return true;
}
