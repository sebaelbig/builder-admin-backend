function JSON_ProfesionalPrimerTurno(json_p, indice_p) {

	this.json = json_p;

	this.indice = indice_p;

	this._resaltado = false;

	this.setResaltado = function(bool) {
		this._resaltado = bool;
	}

	var propiedadesEntidad = new Array('apellido', 'nroMatricula',
			'numeroTurno', 'nombre', 'horaDesde', 'frecuencia',
			'str_horaDesde', 'diaPrimerTurnoLibre', 'str_diaPrimerTurnoLibre',
			'horaPrimerTurnoLibre', 'str_horaPrimerTurnoLibre');

	// Carga los datos
	this.cargarDatos = function() {
		for ( var i = 0; i != propiedadesEntidad.length; i++) {
			try {
				document.getElementById(propiedadesEntidad[i]).value = this.json[propiedadesEntidad[i]];
			} catch (e) {
			}
		}
	}

	// Toma los datos
	this.tomarDatos = function() {
		for ( var i = 0; i != propiedadesEntidad.length; i++) {
			try {
				this.json[propiedadesEntidad[i]] = document
						.getElementById(propiedadesEntidad[i]).value;
			} catch (e) {
			}
		}
	}

	this.toString = function() {
		return dojo.toJson(this.json);
	}

	this.parsearHTML = function() {
		var tr = document.createElement("tr");
		return tr;
	}

	this.getLabel = function() {
		return "<span class=\"negrita\" >" + this.json.apellido + ", "
				+ this.json.nroMatricula
				+ "</span> (1T: <span class=\"negrita\" >" + this.json.nombre
				+ "</span>, <span class=\"negrita\" >"
				+ this.json.str_diaPrimerTurnoLibre
				+ "</span> a las <span class=\"negrita\" >"
				+ this.json.str_horaPrimerTurnoLibre + "</span>)";
	}

	this.getClass = function() {

		var resul = this.json.nombre;

		if (this._resaltado) {
			resul += " itemListaDistinguido";
		}

		return resul;
	}

	this.getNumeroTurno = function() {
		return this.json.numeroTurno;
	}

	this.getIdBloqueTurno = function() {
		return this.indice;
	}

	this.getFechaTurno = function() {
		return this.json.str_diaPrimerTurnoLibre;
	}
	this.getHoraTurno = function() {
		return this.json.str_horaPrimerTurnoLibre;
	}
	this.getStr_horaInicioBloqueTurno = function() {
		return this.json.str_horaDesde;
	}
	
	this.equal = function(otroBT){
		return otroBT.getIdBloqueTurno() == this.getIdBloqueTurno();
	}
}