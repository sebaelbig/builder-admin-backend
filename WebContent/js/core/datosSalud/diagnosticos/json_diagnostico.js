function JSON_Diagnostico(json_e, fila_e){
	
	this.json = json_e;
	
	this.fila = fila_e;
	
	this.label = json_e.codigoUnico;
	
	this.propiedadesEntidadDiagnostico = new Array('codigoUnico','codigoUnico', 'componentes', 'codigoCIAP', 'descripcionCIAP', 'resumenCIAP', 'terminosIncluidos', 'terminosExcluidos','criteriosDeInclusion', 'consideraciones', 'notaCIAP', 'icpc2', 'correspondencia_CIAP_CIE', 'componente', 'nroComponente', 'codigoCIE', 'capituloCIE', 'nroGrupoPrincipal', 'nroGrupoMenor', 'descripcionCIE');
	
	//Carga los datos para ser modificado
	this.cargarDatos = function(){

		document.getElementById("tipoDiagnostico_ciap").disabled = "disabled";
		document.getElementById("tipoDiagnostico_cie").disabled = "disabled";
				
		if (this.json.ciap_2){
			tipoDiagnosticoCIAP();
			for (i=0; i!=this.propiedadesEntidadDiagnostico.length; i++ ){
				try{
					document.getElementById("formu_ciap:"+this.propiedadesEntidadDiagnostico[i]).value = this.json[this.propiedadesEntidadDiagnostico[i]];
				}catch(e){}
			
			}
		}else{
			tipoDiagnosticoCIE();
			for (i=0; i!=this.propiedadesEntidadDiagnostico.length; i++ ){
				try{
					document.getElementById("formu_cie:"+this.propiedadesEntidadDiagnostico[i]).value = this.json[this.propiedadesEntidadDiagnostico[i]];
				}catch(e){}
			
			}
		}
	
	}
	
	//Toma los datos
	this.tomarDatos = function(){

		if (this.json.ciap_2){
			for (i=0; i!=this.propiedadesEntidadDiagnostico.length; i++ ){
				try{
					this.json[this.propiedadesEntidadDiagnostico[i]] = document.getElementById("formu_ciap:"+this.propiedadesEntidadDiagnostico[i]).value;
				}catch(e){}
			
			}
		}else{
			for (i=0; i!=this.propiedadesEntidadDiagnostico.length; i++ ){
				try{
					this.json[this.propiedadesEntidadDiagnostico[i]] = document.getElementById("formu_cie:"+this.propiedadesEntidadDiagnostico[i]).value;
				}catch(e){}
			
			}
		}
	}
	
	this.getLabel = function(){
		var resul = "("+this.json.codigoUnico+")";
		
		if (this.json.ciap_2){
			resul += this.json.descripcionCIAP;
		}else{
			resul += this.json.descripcionCIE;
		}
		
		return resul;
	}
	

	this.toString = function(){
	  return dojo.toJson(this.json);
	  
	}
	
	this.parsearHTML = function(){
		var str_txt;
		
		var tr = document.createElement("tr");
			
		var td1 = document.createElement("td");
		var texto1 = document.createTextNode(this.json.codigoUnico); 
		td1.appendChild(texto1);
		
		tr.appendChild(td1);
		
		//capitulo
		if (this.json.ciap_2){
			str_txt = this.json.descripcionCIAP;
		}else{
			str_txt = this.json.capituloCIE;
		}
		var td2 = document.createElement("td");
		var texto2 = document.createTextNode(str_txt); 
		td2.appendChild(texto2);
		
		tr.appendChild(td2);	
		
		//categoria	
		if (this.json.ciap_2){
			str_txt = this.json.resumenCIAP;
		}else{
			str_txt = this.json.nroGrupoPrincipal;
		}		
		var td3 = document.createElement("td");
		var texto3 = document.createTextNode(str_txt); 
		td3.appendChild(texto3);
		
		tr.appendChild(td3);	
		
		//subCategoria
		if (this.json.ciap_2){
			str_txt = this.json.resumenCIAP;
		}else{
			str_txt = this.json.nroGrupoMenor;
		}	
		var td4 = document.createElement("td");
		var texto4 = document.createTextNode(str_txt);  
		td4.appendChild(texto4);
		
		tr.appendChild(td4);	
		
		//Tipo de diag
		if (this.json.ciap_2){
			str_txt = "CIAP-2";
		}else{
			str_txt = "CIE-10";
		}
		var td6 = document.createElement("td");
		var texto6 = document.createTextNode(str_txt);  
		td6.appendChild(texto6);
		
		tr.appendChild(td6);
			
		if (puedeModificar || puedeEliminar ){	
			var td5 = document.createElement("td");
			
			if (puedeModificar){
				var modificar = new OpcionEditar(this.fila,"javascript:modificarLocal("+this.fila+")");
				td5.appendChild(modificar.a);
			}
			
			if(puedeEliminar){	
				var eliminar = new OpcionEliminar(this.fila);
				td5.appendChild(eliminar.a);
			}
			
			tr.appendChild(td5);
		}	
			
		return tr;
	}
	
	
} 
