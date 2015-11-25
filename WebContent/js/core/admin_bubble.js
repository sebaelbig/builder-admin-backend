/*<div id="vista_anio_bubble" class="vista_bubble">
		
		<div id="texto_bubble" class="texto">
			<p class="p_bubble"><span class="negrita">Fecha:</span><span id="fecha_edit"></span></p>
			<p class="p_bubble"> <span class="negrita">Feriado:</span> <input id="feriado_edit" type="text" size="50" /></p>
			<div class="div_boton_bubble">
				<input id="boton_bubble_crear" class="boton_bubble"  type="button" value="Crear" onclick="crearElemento();" />
				<input id="boton_bubble_modificar" class="boton_bubble"  type="button" value="Modificar" onclick="modificarElemento();" />
				<input id="boton_bubble_eliminar" class="boton_bubble"  type="button" value="Eliminar" onclick="EliminarFeriado();" />
				<input class="boton_bubble" type="button" value="Cancelar" onclick="ocultar();" />
			</div>
		</div>
		<div id="tip" class="tip"></div>

	</div>			
*/


/* elem debe tener dos atributos: id, html*/
var bubbleActiva = null;
function generarBurbujaForm(divTexto, pos, funcion_crear, funcion_modificar, funcion_eliminar){
	
	if (bubbleActiva != null){
		ocultarBubble();
	}
	
	var divBubble = document.createElement("div");
	divBubble.setAttribute("id", "vista_anio_bubble");
	divBubble.setAttribute("class", "vista_bubble");
	//divBubble.setAttribute("horusId",elem.id);
	

	
	var div_boton = document.createElement("div");
	div_boton.setAttribute("class", "div_boton_bubble");
	
	if (funcion_crear != null){
		var input_crear = document.createElement("input");
		input_crear.setAttribute("class","boton_bubble");
		input_crear.setAttribute("type","button");
		input_crear.setAttribute("value","Crear");
		input_crear.setAttribute("onclick",funcion_crear);
		div_boton.appendChild(input_crear);
	}
	
	if (funcion_modificar != null){
		var input_modificar = document.createElement("input");
		input_modificar.setAttribute("class","boton_bubble");
		input_modificar.setAttribute("type","button");
		input_modificar.setAttribute("value","Modificar");
		input_modificar.setAttribute("onclick",funcion_modificar);
		div_boton.appendChild(input_modificar);
	}
	
	if (funcion_eliminar != null ){
		var input_eliminar = document.createElement("input");
		input_eliminar.setAttribute("class","boton_bubble");
		input_eliminar.setAttribute("type","button");
		input_eliminar.setAttribute("value","Eliminar");
		input_eliminar.setAttribute("onclick",funcion_eliminar);
		div_boton.appendChild(input_eliminar);
	}
		
	var input_cancelar = document.createElement("input");
	input_cancelar.setAttribute("class","boton_bubble");
	input_cancelar.setAttribute("type","button");
	input_cancelar.setAttribute("value","Cancelar");
	input_cancelar.setAttribute("onclick","ocultarBubble();");
	div_boton.appendChild(input_cancelar);
	
	divTexto.appendChild(div_boton);
	
	var div_tip = document.createElement("div");
	div_tip.setAttribute("class", "tip");
	div_tip.setAttribute("id", "tip");
	
	divBubble.appendChild(divTexto);
	divBubble.appendChild(div_tip);
	
	//var pos = getPosition(elem.html);
		
	bubbleActiva = divBubble;
	
	document.body.appendChild(divBubble);
	
	divBubble.style.position = 'absolute';
	/*132*/
	divBubble.style.top      = pos.y - divBubble.clientHeight + "px";
	divBubble.style.left     = pos.x - 13 + "px";
	
	
}


function ocultarBubble(){
	if (bubbleActiva != null){
		bubbleActiva.parentNode.removeChild(bubbleActiva);
		bubbleActiva = null;
	}
}


function generarBurbujaContenido(div, pos){
	if (bubbleActiva != null){
		ocultarBubble();
	}
	
	var divBubble = document.createElement("div");
	divBubble.setAttribute("id", "vista_anio_bubble");
	divBubble.setAttribute("class", "vista_bubble");
	
	var divTexto = document.createElement("div");
	divTexto.setAttribute("id", "texto_bubble");
	divTexto.setAttribute("class","texto");
		
	var a = document.createElement("a");
    imagen = document.createElement("img");
    imagen.setAttribute("src","/horus_fe/img/comun/ico_cerrar.png");
    a.setAttribute("value","Cerrar");
    a.setAttribute("style","float:right");
    imagen.setAttribute("class","imagenEscalable");
    imagen.setAttribute("title","Cerrar");
    imagen.setAttribute("alt","Cerrar");
	a.setAttribute("onclick","javascript:ocultarBubble();");
	a.appendChild(imagen);
	
	divBubble.appendChild(a);
	
	divTexto.appendChild(div);
	
	var div_tip = document.createElement("div");
	div_tip.setAttribute("class", "tip");
	div_tip.setAttribute("id", "tip");
	
	divBubble.appendChild(divTexto);
	divBubble.appendChild(div_tip);
			
	bubbleActiva = divBubble;
	
	document.body.appendChild(divBubble);
	
	divBubble.style.position = 'absolute';
	
	divBubble.style.top      = pos.Y - divBubble.clientHeight + "px";
	divBubble.style.left     = pos.X - 13 + "px";
	
}

