package ar.org.hospitalespanol.vo.historiaClinica.antecedentes;

import java.lang.reflect.Type;
import java.util.List;

import ar.org.hospitalespanol.vo.core.datosSalud.diagnosticos.Diagnostico_VO;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

public class AntecedenteFamiliarHistoriaClinicaSimpleAdapter implements JsonDeserializer<AntecedenteFamiliarHistoriaClinica_VO>, JsonSerializer<AntecedenteFamiliarHistoriaClinica_VO> {
	
	public AntecedenteFamiliarHistoriaClinica_VO deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		
		AntecedenteFamiliarHistoriaClinica_VO ante = new AntecedenteFamiliarHistoriaClinica_VO();
		
		Gson gson = new Gson();
		
		try{
			JsonElement elem = null;
			
			//List<Diagnostico_VO> diagnosticos
			elem = this.getValor(json, "diagnosticos");
			if (elem != null){
				
				Type listType = new TypeToken<List<Diagnostico_VO>>() {}.getType();

				List<Diagnostico_VO> list = gson.fromJson(elem, listType);

				for (Diagnostico_VO d : list){

					if (d!=null){
						ante.getDiagnosticos().add(d);
					}
					
				}
				
			}
			
			//private Long id;
			elem = this.getValor(json, "id");
			if (elem != null){
				ante.setId(elem.getAsLong());
			}
			
			//private Integer version;
			elem = this.getValor(json, "version");
			if (elem != null){
				ante.setVersion(elem.getAsInt());
			}
			
			//private String nombre;
			elem = this.getValor(json, "parentezco");
			if (elem != null){
				ante.setParentezco(elem.getAsString());
			}
			
			//private String codigo;
			elem = this.getValor(json, "apellido");
			if (elem != null){
				ante.setApellido(elem.getAsString());
			}

			elem = this.getValor(json, "nombre");
			if (elem != null){
				
				ante.setNombre(elem.getAsString());
			}
			
			elem = this.getValor(json, "razonFallecimiento");
			if (elem != null){
				
				ante.setRazonFallecimiento(elem.getAsString());
			}
			
			elem = this.getValor(json, "edad");
			if (elem != null){
				
				ante.setEdad(elem.getAsString());
			}
			
			elem = this.getValor(json, "descripcion");
			if (elem != null){
				
				ante.setDescripcion(elem.getAsString());
			}
			
			//private TipoRol_VO tipoRol;
			elem = this.getValor(json, "vive");
			if (elem != null){
				ante.setVive(elem.getAsBoolean());
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return ante;
	}
	
	private JsonElement getValor(JsonElement json, String key) {
		JsonElement elem = json.getAsJsonObject().get(key);
		
		if (elem instanceof JsonNull){
			elem = null;
		}
		 
		return elem;
	}

	//Cuando serializo el usuario le quito el tipo de rol y todas las funciones asignadas
	public JsonElement serialize(AntecedenteFamiliarHistoriaClinica_VO ante, Type tipo, JsonSerializationContext contexto) {
		
		return new Gson().toJsonTree(ante);
		
	}
	
}
