function JSON_ObrasSocial(json_e, fila_e){
	
	this.json = json_e;
	
	this.fila = fila_e;
	
	this.label = json_e.nombre;
	
	this.cargarDatos = function(){
		document.getElementById('nombre').value 	 = this.json.nombre;
	};
	
	this.tomarDatos = function(){
	    this.json.nombre 	  = document.getElementById('nombre').value ;
	};
	
	this.getId = function(){ return this.json.id; };
	
	this.getLabel = function(){
		return this.json.nombre;
	};
	
	this.toString = function(){
	  return dojo.toJson(this.json);
	};
	
	this.parsearHTML = function(){
		
		var tr = document.createElement("tr");
			
		var td1 = document.createElement("td");
		var texto1 = document.createTextNode(this.json.nombre); 
		td1.appendChild(texto1);
		
		tr.appendChild(td1);
			
		return tr;
	};
	
} 