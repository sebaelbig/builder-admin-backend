function JSON_Producto_OS_Profesional(json_e, fila_e){
	
	this.json = json_e;
	
	this.fila = fila_e;
	
	this.label = json_e.nombre;
	
	this.cargarDatos = function(){
	}
	
	this.tomarDatos = function(){
	}

	this.toString = function(){
	  return dojo.toJson(this.json);
	}
	
	this.parsearHTML = function(){
		var tr = document.createElement("tr");
			
		var td1 = document.createElement("td");
		var texto1 = document.createTextNode(this.json.unidadArancelaria_VO.tipo); 
		td1.appendChild(texto1);
		
		tr.appendChild(td1);
			
		var td2 = document.createElement("td");
		var texto2 = document.createTextNode(this.json.tipoCoeficiente_VO.codigo); 
		td2.appendChild(texto2);
		
		tr.appendChild(td2);	
			
		var td3 = document.createElement("td");
		var texto3 = document.createTextNode(this.json.porcentaje); 
		td3.appendChild(texto3);
		
		tr.appendChild(td3);	
		
		if (puedeModificar || puedeEliminar){	
			var td3 = document.createElement("td");
			
			if (puedeModificar){
				var modificar = new OpcionEditar(this.fila,"javascript:modificarLocal("+this.fila+")");
				td3.appendChild(modificar.a);}
			
			if(puedeEliminar){	
				var eliminar = new OpcionEliminar(this.fila);
				td3.appendChild(eliminar.a);
			}
			tr.appendChild(td3);
		}
		
		
			
		return tr;
	}

	this.getLabel = function(){
		var label;
		if (this.json.rechazada){
			label = "No se acepta la obra social "+this.json.producto.nombre;
		}else{
			label = "Solo se podran sacar: "+this.json.cantidadDeTurnosPorBloqueTurno+" turno(s) del producto de obra social: "+this.json.producto.nombre; 
		}
		return label; 
	}
	
	//Implemento el equals
	this.equal = function(objeto){
		var resul = false;
		
		resul = (this.json.producto.nombre == objeto.json.producto.nombre) && ((this.json.cantidadDeTurnosPorBloqueTurno == objeto.json.cantidadDeTurnosPorBloqueTurno) || (this.json.rechazada == objeto.json.rechazada));
		
		return resul;
	}
	
}