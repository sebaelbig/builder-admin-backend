package ar.com.builderadmin.vo.core.usuarios.roles.profesionales;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import ar.com.builderadmin.vo.core.usuarios.UsuarioBDSimpleAdapter;
import ar.com.builderadmin.vo.core.usuarios.Usuario_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.TipoDeRol_VO;

public class ProfesionalSimpleAdapter implements JsonDeserializer<Profesional_VO>, JsonSerializer<Profesional_VO> {
	
	@Override
	public Profesional_VO deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		
		Profesional_VO prof = new Profesional_VO();
		
		try{
			JsonElement elem = null;
			
			/*Propio del Profesional VO*/
			
			//Hereda de Rol_VO: 
			//private Long id;
			elem = this.getValor(json, "id");
			if (elem != null){
				prof.setId(elem.getAsLong());
			}
			
			//private Integer version;
			elem = this.getValor(json, "version");
			if (elem != null){
				prof.setVersion(elem.getAsInt());
			}
			
			//private String nombre;
			elem = this.getValor(json, "nombre");
			if (elem != null){
				prof.setNombre(elem.getAsString());
			}
			
			//private String codigo;
			elem = this.getValor(json, "codigo");
			if (elem != null){
				prof.setCodigo(elem.getAsString());
			}

			elem = this.getValor(json, "usuario");
			if (elem != null){
				
				//deserializo el usuario
				Usuario_VO usr = new UsuarioBDSimpleAdapter().deserialize(elem, typeOfT, context);
				
				prof.setUsuario(usr);
			}
			
			List<Perfil_VO> perfiles = new ArrayList<Perfil_VO>();
			
			//private TipoRol_VO tipoRol;
			elem = this.getValor(json, "tipoRol");
			if (elem != null){
				prof.setTipoRol(new Gson().fromJson(elem, TipoDeRol_VO.class));
			}
			
			elem = this.getValor(json, "nroMatriculaProvincial");
			if (elem != null){
				prof.setNroMatriculaProvincial(elem.getAsString());
			}
			
			elem = this.getValor(json, "nroMatriculaNacional");
			if (elem != null){
				prof.setNroMatriculaNacional(elem.getAsString());
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return prof;
	}
	
	private JsonElement getValor(JsonElement json, String key) {
		JsonElement elem = json.getAsJsonObject().get(key);
		
		if (elem instanceof JsonNull){
			elem = null;
		}
		 
		return elem;
	}

	//Cuando serializo el Profesional le quito el tipo de rol y todas las funciones asignadas
	@Override
	public JsonElement serialize(Profesional_VO prof, Type arg1, JsonSerializationContext arg2) {
		
		if (prof.getUsuario()!= null){
			//Como estoy serializando un profesional, no me interesa que otros roles tiene el usuario.
			//Si le dejo los roles, me da referencia circular
			prof.getUsuario().getRoles().clear();
		}
		
		return new Gson().toJsonTree(prof);
	}

	
}
