$(function(){
	$('.goodButton').on('click', function(){
		$.ajax({
			url: "/dokoTsubu/Good",
			type: "POST",
			data: {
				mutterId : $('.goodButton').val()
			}
		}).done(function(result){
			console.log(result);
			
		}).fail(function(){
			
		});
	});
});