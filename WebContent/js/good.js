$(function(){
	// goodを増やした時の処理関数
	let setCancel = function(){
		// イベントをいったん消去
		$(this).off('click');
		
		// 要素の取得
		let g = $(this).next('.goodSum');
		
		// ボタンの表示を変更		
		$(this).text('cancel');
		
		// クラスの付け替え
		$(this).toggleClass('goodButton cancelButton');
		
		// 新たにイベントをつけなおす
		$(this).on('click', setGood);
		
		// 非同期通信
		$.ajax({
			url: "/dokoTsubu/Good",
			type: "POST",
			data: {
				mutterId : $(this).val(),
				good : 1
			}
		}).done(function(result){
			console.log(result);
			g.text(result);
			
		}).fail(function(){
			
		});
	}
	
	// cancelした時の処理関数
	let setGood = function(){
		// イベントをいったん消去
		$(this).off('click');
		
		// 要素の取得
		let g = $(this).next('.goodSum');
		
		// ボタンの表示を戻す		
		$(this).text('good');
		
		// クラスの付け替え
		$(this).toggleClass('cancelButton goodButton');
		
		// 新たにイベントをつけなおす
		$(this).on('click', setCancel);
		
		// 非同期通信
		$.ajax({
			url: "/dokoTsubu/Good",
			type: "POST",
			data: {
				mutterId : $(this).val(),
				good : -1
			}
		}).done(function(result){
			console.log(result);
			g.text(result);
			
		}).fail(function(){
			
		});
	}
	
	
	$('.goodButton').on('click', setCancel);
	
	$('.cancelButton').on('click', setGood);
});