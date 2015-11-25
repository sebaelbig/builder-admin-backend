// FUNCIONES JAVASCRIPT PARA EL ARBOl 
Seam.Remoting.loadingMessage = "Obteniendo informacion ...";

var admin_usuario_remoto = Seam.Component.getInstance('admin_Usuarios_Remoto');
//FUNCIONES JAVASCRIPT PARA EL ARBOl
//Menu
function manejarMenu(nodoMenu){
	var abierto = eval(nodoMenu.getAttribute("abierto"));
	
	if (!abierto){
		//Esta cerrado, Abrir
		nodoMenu.setAttribute("abierto", "true");
		mostrarSubMenu(nodoMenu);
		
	}else{
		//Esta abierto, Cerrar
		nodoMenu.setAttribute("abierto", "false");
		ocultarSubMenu(nodoMenu);
	}
	
}

function mostrarSubMenu(nodoMenu){
	dojo.removeClass(document.getElementById("contenido_"+nodoMenu.id), "oculto");
	dojo.addClass(nodoMenu, "sombreado");
	nodoMenu.getElementsByTagName('img')[0].src = img_btn_menos;
}

function ocultarSubMenu(nodoMenu){
	dojo.addClass(document.getElementById("contenido_"+nodoMenu.id), "oculto");
	dojo.removeClass(nodoMenu, "sombreado");
	document.getElementById("txt_"+nodoMenu.id).style.border = "";
	nodoMenu.getElementsByTagName('img')[0].src = img_btn_mas;			
}

//Sub Menu
function manejarSubMenu(nodoSubMenu){
	var abierto = eval(nodoSubMenu.getAttribute("abierto"));
	
	if (!abierto){
		//Esta cerrado
		nodoSubMenu.setAttribute("abierto", "true");
		mostrarAcciones(nodoSubMenu);
		
	}else{
		//Esta abierto
		nodoSubMenu.setAttribute("abierto", "false");
		ocultarAcciones(nodoSubMenu);
	}
	
}

function mostrarAcciones(nodoSubMenu){
	dojo.removeClass(document.getElementById("contenido_"+nodoSubMenu.id), "oculto");
	dojo.addClass(nodoSubMenu, "sombreado");
	nodoSubMenu.getElementsByTagName('img')[0].src = img_carpeta_abierta;
}

function ocultarAcciones(nodoSubMenu){
	dojo.addClass(document.getElementById("contenido_"+nodoSubMenu.id), "oculto");
	dojo.removeClass(nodoSubMenu, "sombreado");
	document.getElementById("txt_"+nodoSubMenu.id).style.border = "";	
	nodoSubMenu.getElementsByTagName('img')[0].src = img_carpeta_cerrada;
}

//Acciones
function seleccionoFuncion(nodoFuncion){
	var checkBox = document.getElementById("form:check_"+nodoFuncion.id);
	
	checkBox.checked = !checkBox.checked;
	
	actualizarVista(nodoFuncion, checkBox.checked);
}

function actualizarVista(nodoFuncion, seleccionado){
	
	//nodoSubMenu -> ContenedorFunciones -> nodoFuncion
	var nodoSubMenu = nodoFuncion.parentNode.parentNode;
	var cantidadEnSubMenu = eval(nodoFuncion.parentNode.getAttribute("cantidadSeleccionadas"));
	
	//nodoMenu -> ContenedorSubMenues -> nodoSubMenu
	var nodoMenu = nodoSubMenu.parentNode.parentNode;
	var cantidadEnMenu = eval(nodoSubMenu.parentNode.getAttribute("cantidadSeleccionadas"));
	
	//Muestro los iconos
	if (seleccionado ){
		//Selecciono una funcion
		
		if (cantidadEnSubMenu == 0){
			//No habia ninguna seleccionada en el sub menu
			nodoSubMenu.getElementsByTagName("img")[1].src = img_flecha_der_verde;
		}
		
		if (cantidadEnMenu == 0){
			//No habia en el menu
			nodoMenu.getElementsByTagName("img")[1].src = img_mas_verde;
		}
		
		cantidadEnSubMenu++;
		cantidadEnMenu++;
	
	}else{
		if (cantidadEnSubMenu == 1){
			//Habia exactamente una, la que estoy sacando
			nodoSubMenu.getElementsByTagName("img")[1].src = null;
		}
		
		if (cantidadEnMenu == 1){
			//Habia exactamente una, la que estoy sacando
			nodoMenu.getElementsByTagName("img")[1].src = null;
		}
		if (cantidadEnSubMenu>0){
			cantidadEnSubMenu--;
		}
		if (cantidadEnMenu>0){
			cantidadEnMenu--;
		}
	}
	
	//Actualizao los contadores
	nodoFuncion.parentNode.setAttribute("cantidadSeleccionadas", cantidadEnSubMenu)
	nodoSubMenu.parentNode.setAttribute("cantidadSeleccionadas", cantidadEnMenu)
}

function resaltarAccion(nodoFuncion){
	nodoFuncion.style.border = "1px solid #ffb845";
	dojo.addClass(nodoFuncion, "sombreado");
	var desc = nodoFuncion.getAttribute("descripcion");
	document.getElementById("detalle_hoja").innerHTML = desc; 
}

function normalizarAccion(nodoFuncion){
	nodoFuncion.style.border = "";
	document.getElementById("detalle_hoja").innerHTML = "Sit\u00FAe el mouse sobre el elemento que desee para ver su descripci\u00F3n";
	dojo.removeClass(nodoFuncion, "sombreado");
}

function selectCheckBox(id){
	var check = document.getElementById(id);
	var nodoFuncion = check.parentNode;
	
	actualizarVista(nodoFuncion, check.checked);
}

var fxsAsignados = new Array(); 
function procesarCheckBox(check){
	if (check.checked){
		fxsAsignados.push(check.parentNode.getAttribute("nombre"));
	}
}

function recolectarFuncionesElegidas(){
	dojo.query("input[id^='form:check_fxs_']").forEach(procesarCheckBox);
	document.getElementById("form:fxs_seleccionados").value = fxsAsignados;
}

function setearCheckBox(check){
	if (check.getAttribute("tieneFuncion") == "si"){
		check.checked = true;
		actualizarVista(check.parentNode, check.checked);
	}else{
		check.checked = false;
	}
	
}

function setearImagenMenu(img){
	img.src = img_btn_mas;
}
function setearImagenSubMenu(img){
	img.src = img_carpeta_cerrada;
}
function setearImagenAccion(img){
	img.src = img_item_lisa;
}

