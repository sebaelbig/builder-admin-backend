function verificarHorarios(){
			var datosIncorrectos = false;
			var datosEnFalta = false;
			var datosErroneos = false;
			for (i=1; i != 7; i++){
			 	if (document.getElementById("check_"+i).checked == true){
			 		var horaInicio = document.getElementById("horarioInicio_"+i).value;
			 		var horaFin = document.getElementById("horarioFin_"+i).value
			 		if (horaInicio == "" || horaFin == ""){
			 			dojo.addClass(document.getElementById("dia_"+i),"enFalta");
			 			datosEnFalta = true;
			 		} else {
				 		if (!validarHora(horaInicio) || !validarHora(horaFin) ){
					 		 dojo.addClass(document.getElementById("dia_"+i),"enFalta");
					 		 datosIncorrectos = true;
				 		 }else{
				 		    var hora1  = horaInicio.slice(0,2);
							var min1   = horaInicio.slice(3,5);
							var hora2  = horaFin.slice(0,2);
							var min2   = horaFin.slice(3,5);
				 		 	if (hora1 > hora2){
				 		 		datosErroneos = true;
				 		 	}else{
				 		 		if (hora1 == hora2){
				 		 			if (min1 > min2){
				 		 				datosErroneos = true;
				 		 			}else{
				 		 			  dojo.removeClass(document.getElementById("dia_"+i),"enFalta");
				 		 			}
				 		 		}else{
				 		 			dojo.removeClass(document.getElementById("dia_"+i),"enFalta");
				 		 		}
				 		 	}
				 		 	
				 		 }
				 	}				 		
			 	} 
			 }
			if (!(datosIncorrectos || datosEnFalta || datosErroneos)){
				return true;
			}else{
				if (datosIncorrectos){
					mostrarCartel('Error',imagenError,"El formato del horario debe ser 00:00 y los minutos pueden ser 0 o 30");
					return false;
				}
				if (datosEnFalta){	
					mostrarCartel('Error',imagenError,"Debe ingresar la hora de inicio y de fin del dia seleccionado");
					return false;
				}
				if (datosErroneos){	
					mostrarCartel('Error',imagenError,"La hora de inicio debe ser menor a la hora de fin");
					return false;
				}
				
				
			}
					
}

function cambioCheck(value){
			if (document.getElementById("check_"+value).checked == false){
				document.getElementById("horarioInicio_"+value).value = "";
				document.getElementById("horarioFin_"+value).value = "";
				dojo.removeClass(document.getElementById("dia_"+value),"enFalta");
			}else{
				document.getElementById("horarioInicio_"+value).value = "00:00";
				document.getElementById("horarioFin_"+value).value = "23:30";
			}
}
				