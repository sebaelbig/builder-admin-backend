/*
//Obtengo el div que contiene al menu
var divMenuModerno = dojo.query("div[horusType='menuModerno']")[0];
alert(divMenuModerno);
dojo.addClass(divMenuModerno, "menu_moderno");

//Obtengo el div que contiene las solapas
var divMenuModernoConfiguracion = dojo.query("div[horusType='menuModerno'] > div[horusType='menuModerno_opciones']")[0];
dojo.addClass(divMenuModernoConfiguracion, "menu_moderno_opciones");

//Obtengo el div que contiene el contenido de las solapas
var divMenuModernoContenidos = dojo.query("div[horusType='menuModerno'] > div[horusType='menuModerno_contenidos']")[0];
dojo.addClass(divMenuModernoContenidos, "menu_moderno_contenidos");

//Obtengo el div que contiene los botones del menu
var divMenuModernoBotonera = dojo.query("div[horusType='menuModerno'] > div[horusType='menuModerno_botonera']")[0];
dojo.addClass(divMenuModernoBotonera, "menu_moderno_botonera");
*/
var idMenuActivo = 1;
var cantMenu = 4;

function mostrarConfiguracion(idMenu){
	var menu;
	var contenido;

	//Desaparezco el contenido actual		
	var animacionArgs = {
		node:"contenido_"+idMenuActivo,
		duration: 500,
		
		onEnd: function(){
			//Cuando termina la animacion realizo las demas acciones
			
		}
     	}
     	//dojo.fadeOut(animacionArgs).play();
     	
	//Escondo todos los contenidos y menues
	for (i = 1; i != (cantMenu+1); i++){
		
		menu = document.getElementById("menu_"+i);
		dojo.removeClass(menu, "menu_moderno_opcion_activada"); 
		
		contenido = document.getElementById("contenido_"+i);
		contenido.style.display = "none";
		dojo.removeClass(contenido, "menu_moderno_contenidos_activada");
	}
	
	//Me guardo el menu activo
	idMenuActivo = idMenu;

	//Obtengo el menu seleccionado y el contenido correspondiente		
	menu = document.getElementById("menu_"+idMenuActivo);
	contenido = document.getElementById("contenido_"+idMenuActivo);
	
	//Muestro el contenido correspondiente al menu seleccionado
	contenido.style.display = "block";
	
	//Le agrego el estilo de la clase actual
	dojo.addClass(menu, "menu_moderno_opcion_activada");
	dojo.addClass(contenido, "menu_moderno_contenidos_activada");
     	
}