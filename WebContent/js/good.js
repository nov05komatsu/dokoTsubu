$(function(){
	$('.goodButton').on('click', function(){
		let g = $(this).next('span');
		
		$.ajax({
			url: "/dokoTsubu/Good",
			type: "POST",
			data: {
				mutterId : $(this).val(),
				good : g.text()
			}
		}).done(function(result){
			console.log(result);
			g.text(result);
			
		}).fail(function(){
			
		});
	});
});