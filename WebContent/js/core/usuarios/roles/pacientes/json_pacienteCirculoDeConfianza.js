//Se deben esepecificar las variables globales: puedeModificar y puedeVerHX
function JSON_PacienteCC(json_p, fila_p, formulario){
	
	this.json = json_p;
	
	this.fila = fila_p;
	
	this._formulario = formulario;
	
	
	var propiedadesEntidadUsuario = new Array('apellido', 'nombres', 'id', 'sucursal', 'nroDocumento');
	var propiedadesEntidad = new Array('id', 'version', 'contrasena', 'obrasSocialesPaciente', 'historiaClinica','nombre', 'codigo', 'usuario', 'tipoRol', 'idUsuario','perfiles');
	
	this.getSrcFoto = function(){
		return this.json.usuario.foto;
	}
	
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
		
	}
		
		
	//Toma los datos
	this.tomarDatos = function(){
	
		for (var i=0; i!=propiedadesEntidadUsuario.length; i++ ){
			try{
				if (this._formulario){
					this.json.usuario[propiedadesEntidadUsuario[i]] = this.getValor( this._formulario+":"+propiedadesEntidadUsuario[i] );	
					this.json[propiedadesEntidad[i]] = this.getValor( this._formulario+":"+propiedadesEntidad[i] );
				}else{
					this.json.usuario[propiedadesEntidadUsuario[i]] = this.getValor( propiedadesEntidadUsuario[i] );
					this.json[propiedadesEntidad[i]] = this.getValor( propiedadesEntidad[i] );
				}
			}catch(e){}
		}
		
	}

	this.getValor = function(id){
		var valor = document.getElementById(id).value;
		if (valor){
			return valor;
		}else{
			return null;
		}
	}
	
	this.getValorJson = function(valorJson){
		if (valorJson){
			return valorJson;
		}else{
			return null;
		}
	}
	
	this.toString = function(){
	  return dojo.toJson(this.json);
	}
	
	this.parsearHTML = function(){
		
		var tr = document.createElement("tr");
			
		var td1 = document.createElement("td");
		var texto1 = document.createTextNode(this.json.apellido); 
		td1.appendChild(texto1);
		
		tr.appendChild(td1);
			
		var td2 = document.createElement("td");
		var texto2 = document.createTextNode(this.json.nombres); 
		td2.appendChild(texto2);
		
		tr.appendChild(td2);	
		
		var td4 = document.createElement("td");
		var texto4 = document.createTextNode(this.json.nroDocumento); 
		td4.appendChild(texto4);
		
		tr.appendChild(td4);
			
		if (puedeModificar || puedeVerHX){	
			var td3 = document.createElement("td");
			
			if (puedeModificar){
				var modificar = new OpcionEditar(this.fila, "javascript:modificarExterno("+this.fila+")");
				td3.appendChild(modificar.a);
			}
			
			if (puedeVerHX){			
				var ficha = new OpcionVerHC(this.fila);
				td3.appendChild(ficha.a);
			}	
			
			tr.appendChild(td3);
		}
			
		return tr;
	}
	
	this.getLabel = function(){
		return this.json.usuario.apellido + ", "+ this.json.usuario.nombres + " (" + this.json.usuario.nombreUsuario + " - "+this.json.usuario.nroDocumento+")";
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
	this.getFoto = function(){
		return this.json.usuario.foto;
	}
	this.setUsuario = function(usr){
		this.json.usuario = usr;
	}
} 

var imagenHC ="/horus_fe/img/historiaClinica/hc.png";

function OpcionVerHC(fila){
	this.a = document.createElement("a");
    imagen = document.createElement("img");
    imagen.setAttribute("src",imagenHC);
    this.a.setAttribute("value","Ver Historia Clinica");
    imagen.setAttribute("class","imagenEscalable");
    imagen.setAttribute("title","Ver Historia Clinica");
    imagen.setAttribute("alt","Ver Historia Clinica");
	this.a.setAttribute("onclick","javascript:verHC("+fila+")");
	this.a.appendChild(imagen);
}

function verHC(fila){
	var link = document.getElementById('link_hc').getAttribute("href");
	link += "&idElemento="+elementos[fila].json.id;
	location.href = link;
} 