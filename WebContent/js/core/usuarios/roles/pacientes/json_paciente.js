function JSON_Paciente(json_p, fila_p, formulario){
	
	this.json = json_p;
		
	this.fila = fila_p;
	
	this._formulario = formulario;
	
	var propiedadesEntidadUsuario = new Array('apellido', 'codigoPostal', 'contrasena', 'domicilio', 'eMail','fechaNacimiento', 'localidad', 'nombres', 'nombreUsuario', 'nroCUIT','nroDocumento', 'sexo', 'telefonoParticular', 'tipoDocumento');
	
	this.getSrcFoto = function(){
		return this.json.usuario.foto;
	};
	
	//Carga los datos
	this.cargarDatos = function(){
		
		for (var i=0; i!=propiedadesEntidadUsuario.length; i++ ){
			try{
				if (this._formulario){
					document.getElementById(this._formulario+":"+propiedadesEntidadUsuario[i]).value = this.getValorJson(  this.json.usuario[propiedadesEntidadUsuario[i]] );
				}else{
					document.getElementById(propiedadesEntidadUsuario[i]).value = this.getValorJson( this.json.usuario[propiedadesEntidadUsuario[i]] );
				}
			}catch(e){}
		}
		
	};
		
		
	//Toma los datos
	this.tomarDatos = function(){
	
		for (var i=0; i!=propiedadesEntidadUsuario.length; i++ ){
			try{
				if (this._formulario){
					this.json.usuario[propiedadesEntidadUsuario[i]] = this.getValor( this._formulario+":"+propiedadesEntidadUsuario[i] );	
				}else{
					this.json.usuario[propiedadesEntidadUsuario[i]] = this.getValor( propiedadesEntidadUsuario[i] );
				}
			}catch(e){}
		}
		
	};

	this.getValor = function(id){
		var valor = document.getElementById(id).value;
		if (valor){
			return valor;
		}else{
			return null;
		}
	};
	
	this.getValorJson = function(valorJson){
		if (valorJson){
			return valorJson;
		}else{
			return null;
		}
	};
	
	this.toString = function(){
	  return dojo.toJson(this.json);
	};
	
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
			
		if (puedeModificar){	
			var td3 = document.createElement("td");
			
			if (puedeModificar){
				var modificar = new OpcionEditar(this.fila, "javascript:modificarExterno("+this.fila+")");
				td3.appendChild(modificar.a);
			}
						
			tr.appendChild(td3);
		}
			
		return tr;
	};
	
	this.getLabel = function(){
		return this.json.usuario.apellido;// + " (" + this.json.usuario.usuario + " - "+this.json.usuario.nroDocumento+")";
	};
	
	this.getUserName = function(){
		return this.json.usuario.usuario;
	};
	
	this.getNroAfiliado = function(nombreProductoOS){
		var resul = "Ingrese un nro de afiliado";
		
		for (var i=0; i!=this.json.productosObrasSocialPaciente.length ; i++ ){
			if (this.json.productosObrasSocialPaciente[i].producto.nombre == nombreProductoOS){
				resul = this.json.productosObrasSocialPaciente[i].nroAfiliado;
			}
		}
		
		return resul;
	};
	
	this.getApellido = function(){
		return this.json.usuario.apellido;
	};
	this.getTelefonoParticular = function(){
		return this.json.usuario.telefonoParticular;
	};
	this.getTelefonoCelular = function(){
		return this.json.usuario.telefonoCelular;
	};
	this.getSexo = function(){
		return this.json.usuario.masculino;
	};
	this.getNroDocumento = function(){
		return this.json.usuario.nroDocumento;
	};
	this.getNombres = function(){
		return this.json.usuario.nombres;
	};
	this.getLocalidad = function(){
		return this.json.usuario.localidad;
	};
	this.getFechaNacimiento = function(){
		return this.json.usuario.fechaNacimiento;
	};
	this.getEMail = function(){
		return this.json.usuario.eMail;
	};
	this.getDomicilio = function(){
		return this.json.usuario.domicilio;
	};
	this.getNroHistoriaClinica = function(){
		return this.json.historiaClinica.numero;
	};
	this.getId = function(){
		return this.json.id;
	};
	this.getStrFechaNacimiento = function(){
		return this.json.usuario.str_fechaNacimiento;
	};
	this.getFoto = function(){
		return this.json.usuario.foto;
	};
	this.setUsuario = function(usr){
		this.json.usuario = usr;
	};
	this.getUsuario = function(){
		return this.json.usuario.usuario;
	};
} 