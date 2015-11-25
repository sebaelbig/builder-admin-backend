function JSON_Horario(json_e){
	
	this.json = json_e;
	
	this.cargarDatos = function(){
	  if (this.json.seleccionado == true){
		document.getElementById("check_"+this.json.dia.numero).checked = this.json.seleccionado ;
		document.getElementById("horarioInicio_"+this.json.dia.numero).value = this.json.horarioInicio; 
		document.getElementById("horarioFin_"+this.json.dia.numero).value = this.json.horarioFin;
	  }
	}
	
	this.tomarDatos = function(){
		this.json.seleccionado = document.getElementById("check_"+this.json.dia.numero).checked;
		if (this.json.seleccionado == true){
			this.json.horarioInicio = document.getElementById("horarioInicio_"+this.json.dia.numero).value; 
			this.json.horarioFin = document.getElementById("horarioFin_"+this.json.dia.numero).value;
		}
	}
	
	
	this.toString = function(){
	  return dojo.toJson(this.json);
	}
	
	
	
	
	
	
} 