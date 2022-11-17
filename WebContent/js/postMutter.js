$(function(){
	$('#postMutterButton').on('click', function(){
		$.ajax({
			url: "/dokoTsubu/Main",
			type: "POST",
			data: {
				text : $("#postMutterText").val()
			}
		}).done(function(){
			console.log("成功");
		}).fail(function(){
			
		});
	});
});