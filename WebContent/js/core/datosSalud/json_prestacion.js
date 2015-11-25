function JSON_Prestacion(json_e, fila_e){
	
	this.json = json_e;
	
	this.fila = fila_e;
	
	this.cargarDatos = function(){
		document.getElementById('codigoUnico').value = this.json.codigoUnico;
	   	document.getElementById('nombre').value = this.json.nombre; 
	   		   	
	   	sel1 = 	document.getElementById('dojo_tipoPrestacion');
		str1 = this.json.tipoPrestacion.nombre+" ("+this.json.tipoPrestacion.codigo+")";
		for (i=0; i<sel1.options.length; i++) {
		       t = sel1.options[i].text;
		       if (sel1.options[i].text == str1) {
						sel1.selectedIndex = i;
		     }
	     }
	   	
	   	document.getElementById('tipoPrestacion').value = document.getElementById('dojo_tipoPrestacion').value; 
	   	

        
        
        sel2 = 	document.getElementById('dojo_nomenclador');
		str2 = this.json.nomenclador.nombre+" ("+this.json.nomenclador.codigo+")";
		for (i=0; i<sel2.options.length; i++) {
		       t = sel2.options[i].text;
		       if (sel2.options[i].text == str2) {
						sel2.selectedIndex = i;
		     }
	     }

	   	document.getElementById('nomenclador').value = document.getElementById('dojo_nomenclador').value; 
	   		   	 
	   	document.getElementById('indicacion_preparacion').value = this.json.indicacion_preparacion; 
	    document.getElementById('contraindicaciones').value = this.json.contraindicaciones;
	    document.getElementById('restricciones').value = this.json.restricciones;
					
	}
	
	this.tomarDatos = function(){
	   this.json.codigoUnico 		    = document.getElementById('codigoUnico').value;
	   this.json.nombre 		        = document.getElementById('nombre').value;
	   this.json.nomenclador.codigo 		    = document.getElementById('nomenclador').value;
	   this.json.tipoPrestacion.codigo 	    = document.getElementById('tipoPrestacion').value;
	   this.json.indicacion_preparacion = document.getElementById('indicacion_preparacion').value;
	   this.json.contraindicaciones     = document.getElementById('contraindicaciones').value;
	   this.json.restricciones 		    = document.getElementById('restricciones').value;
	      
	}
	
	this.label = "("+this.json.codigoUnico+") "+this.json.nombre;
	this.getLabel = function(){
		return this.label;
	}

	this.toString = function(){
	  return dojo.toJson(this.json);
	  
	}
	
	this.parsearHTML = function(){
		
		var tr = document.createElement("tr");
			
		var td1 = document.createElement("td");
		var texto1 = document.createTextNode(this.json.codigoUnico); 
		td1.appendChild(texto1);
		
		tr.appendChild(td1);
			
		var td2 = document.createElement("td");
		var texto2 = document.createTextNode(this.json.nombre); 
		td2.appendChild(texto2);
		
		tr.appendChild(td2);	
			
		var td3 = document.createElement("td");
		var texto3 = document.createTextNode(this.json.tipoPrestacion.nombre +" ("+this.json.tipoPrestacion.codigo+")"); 
		td3.appendChild(texto3);
		
		tr.appendChild(td3);	
		
		var td4 = document.createElement("td");
		var texto4 = document.createTextNode(this.json.nomenclador.nombre+" ("+this.json.nomenclador.codigo+")"); 
		td4.appendChild(texto4);
		
		tr.appendChild(td4);	
			
			
		if (puedeModificar || puedeEliminar){	
			var td5 = document.createElement("td");
			
			if (puedeModificar){
				var modificar = new OpcionEditar(this.fila,"javascript:modificarLocal("+this.fila+")");
				td5.appendChild(modificar.a);}
			
			if(puedeEliminar){	
				var eliminar = new OpcionEliminar(this.fila);
				td5.appendChild(eliminar.a);
			}
			tr.appendChild(td5);
		}
			
		return tr;
	}
	
} 