function JSON_ItemModulo(json_e, fila_e){
	
	this.json = json_e;
	
	this.fila = fila_e;
	
	this.label = json_e.prestacion.codigUnico;
	
	this.cargarDatos = function(){
		document.getElementById('prestacion').value = this.json.prestacion;
		document.getElementById('cantidad').value   = this.json.cantidad;
	}
	
	this.tomarDatos = function(){
	    this.json.prestacion = document.getElementById('prestacion').value ;
		this.json.cantidad   = document.getElementById('cantidad').value;
	}
	

	this.toString = function(){
	  return dojo.toJson(this.json);
	}
	
	this.getLabel = function(){
		return this.label;
	}
	
	this.parsearHTML = function(){
		
		var tr = document.createElement("tr");
		
		//Cantidad	
		var td1 = document.createElement("td");
		var texto1 = document.createTextNode(this.json.cantidad); 
		td1.appendChild(texto1);
		
		tr.appendChild(td1);
		
		//Codigo
		var td2 = document.createElement("td");
		var texto2 = document.createTextNode(this.json.prestacion.codigoUnico); 
		td2.appendChild(texto2);
		
		tr.appendChild(td2);
			
		//Prestacion	
		var td3 = document.createElement("td");
		var texto3 = document.createTextNode(this.json.prestacion.nombre); 
		td3.appendChild(texto3);
		
		tr.appendChild(td3);
		
		/*puedeModificar || */
		if (puedeEliminar){	
			var td4 = document.createElement("td");
			
			/*			
			if (puedeModificar){
				var modificar = new OpcionEditar(this.fila,"javascript:modificarLocal("+this.fila+")");
				td4.appendChild(modificar.a);}
			*/
			if(puedeEliminar){	
				var eliminar = new OpcionQuitarItem(this.fila);
				td4.appendChild(eliminar.a);
			}
			tr.appendChild(td4);
		}
		
		return tr;
	}
} 

function OpcionQuitarItem(fila){
	this.a = document.createElement("a");
    imagen = document.createElement("img");
    imagen.setAttribute("src",imagenEliminar);
    this.a.setAttribute("value","Eliminar");
    imagen.setAttribute("class","imagenEscalable");
    imagen.setAttribute("title","Eliminar");
    imagen.setAttribute("alt","Eliminar");
	this.a.setAttribute("onclick","javascript:quitarItem("+fila+")");
	this.a.appendChild(imagen);
}
