document.onkeypress = keyhandler;

function keyhandler(event) {
    
	var e = event || window.event;
	var keychar = e.keyCode || e.charCode;
    
	if (keychar==K_ENTER)try{e.stopPropagation();e.preventDefault();}catch(errorIExplorer){e.cancelBubble = true;}
}

function evitaEventos(event){
	var e = event || window.event;
	
	try{
		e.stopPropagation();
		e.preventDefault();
	}catch(errorIExplorer){
		e.cancelBubble = true;
		e.returnValue  =false;
	}
   	
}