//Reinicia los objetos dejandolos como iniciales
var form_modif_pac = "form_modif_pac";

function resetearEstadoDePaciente(){
	
	//Resetea la imagen
	mostrarImagenPaciente("ninguna");
	
	//Reiniciar el json del paciente
}

function cargarDatosPaciente(){
	//Si no se estaba en el proceso de creacion debo refrescar el elemento del paciente
	//Muestro la imagen segun el sexo del paciente	
	mostrarImagenPaciente(elem_paciente.getSexo());	
	
	//Cargo la informacion en la segunda pestania.
	cargarDatosDeHCyAcomp();
}

//Muestra la imagen segun el sexo del paciente
function mostrarImagenPaciente(sexo){

	document.getElementById("img_no_paciente").style.opacity = 0;
	document.getElementById("img_paciente_hombre").style.opacity = 0;
	document.getElementById("img_paciente_mujer").style.opacity = 0;
	
	document.getElementById("img_no_paciente").style.display = "none";
	document.getElementById("img_paciente_hombre").style.display = "none";
	document.getElementById("img_paciente_mujer").style.display = "none";
	
	var id_img = "img_no_paciente";
	if (sexo == "Masculino"){
		id_img = "img_paciente_hombre";
	}
	if (sexo == "Femenino"){
		id_img = "img_paciente_mujer";
	}
	
	document.getElementById(id_img).style.display = "block";
	dojo.fadeIn({node:id_img}).play();
}
var buscadorRemoto_localidad = Seam.Component.getInstance('admin_Usuarios_Remoto');
var asistidor_localidad	 = new AsistidorDeInputs(buscadorRemoto_localidad, 'localidad', form_modif_pac, false);