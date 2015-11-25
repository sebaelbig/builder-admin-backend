//Muestro u oculto las ayudas
function ocultarAyuda(indice){ 
	dojo.addClass('ayuda_'+indice, 'oculto');
}
function mostrarAyuda(indice){ 
	dojo.fadeIn(
			{node:'ayuda_'+indice, 
				duration:800, 
				beforeBegin: dojo.removeClass('ayuda_'+indice, 'oculto')
			}).play();
	
}

//Trabajo con el top
function setTopAyuda(indiceAyuda, cantPx){
	document.getElementById('ayuda_'+indiceAyuda).style.top = cantPx+"px";
}
function addTopAyuda(indiceAyuda, offsetPx){
	var ayuda = document.getElementById('ayuda_'+indiceAyuda);
	ayuda.style.top = parseInt(ayuda.style.top) + offsetPx +"px";
}

//Trabajo con el left
function setLeftAyuda(indiceAyuda, cantPx){
	document.getElementById('ayuda_'+indiceAyuda).style.left = cantPx+"px";
}
function addLeftAyuda(indiceAyuda, offsetPx){
	var ayuda = document.getElementById('ayuda_'+indiceAyuda);
	ayuda.style.left = parseInt(ayuda.style.left) + offsetPx +"px";
}

//Posiciona una ayuda sobre un elemento indicado
function posicionarAyudaSobre(indiceAyuda, idElementoDebajo){
	try{
		//Obtiene la posicion del elemento que va debajo
		var pos = getPosition( document.getElementById(idElementoDebajo) );

		//Obtengo la ayuda a posicionar
		var ayuda = document.getElementById('ayuda_'+indiceAyuda);

		//Posiciono la ayuda
//		setTopAyuda(indiceAyuda, pos.y - ayuda.offsetHeight - 53);
//		setLeftAyuda(indiceAyuda, pos.x-3);

	}catch(e){
		alert("No se pudo cargar la ayuda: "+indiceAyuda);
	}
}

//Posiciono las ayudas
function posicionarAyuda(){
//	posicionarAyudaSobre( 1,'img_buscar_profesionales');
	mostrarAyuda(1);
//	posicionarAyudaSobre( 2,'nodoPadre_profesional_apellido_nombre_nro_matricula');
	mostrarAyuda(2);
//	posicionarAyudaSobre( 3,'info_bloqueTurnos');
//	addTopAyuda(3, 15);
//	addLeftAyuda(3, 5);
//	posicionarAyudaSobre( 4,'producto_os_div_paciente_apellido_nro_doc');
//	setLeftAyuda(4, 90);
//	addTopAyuda(4, 14);
//	posicionarAyudaSobre( 5,'tablaTurnos');
}

//dojo.addOnLoad(posicionarAyuda);