function JSON_ItemFichaDeConsumo(json_l, fila_e){
	
	this.json = json_l;
	
	this.fila = fila_e;
	
	this.label = json_l.nombre;
	
	this.cargarDatos = function(){
		// No es necesario	
	}
	
	this.tomarDatos = function(){
		// No es necesario
	}
	

	this.toString = function(){
	  return dojo.toJson(this.json);
	}
	
	
	
	this.parsearHTML = function(){
		
		var tr = document.createElement("tr");
			
		var td1 = document.createElement("td");
		var texto1 = document.createTextNode(this.json.fecha); 
		td1.appendChild(texto1);
		
		tr.appendChild(td1);
			
		var td2 = document.createElement("td");
		var texto2 = document.createTextNode(this.json.nombre); 
		td2.appendChild(texto2);
		
		tr.appendChild(td2);	
			
		var td3 = document.createElement("td");
		var texto3 = document.createTextNode(this.json.precioUnitario); 
		td3.appendChild(texto3);
		
		tr.appendChild(td3);	
		
		var td4 = document.createElement("td");
		var texto4 = document.createTextNode(this.json.cantidad); 
		td4.appendChild(texto4);
		
		tr.appendChild(td4);
		
		var td5 = document.createElement("td");
		var texto5 = document.createTextNode(this.json.estado.nombre); 
		td5.appendChild(texto5);
		
		tr.appendChild(td5);		
			
		var td6 = document.createElement("td");
		if (puedeQuitar && this.json.estado.nombre != "Rendido" && this.json.claseConsumo != "com.horus.core.datosSalud.Prestacion"){
			var eliminar = new OpcionEliminar(this.fila);
			td6.appendChild(eliminar.a);
		}
		tr.appendChild(td6);
		
			
		return tr;
	}
	
	
} 

