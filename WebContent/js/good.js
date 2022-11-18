$(function(){
	$('.goodButton').on('click', function(){
		let g = $(this).next('span').text();
		
		$.ajax({
			url: "/dokoTsubu/Good",
			type: "POST",
			data: {
				mutterId : $(this).val(),
				good : g
			}
		}).done(function(result){
			console.log(result);
			$(this).text(result);
			
		}).fail(function(){
			
		});
	});
});