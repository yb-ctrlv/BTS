$(function(){
	token = $('meta[name="_csrf"]').attr('th:content');
	header = $('meta[name="_csrf_header"]').attr('th:content');
	$.ajax({
		type: "POST",
		url: "/membercount",
		dataType : "Json",
		beforeSend : function(xhr){
			xhr.setRequestHeader(header,token);
		},
		success : function(data){
			var chartData = [data.previousReg, data.todayReg, data.weeklyReg, data.monthlyReg, data.totalReg];
			var chartName = ["전일 가입자수", "금일 가입자수", "주간 가입자수", "월간 가입자수", "전체 가입자수"];
			var format = d3.format(",d");
			var color = d3.scaleOrdinal(d3.schemeCategory10);
			
			d3.select("#chart").selectAll("div").data(chartData)
				.enter().append("div")
					.style("font", "30px sans-serif")
					.style("text-align", "right")
					.style("padding", "3px")
					.style("margin", "1px")
					.style("width", "0px")
					.style("background-color", function(d, i) { return color(i); })
					.transition()
					.duration(2000)
					.style("width", function(d) { return d*20 + "px"; })
				    .on("start", function repeat() {
				        d3.active(this)
				            .tween("text", function(d) {
				              var that = d3.select(this),
			                  i = d3.interpolateNumber(that.text().replace(/,/g, ""), d);
				              return function(d) {  that.text(format(i(d))); };
				            })
				          .transition()
				            .on("start", repeat);
				      });
			
			d3.select("#chartName").selectAll("span").data(chartName)
				.enter().append("span")
					.style("font", "18px sans-serif")
					.style("text-align", "right")
					.style("color", "white")
					.style("padding", "3px")
					.style("margin", "1px")
					.style("width", "0px")
					.style("background-color", function(d, i) { return color(i); })
					.text(function(d){
						return d;
					});
				
		},error : function(data, status, error){
			console.log(data);
			console.log(status);
			console.log(error);
			
		}				
	});
});

	
	
