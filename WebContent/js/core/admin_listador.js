/*
	-Requiere tener implementado en la pagina que se importa las siguientes funciones:
	
		1)	function recuperarJson(){
				//Recuperar los datos: var valor = dojo.byId('id_attr').value;
		        //Armar el json: var json_object = "{'id':null, 'version':0, 'attr':'"+valor+"'}";
				//Devolver el json: return json_object;
			}
			
		2)  function limpiarDatos(){
				//Limpia los input de ingreso: dojo.byId('id_attr').value = null;
			}	
		3)  function dameObjeto(json_element, fila){
				//Devolver un objeto JSON_Entidad: return new JSON_Especialidad(json_element, fila);
			}	
			
		4)  Tener un boton de modificar (btn_modificar) definido, si no es necesario el boton, poner el tag
		    para que exista el id correspondiente pero ponerlo oculto.	
		
		5)	Tener una tabla con id "tabla"      
	
	-Provee las siguientes funciones:
	
		-> Clase OpcionEditar(fila, funcion) : devuelve un objeto que contiene un elemento anchor que ejecuta la funcion pasada como parametro al ser clickeado
		-> Clase OpcionEliminar(fila) : devuelve un objeto que contiene un elemento anchor que pregunta si desea eliminar al objeto de la fila al ser clickeado
		
		->function modificarLocal(fila); le dice al objeto de la fila 'fila' que cargue los datos, ya que va a ser modificado 
	
*/

var filaEnEdicion = null;

var cantidadElementos = 10;

var buscando = false;

var elementos = new Array();

var imagenModificar = "/horus_fe/img/comun/modificar.png";
var imagenEliminar = "/horus_fe/img/comun/eliminar.png";



try{
	//Boton para realizar la accion
	var _botonAccion = dojo.byId('btn_crear');

	//Boton que realiza el submit
	var _botonSubmit = dojo.byId('btn_crear');

	var adminForm = new Admin_Formulario(idFormulario, _botonAccion, _botonSubmit);
}catch(_exc){}

//Id del formulario
var idFormulario = null;

//Crea el link editar
function OpcionEditar(fila,funcion){
	this.a = document.createElement("a");
    imagen = document.createElement("img");
    imagen.setAttribute("src",imagenModificar);
    this.a.setAttribute("value","Modificar");
    imagen.setAttribute("class","imagenEscalable");
    imagen.setAttribute("title","Modificar");
    imagen.setAttribute("alt","Modificar");
	this.a.setAttribute("onclick",funcion);
	this.a.appendChild(imagen);
}
			
//Crea el link eliminar
function OpcionEliminar(fila){
	this.a = document.createElement("a");
    imagen = document.createElement("img");
    imagen.setAttribute("src",imagenEliminar);
    this.a.setAttribute("value","Eliminar");
    imagen.setAttribute("class","imagenEscalable");
    imagen.setAttribute("title","Eliminar");
    imagen.setAttribute("alt","Eliminar");
	this.a.setAttribute("onclick","javascript:eliminar("+fila+")");
	this.a.appendChild(imagen);
}

function tomarListado(json_paginador){
	tomarDatos(dojo.fromJson(json_paginador));

}

function modificarLocal(fila){
	filaEnEdicion = fila;
	elementos[filaEnEdicion].cargarDatos();
	botonAccion = botonSubmit = dojo.byId('btn_modificar');
	if (botonAccion){
		
		admin_setDisplayBoton(botonAccion, "block");
		admin_setDisplayBoton("btn_crear", "none");
		
		adminForm.setBotonSubmit(botonSubmit);
		adminForm.setBotonAccion(botonAccion);
	}
	desaparecerTabla();
}

function modificarExterno(fila){
	var link = dojo.byId('link_modificar').getAttribute("href");
	link += "&idElemento="+elementos[fila].json.id;
	location.href = link;
}

function cancelar(){
	limpiarDatos();
	buscando = false;	
	botonAccion = botonSubmit = dojo.byId('btn_crear');
	if (botonAccion){
		botonAccion.disabled="disabled";
		
		admin_setDisplayBoton(botonAccion, "block");
		admin_setDisplayBoton("btn_modificar", "none");
		
		filaEnEdicion = null;
		adminForm.setBotonSubmit(botonSubmit);
		adminForm.setBotonAccion(botonAccion);
		adminForm.resetear();
	}
	aparecerTabla();
}

