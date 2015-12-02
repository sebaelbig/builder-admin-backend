package ar.com.builderadmin.model.core.templates;

public interface I_Template {

	
	public static String paciente = "Paciente";
	public static String documento = "Documento";
	public static String medico = "M�dico";
	public static String fecha = "Fecha";
	public static String estudio = "Estudio";
	public static String ubicacion = "Ubicaci�n";
	
	
	
	public String getPacienteT();
	
	public String getDocumentoT(); 		
	
	public String getMedicoT();
	
	public String getFechaT();
	
	public String getEstudioT();
	
	public String getUbicacionT();
	
	public String getInformeT();
}
