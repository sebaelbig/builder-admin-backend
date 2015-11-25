var _pag;

/*
Descripcion:
	Arma la tabla con los elementos
	
Requiere:
	Incorporar:
		-dojo
		
	Funciones que deben estar definidas
		-daneObjeto(json_element, fila) : Funcion que devuelve un objeto JSON_XXXX para ser agregado a la tabla
			@1 (json_element): Representa los datos internos que contendra el objeto
			@2 (fila): Indica la fila en la cual estara posicinoado el elemento
 		-
 		
 	Acciones:
 		-"agregar"  : La accion asociada a la coleccion sera la de agregar	 
 		-"eliminar" : Al cargar una coleccion se le debe indicar que accion realizará
 		-"externo"	: Llama a una funcion externa en donde se le pasa el elemento seleccionado 
 		
 	Instanciamiento:
 		-idLista : id del nodo DOM que contendra la lista
 		-accion  : Nombre de la accion   	
 	
 	HTML:
	 	<div solType="barraDeNavegacion" />	
*/
function cargarBarraDeNavegacion(){
	
	//Flecha anterior
	var flechaIzq;
	if (getPaginador().hayPaginaAnterior){
		flechaIzq = crearFlechaDeBarraDeNavegacion(	"/horus_fe/img/comun/flechas/flecha_izq.png", "javascript:listarAnterior()", "flechaAnterior");
		flechaIzq.title = "P\u00E1gina anterior (Ctrl + Flecha Izq)";	
		flechaIzq.style.cursor = "pointer";
	}else{
		flechaIzq = crearFlechaDeBarraDeNavegacion(	"/horus_fe/img/comun/flechas/flecha_izq_disabled.png", "", "flechaAnterior");
	    flechaIzq.style.cursor = "not-allowed";
	    flechaIzq.title = "No hay m\u00E1s p\u00E1ginas";
	}
	flechaIzq.style.marginLeft = "40%";
	flechaIzq.style.marginRight = "2%";

	//Flecha siguiente
	var flechaDer;
	if (getPaginador().hayPaginaSiguiente){
		flechaDer = crearFlechaDeBarraDeNavegacion(	"/horus_fe/img/comun/flechas/flecha_der.png", "javascript:listarSiguiente()", "flechaSiguiente");	
		flechaDer.style.cursor = "pointer";
		flechaDer.title = "P\u00E1gina siguiente (Ctrl + Flecha Der)";
	}else{
		flechaDer = crearFlechaDeBarraDeNavegacion(	"/horus_fe/img/comun/flechas/flecha_der_disabled.png", "", "flechaSiguiente");
	    flechaDer.style.cursor = "not-allowed";
	    flechaDer.title = "No hay m\u00E1s p\u00E1ginas";
	}	
	flechaDer.style.marginLeft = "2%";
	
	//Carga los datos del medio de la navegacion
	var informacionDeNavegacion = crearInformacionDeNavegacion();
	
	var divBarraDeNavegacion = 	document.createElement("div");
	divBarraDeNavegacion.setAttribute("class", "navegacion");
	divBarraDeNavegacion.setAttribute("id", "barraDeNavegacion");
	
	divBarraDeNavegacion.appendChild(flechaIzq);
	divBarraDeNavegacion.appendChild(informacionDeNavegacion);
	divBarraDeNavegacion.appendChild(flechaDer);

	//Busco y transformo el div horusType con id barraDeNavegacion
	var divContenedorBarraDeNavegacion = dojo.query("div[horusType='barraDeNavegacion']")[0];
	divContenedorBarraDeNavegacion.innerHTML = "<div style='width:100%; float:left;height: 10px;'/>";
	divContenedorBarraDeNavegacion.appendChild(divBarraDeNavegacion);

}

/*
Armo la vista de los datos segun esta seleccionado el indexamiento:

Estado pasivo:
	<span id="paginaActual" value="1">1</span> de <span id="totalPaginas">1</span>

Estado activo:
	<span id="paginaActual"><input type="text" value="1"/></span> de <span id="totalPaginas">1</span>
*/
function crearInformacionDeNavegacion(){
	
	//Armo la parte del nro de pagina actual
	var spanInformacionPaginaActual = document.createElement("span");
	spanInformacionPaginaActual.setAttribute("id","paginaActual");
	spanInformacionPaginaActual.setAttribute("value", getPaginador().paginaActual);
	spanInformacionPaginaActual.setAttribute("class", "negrita");
	spanInformacionPaginaActual.setAttribute("onclick", "javascript:ingresarPaginaANavegar()");
	spanInformacionPaginaActual.setAttribute("title","Haga click aqu\u00ED para ingresar la p\u00E1gina a listar");
	spanInformacionPaginaActual.setAttribute("class", "negrita");
	spanInformacionPaginaActual.style.cursor = "pointer";
	
	var txt_medio = document.createTextNode(" de ");

	//Armo la parte de la cantidad de paginas totales
	var spanInformacionTotalPaginas = document.createElement("span");
	spanInformacionTotalPaginas.setAttribute("id","totalPaginas");
	spanInformacionTotalPaginas.setAttribute("class", "negrita");
	spanInformacionTotalPaginas.style.cursor = "default";
	
	//Armo el div q contiene a las partes anteriores
	var divInformacionDeNavegacion = document.createElement("div");
	divInformacionDeNavegacion.setAttribute("style","margin-top: 5px; float: left;");
	divInformacionDeNavegacion.appendChild(spanInformacionPaginaActual);
	divInformacionDeNavegacion.appendChild(txt_medio);
	divInformacionDeNavegacion.appendChild(spanInformacionTotalPaginas);
	
	return divInformacionDeNavegacion;
}

