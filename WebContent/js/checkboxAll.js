'use strict';

function allCheck(tf){
	let counter = document.mutterList.elements.length;
	let i = 0;
	while(i < counter){
		document.mutterList.elements[i].checked = tf;
		i++;
	}
}