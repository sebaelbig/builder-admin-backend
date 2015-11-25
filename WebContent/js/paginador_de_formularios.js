var paginaActual = 1;
var cantPaginas = 2;
var flechaAnterior  = document.getElementById('flechaAnterior');
var flechaSiguiente = document.getElementById('flechaSiguiente');
var nodoPaginaActual = document.getElementById('paginaActual');

function paginaSiguiente(){
	if (paginaActual < cantPaginas){
		
		document.getElementById('inputs_pagina_'+paginaActual).style.display = 'none';
		
		if (paginaActual == 1){
			flechaAnterior.setAttribute("src","/horus_fe/img/comun/flechas/flecha_izq.png");
			flechaAnterior.setAttribute("style", "border: 0px none ; vertical-align: middle; cursor: pointer;");
			flechaAnterior.setAttribute("onclick", "javascript:paginaAnterior()");
		}
		
		paginaActual ++;
		
		if (paginaActual == cantPaginas){
			flechaSiguiente.setAttribute("src", "/horus_fe/img/comun/flechas/flecha_der_disabled.png");
			flechaSiguiente.setAttribute("style", "border: 0px none ; vertical-align: middle;");
			flechaSiguiente.removeAttribute("onclick");
		}
		
		nodoPaginaActual.replaceChild(document.createTextNode(paginaActual),nodoPaginaActual.firstChild);
		document.getElementById('inputs_pagina_'+paginaActual).style.display = 'block';
	}
}

// cursor:pointer;
function paginaAnterior(){
	
	if (paginaActual > 1){
		
		document.getElementById('inputs_pagina_'+paginaActual).style.display = 'none';
		
		if (paginaActual == cantPaginas){
			flechaSiguiente.setAttribute("src", "/horus_fe/img/comun/flechas/flecha_der.png");
			flechaSiguiente.setAttribute("style", "border: 0px none ; vertical-align: middle; cursor: pointer;");
			flechaSiguiente.setAttribute("onclick", "javascript:paginaSiguiente()");
		}
		
		paginaActual --;
		
		if (paginaActual == 1){
			flechaAnterior.setAttribute("src", "/horus_fe/img/comun/flechas/flecha_izq_disabled.png");
			flechaAnterior.setAttribute("style", "border: 0px none ; vertical-align: middle;");
			flechaAnterior.removeAttribute("onclick");
		}
		
		nodoPaginaActual.replaceChild(document.createTextNode(paginaActual), nodoPaginaActual.firstChild);
		document.getElementById('inputs_pagina_'+paginaActual).style.display = 'block';
	}
}