//Crear
function crearElemento(){
	var json_objeto = recuperarJson();
	admin.crear(json_objeto, tomarValidacion);
}

//Modificar
function modificarElemento(){
	var elem = elementos[filaEnEdicion];
	elem.tomarDatos();
	admin.modificar(elem.toString(),tomarValidacion);
}

function setFilaEnEdicion(fila){
	filaEnEdicion = fila;
}

function getFilaEnEdicion(){
	return filaEnEdicion;
}

function getElementoEnEdicion(){
	return elementos[getFilaEnEdicion()];
}

//Eliminar	
function eliminar(fila){
	if (confirm("\u00BFEst\u00E1 seguro que desea eliminar "+elementos[fila].label+"?")){
		var elem = elementos[fila];
		admin.eliminar(getPaginador().paginaActual, getPaginador().cantPorPagina, elem.toString(),tomarValidacion);
	}
}

//Buscar
function tomarBusqueda(json_respuesta){
	var respuesta = dojo.fromJson(json_respuesta);
	
	//Si no hubo problemas al ejecutar la accion
	if (respuesta.ok){
		tomarDatos(respuesta.paginador);
	}else{
	    procesarErrores(respuesta.mensaje);
	}
}
function buscarElemento(){
	buscando = true;
	var elemento = recuperarJson();
	admin.buscar(1, getPaginador().cantPorPagina, elemento, tomarBusqueda);	
} 


function listar_elementos(valor){
	admin.listar(valor, getPaginador().cantPorPagina, tomarListado);
}

Seam.Remoting.displayLoadingMessage = function() {
	var div = dojo.byId('remote');
	div.style.display= "block";	
};
				
Seam.Remoting.hideLoadingMessage = function() {
	var div = dojo.byId('remote');
	div.style.display= "none";
};

//Listar todos
function listarTodos(){
	cancelar();
	buscando = false;
	admin.listar(1,getPaginador().cantPorPagina,tomarListado);	
}

function listarAnterior(){
	if(getPaginador().hayPaginaAnterior){
		if (buscando){
			var elemento = recuperarJson();
			admin.buscar(getPaginador().paginaActual-1,getPaginador().cantPorPagina, elemento, tomarBusqueda);	
		}else{
			admin.listar(getPaginador().paginaActual-1,getPaginador().cantPorPagina,tomarListado);
		}
	}
}

//Asocio las teclas Ctrl+(Flecha Der) para listar siguiente
function listarSiguiente(){
	if (getPaginador().hayPaginaSiguiente){
		if (buscando){
			var elemento = recuperarJson();
			admin.buscar(getPaginador().paginaActual+1,getPaginador().cantPorPagina, elemento, tomarBusqueda);	
		}else{
			admin.listar(getPaginador().paginaActual+1,getPaginador().cantPorPagina,tomarListado);
		}
	}
}

//Asocio las teclas Ctrl+(Flecha) para listar 
function manejoDePaginador(e){
	var ctrlPressed =e.ctrlKey;
	
	if (ctrlPressed && e.keyCode==K_FLECHA_DER){//Derecha
		listarSiguiente();
	}else if (ctrlPressed && e.keyCode==K_FLECHA_IZQ){//Izquierda
		listarAnterior();
	}
}
dojo.connect(document, "onkeypress", manejoDePaginador );

/*
*/
function tomarValidacion(json_respuesta){
	
	var respuesta = dojo.fromJson(json_respuesta);
	
	//Si no hubo problemas al ejecutar la accion
	if (respuesta.ok){
		cancelar();
		tomarDatos(respuesta.paginador);
	}else{
	    procesarErrores(respuesta.mensaje);
	}
}

function admin_setDisplayBoton(boton, valorDisplay){
	
	var btn = boton;
	
	if ( typeof boton == "string")
		btn = dojo.byId(boton);
	
	//Si es un boton con imagen -> tiene entre sus clases de estilo las clase"imageButton"
	if (btn){
		if (btn.parentElement.getAttribute("class").search("imageButton")>=0)
			btn = btn.parentElement;
		btn.style.display = valorDisplay;
	}
}