/*
Cambia el aspecto para darle soporte a poder ingresar la pagina actual  
*/
var paginaANavegar = 1;
function ingresarPaginaANavegar(){
	//<input type="text" value="1" onkeypress="javascript:guardarPaginaANavegar()" id="paginaANavegar" />
	var input = document.createElement("input");
	input.setAttribute("type","text");
	input.setAttribute("value", getPaginador().paginaActual);
	input.setAttribute("onkeypress", "javascript:guardarPaginaANavegar(event)");
	input.setAttribute("id","paginaANavegar");
	input.setAttribute("style","width: 2em; text-align: right;");
	
	var spanPaginaActual = document.getElementById("paginaActual");
	spanPaginaActual.innerHTML = "";
	spanPaginaActual.appendChild(input);
	
	input.select();
}

/*
Establece que pagina listar, y envia al servidor la peticion de listar los elementos de la nueva pagina 
*/
function guardarPaginaANavegar(e){
    
    if (document.layers)
        Key = e.which;
    else
        Key = e.keyCode;
    
//    var elem = (e.target) ? e.target : e.srcElement;
    
    if (Key == 13){
    	//Si Apreto el enter, y hay cargado un numero valido, listo ese nro de pagina
     	e.stopPropagation();
     	e.preventDefault();  
     	
     	var valorIngresado = document.getElementById("paginaANavegar").value;
     	
     	if ((valorIngresado <= getPaginador().totalPaginas) && (valorIngresado > 0)){
     		paginaActual_template = valorIngresado;
     		document.getElementById("paginaActual").innerHTML = valorIngresado;
			listar_elementos(valorIngresado);     	
     	}else{
     		//Sino es un numero valido, listo la primer pagina
     		document.getElementById("paginaActual").innerHTML = "1";
     		listar_elementos(1);
     	}
	} 	
}

/*
Crear flecha de navegacion segun los parametros recibidos

-img: URL de la imagen 
-accion: Accion que realizara la flecha al momento de realizarle click
-id: ID de la flecha

*/
function crearFlechaDeBarraDeNavegacion(img, accion, id){
    var imagen = document.createElement("img");
    imagen.setAttribute("src",img);
    imagen.setAttribute("class", "flechaDeBarraDeNavegacion");
    imagen.setAttribute("title","Modificar");
    imagen.setAttribute("height","30px");
    imagen.setAttribute("width","30px");
	imagen.setAttribute("onclick",accion);
	imagen.setAttribute("id",id);
	
	return imagen;
}

/*
Toma los datos del paginador y re-arma la navegacion
*/
function tomarDatos(json_paginador){
	
	setPaginador( json_paginador );
	
	cargarBarraDeNavegacion();
	
	actualizarPaginaActual();
	actualizarTotalPaginas();
	
	procesarElementos(getPaginador().elementos);
	
	if (getPaginador().elementos.length==0 && getPaginador().mensaje)
		mostrarCartel("Informacion", imagenInfo, getPaginador().mensaje);
}

function actualizarPaginaActual(){
	var pagAct = document.createTextNode(getPaginador().paginaActual);
	document.getElementById("paginaActual").innerHTML = "";
	document.getElementById("paginaActual").appendChild(pagAct);
}

function actualizarTotalPaginas(){
	var totalPag = document.createTextNode(getPaginador().totalPaginas);
	document.getElementById("totalPaginas").innerHTML = "";
	document.getElementById("totalPaginas").appendChild(totalPag);
}

/*
Transforma los elementos JSON en objetos JS
*/
function procesarElementos(json_elementos){
	
	if (json_elementos.length > 0){
	
		dojo.removeClass(dojo.byId("barraDeNavegacion"), "oculto");
		dojo.removeClass(dojo.byId("divTabla"), "oculto");
		
		eliminarHijos(document.getElementById("tabla").getElementsByTagName("tbody")[0]);
		
		elementos = new Array();
		
		for (var _i=0; _i != json_elementos.length; _i++){
			
			elementos[_i] = dameObjeto(json_elementos[_i], _i);
			
			elemento = elementos[_i].parsearHTML();
			
			if (elemento)
				document.getElementById("tabla").getElementsByTagName("tbody")[0].appendChild(elemento);
		}
		
	}else{
		dojo.addClass(dojo.byId("barraDeNavegacion"), "oculto");
		dojo.addClass(dojo.byId("divTabla"), "oculto");
	}
	
}

/*
Elimina todos los nodos hijos de la celda pasada como parametro
*/
function eliminarHijos(cell){

  while ( cell.hasChildNodes() ){
        cell.removeChild( cell.firstChild );       
    } 

}

/*
Muestra los errores produsidos
*/
function procesarErrores(json_errores){
	mostrarCartel("Error",imagenError,json_errores);
}

function desaparecerTabla(){
	var fadeArgs = {
	             node: "barraDeNavegacion"
	         };
	 dojo.fadeOut(fadeArgs).play();
	 fadeArgs = {
	             node: "tabla"
	         };
	 dojo.fadeOut(fadeArgs).play();
}

function aparecerTabla(){
	var fadeArgs = {
	             node: "barraDeNavegacion"
	         };
	 dojo.fadeIn(fadeArgs).play();
	 fadeArgs = {
	             node: "tabla"
	         };
	 dojo.fadeIn(fadeArgs).play();
}

function getPaginador(){
	return _pag;
}
function setPaginador(paginadorNuevo){
	_pag = paginadorNuevo;
}