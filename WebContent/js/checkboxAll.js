'use strict';

function allCheck(tf){
	var counter = document.mutterList.elements.length;
	var i = 0;
	while(i < counter){
		document.mutterList.elements[i].checked = tf;
		i++;
	}
}