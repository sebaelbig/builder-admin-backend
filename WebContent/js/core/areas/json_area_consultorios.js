function JSON_AreaConsultorios(json_e, fila_e){
	
	this.json = json_e;
	
	this.fila = fila_e;
	
	this.label = json_e.nombre;
	
	this.horarios = new Array();
	
	this.cargarDatos = function(){
		document.getElementById('codigo').value 	 = this.json.codigo;
		document.getElementById('nombre').value = this.json.nombre;
		
	}
	
	this.cargarHorarios = function(json_horario){
		
		this.horarios = new Array();
		
		var h = dojo.fromJson(json_horario);
		
		for (i=0; i != h.length; i++){
			
			this.horarios[i] = new JSON_Horario(h[i]);
						
			this.horarios[i].cargarDatos();
			
		}
		
	}
	
	
	this.tomarDatos = function(){
	   
	    this.json.codigo = document.getElementById('codigo').value;
		
		this.json.nombre = document.getElementById('nombre').value;
		
		this.json.horarios = new Array();
		
		for (i=0; i != this.horarios.length; i++){
				
				this.horarios[i].tomarDatos();
				
				this.json.horarios[i] = this.horarios[i].json; 
		}
			
		 
		
	}
	

	this.toString = function(){
	  return dojo.toJson(this.json);
	}
	
	this.parsearHTML = function(){
		
	}
	
	
}

