function JSON_Profesional(json_p, fila_p){
	
	this.json = json_p;
		
	this.fila = fila_p;
	
	var propiedadesEntidad = new Array('nroMatriculaNacional', 'nroMatriculaProvincial', 'contratos', 'obrasSocialesLimitadas', 'id', 'version', 'codigo', 'perfiles', 'usuario', 'tipoRol');
	
	//Carga los datos
	this.cargarDatos = function(){
		for (i=0; i!=propiedadesEntidad.length; i++ ){
			try{
				document.getElementById(propiedadesEntidad[i]).value = this.json[propiedadesEntidad[i]];
			}catch(e){}
		
		}
	}
	
	//Toma los datos
	this.tomarDatos = function(){
		for (i=0; i!=propiedadesEntidad.length; i++ ){
			try{
				this.json[propiedadesEntidad[i]] = document.getElementById(propiedadesEntidad[i]).value;
			}catch(e){}
		}
	}
	
	this.toString = function(){
	  return dojo.toJson(this.json);
	}
	
	this.parsearHTML = function(){
		
		var tr = document.createElement("tr");
			
		var td1 = document.createElement("td");
		var texto1 = document.createTextNode(this.json.usuario.apellido); 
		td1.appendChild(texto1);
		
		tr.appendChild(td1);
			
		var td2 = document.createElement("td");
		var texto2 = document.createTextNode(this.json.usuario.nombres); 
		td2.appendChild(texto2);
		
		tr.appendChild(td2);	
		
		var td4 = document.createElement("td");
		var texto4 = document.createTextNode(this.json.usuario.nroDocumento); 
		td4.appendChild(texto4);
		
		tr.appendChild(td4);
			
		return tr;
	}
	
	this.getLabel = function(){
		return this.json.usuario.apellido + ", "+ this.json.usuario.nombres + " (" + this.json.usuario.nroDocumento + ")";
	}
	
	this.getJson = function(){
		return this.json;
	}
	
	this.getId = function(){ return this.json.id; }
} 