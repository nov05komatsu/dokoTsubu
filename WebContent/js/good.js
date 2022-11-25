$(function(){
	// goodを増やして、ボタンをキャンセルボタンに変える処理
	let addGood = function(){
		// イベントをいったん消去
		$(this).off('click');
		
		// 要素の取得
		let g = $(this).next('.goodSum');
		
		// ボタンの表示を変更		
		$(this).text('cancel').css('color', 'red');
		
		// クラスの付け替え
		$(this).toggleClass('goodButton cancelButton');
		
		// 新たにイベントをつけなおす
		$(this).on('click', removeGood);
		
		// 非同期通信 将来的にはJSON使って
		$.ajax({
			url: "/dokoTsubu/Good",
			type: "POST",
			data: {
				mutterId : $(this).val(),
				good : 1
			}
		}).done(function(result){
			console.log(result);
			g.text(result).css('color', 'red');
			
		}).fail(function(){
			
		});
	}
	
	// goodを減らしてgoodボタンを復活させる処理
	let removeGood = function(){
		// イベントをいったん消去
		$(this).off('click');
		
		// 要素の取得
		let g = $(this).next('.goodSum');
		
		// ボタンの表示を戻す		
		$(this).text('good').css('color','black');
		
		// クラスの付け替え
		$(this).toggleClass('cancelButton goodButton');
		
		// 新たにイベントをつけなおす
		$(this).on('click', addGood);
		
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
			g.text(result).css('color','black');
			
		}).fail(function(){
			
		});
	}
	
	// goodボタンを押したらgoodを増やす
	$('.goodButton').on('click', addGood);
	
	// cancelボタンを押したらgoodを減らす
	$('.cancelButton').on('click', removeGood);
});