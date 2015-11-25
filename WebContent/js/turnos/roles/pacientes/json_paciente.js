function JSON_Paciente(json_p, fila_p){
	
	this.json = json_p;
		
	this.fila = fila_p;
	
	var propiedadesEntidadUsuario = new Array('apellido', 'codigoPostal', 'contrasena', 'domicilio', 'eMail','fechaNacimiento', 'localidad', 'nombres', 'nombreUsuario', 'nroCUIT','nroDocumento', 'otrostelefonos', 'sexo', 'telefonoCelular', 'telefonoParticular', 'tipoDocumento');
	
	//Carga los datos
	this.cargarDatos = function(){
		
		for (var i=0; i!=propiedadesEntidadUsuario.length; i++ ){
			try{
			
				document.getElementById(propiedadesEntidadUsuario[i]).value = this.json.usuario[propiedadesEntidadUsuario[i]];
				
			}catch(e){}
		}
		
	}
	
	//Toma los datos
	this.tomarDatos = function(){
	
		for (var i=0; i!=propiedadesEntidadUsuario.length; i++ ){
			try{
			
				this.json.usuario[propiedadesEntidadUsuario[i]] = document.getElementById(propiedadesEntidadUsuario[i]).value;
				
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
			
		if (puedeModificar || puedeEliminar){	
			var td3 = document.createElement("td");
			
			if (puedeModificar){
				var modificar = new OpcionEditar(this.fila, "javascript:modificarExterno("+this.fila+")");
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
		return this.json.usuario.apellido;// + " (" + this.json.usuario.usuario + " - Nro Doc: "+this.json.usuario.nroDocumento+")";
	}
	
	this.getNroAfiliado = function(nombreProductoOS){
		var resul = "Ingrese un nro de afiliado";
		
		for (var i=0; i!=this.json.productosObrasSocialPaciente.length ; i++ ){
			if (this.json.productosObrasSocialPaciente[i].producto.nombre == nombreProductoOS){
				resul = this.json.productosObrasSocialPaciente[i].nroAfiliado
			}
		}
		
		return resul;
	}
	
	this.getApellido = function(){
		return this.json.usuario.apellido;
	}
	this.getTelefonoParticular = function(){
		return this.json.usuario.telefonoParticular;
	}
	this.getTelefonoCelular = function(){
		return this.json.usuario.telefonoCelular;
	}
	this.getSexo = function(){
		return this.json.usuario.sexo;
	}
	this.getNroDocumento = function(){
		return this.json.usuario.nroDocumento;
	}
	this.getNombres = function(){
		return this.json.usuario.nombres;
	}
	this.getLocalidad = function(){
		return this.json.usuario.localidad;
	}
	this.getFechaNacimiento = function(){
		return this.json.usuario.fechaNacimiento;
	}
	this.getEMail = function(){
		return this.json.usuario.eMail;
	}
	this.getDomicilio = function(){
		return this.json.usuario.domicilio;
	}
	this.getNroHistoriaClinica = function(){
		return this.json.historiaClinica.numero;
	}
	this.getId = function(){
		return this.json.id;
	}
	this.getStrFechaNacimiento = function(){
		return this.json.usuario.str_fechaNacimiento;
	}
	this.getUsuario = function(){
		return this.json.usuario.usuario;
	}
} 