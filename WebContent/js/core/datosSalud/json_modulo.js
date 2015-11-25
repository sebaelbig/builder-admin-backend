function JSON_Modulo(json_e, fila_e){
	
	this.json = json_e;
	
	this.fila = fila_e;
	
	this.label = json_e.nombre;
	
	this.cargarDatos = function(){
			
			document.getElementById('codigo').value = this.json.codigo;
			document.getElementById('nombre').value = this.json.nombre;
			
			document.getElementById('obraSocial').value = this.json.obraSocial.nombre;
			document.getElementById('obraSocial').setAttribute("json",dojo.toJson(this.json.obraSocial));
							
	}
	
	this.tomarDatos = function(){
	   
	   this.json.codigo = document.getElementById('codigo').value;
	   this.json.nombre = document.getElementById('nombre').value ;
	   
	   this.json.obraSocial = dojo.fromJson(document.getElementById('obraSocial').getAttribute("json"));
	    
	}
	

	this.toString = function(){
	  return dojo.toJson(this.json);
	}
	
	
	
	this.parsearHTML = function(){
		
		var tr = document.createElement("tr");
			
		var td1 = document.createElement("td");
		var texto1 = document.createTextNode(this.json.codigo); 
		td1.appendChild(texto1);
		
		tr.appendChild(td1);
			
		var td2 = document.createElement("td");
		var texto2 = document.createTextNode(this.json.nombre); 
		td2.appendChild(texto2);
		
		tr.appendChild(td2);	
			
		var td3 = document.createElement("td");
		var texto3 = document.createTextNode(this.json.obraSocial.nombre); 
		td3.appendChild(texto3);
		
		tr.appendChild(td3);	
				
		//if (puedeModificar || puedeEliminar){
		if (puedeEliminar){
			
			var td4 = document.createElement("td");
			
			//if (puedeModificar){
			//	var modificar = new OpcionEditar(this.fila,"javascript:modificarLocal("+this.fila+")");
			//	td4.appendChild(modificar.a);}
			
			if(puedeEliminar){	
				var eliminar = new OpcionEliminar(this.fila);
				td4.appendChild(eliminar.a);
			}
			tr.appendChild(td4);
		}
					
		return tr;
	}
		
} 
