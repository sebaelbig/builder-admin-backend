


function JSON_Subproducto(json_e, fila_e){
	
	this.json = json_e;
	
	this.fila = fila_e;
	
	this.label = json_e.nombre;
	
	this.cargarDatos = function(){
			
			document.getElementById('codigo').value = this.json.codigo;
			document.getElementById('nombre').value = this.json.nombre;
	}
	
	this.tomarDatos = function(){
	  	
	  	this.json.nombre  = document.getElementById('nombre').value ;
      	this.json.codigo = document.getElementById('codigo').value;
      	     	
      	var os = dojo.fromJson(document.getElementById('obraSocial').getAttribute("json"));
      	
      	this.json.nombreObraSocial  = os.nombre ;
      	this.json.idObraSocial = os.id; 
      	
      	//var	json_object = "{'id':"+this.json.id+",'version':"+this.json.version+",'nombre':'"+nom+"','nombreObraSocial':'"+nombreOS+"','idObraSocial':"+idOS+"}";
      	//this.json = dojo.fromJson(json_object);
	}
	

	this.toString = function(){
	  return dojo.toJson(this.json);
	}
	

	
	this.parsearHTML = function(){
		
		var tr = document.createElement("tr");
			
		var td1 = document.createElement("td");
		var texto1 = document.createTextNode(this.json.nombreObraSocial); 
		td1.appendChild(texto1);
		
		tr.appendChild(td1);
		
		var td2 = document.createElement("td");
		var texto2 = document.createTextNode(this.json.codigo); 
		td2.appendChild(texto2);
		
		tr.appendChild(td2);	
		
		
		var td3 = document.createElement("td");
		var texto3 = document.createTextNode(this.json.nombre); 
		td3.appendChild(texto3);
		
		tr.appendChild(td3);	
							
		if (puedeModificar || puedeEliminar){	
			var td3 = document.createElement("td");
			
			if (puedeModificar){
				var modificar = new OpcionEditar(this.fila,"javascript:modificarProducto("+this.fila+")");
				td3.appendChild(modificar.a);}
			
			if(puedeEliminar){	
				var eliminar = new OpcionEliminar(this.fila);
				td3.appendChild(eliminar.a);
			}
			tr.appendChild(td3);
		}
		
		
			
		return tr;
	}
	
	
} 

