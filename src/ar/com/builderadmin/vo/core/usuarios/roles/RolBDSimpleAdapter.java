package ar.com.builderadmin.vo.core.usuarios.roles;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.perfiles.PerfilBDSimpleAdapter;

public class RolBDSimpleAdapter implements JsonDeserializer<Rol_VO> {
	@Override
	public Rol_VO deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		Rol_VO rol = new Rol_VO();
		try {
			JsonElement elem = null;


			elem = getValor(json, "id");
			if (elem != null) {
				rol.setId(elem.getAsLong());
			}
			elem = getValor(json, "codigo");
			if (elem != null) {
				rol.setCodigo(elem.getAsString());
			}
			elem = getValor(json, "nombre");
			if (elem != null) {
				rol.setNombre(elem.getAsString());
			}
			
			elem = getValor(json, "valor_tipo_id");
			if (elem != null) {
				rol.setValorTipoID(elem.getAsString());
			}
			
			elem = getValor(json, "perfiles");
			if (elem != null) {
				List<Perfil_VO> perfiles = 
						(List<Perfil_VO>) 
						new GsonBuilder()
							.registerTypeAdapter(Perfil_VO.class, new PerfilBDSimpleAdapter())
							.create()
							.fromJson(elem, new TypeToken<List<Perfil_VO>>() {}.getType());
				
				
				rol.setPerfiles(perfiles);
			}
			
//			elem = getValor(json, "id_rol");
//			if (elem != null) {
//				rol.setId_rol(elem.getAsString());
//			}else{
//				elem = getValor(json, "id");
//				if (elem != null) {
//					rol.setId_rol(elem.getAsString());
//				}
//			}
//			elem = getValor(json, "estado");
//			if (elem != null) {
//				rol.setEstado(elem.getAsString());
//			}
//			elem = getValor(json, "login");
//			if (elem != null) {
//				rol.setLogin(elem.getAsString());
//			}
//			elem = getValor(json, "prioridad");
//			if (elem != null) {
//				rol.setPrioridad(elem.getAsString());
//			}
//			elem = getValor(json, "codigoID_x_default");
//			if (elem != null) {
//				rol.setCodigoID_x_default(elem.getAsString());
//			}
//			//String(‘dd/MM/yyy HH:mm’)
//			elem = getValor(json, "fecha");
//			if (elem != null) {
//				rol.setFechaModifico(elem.getAsString());
//			}
//			elem = getValor(json, "sitio");
//			if (elem != null) {
//				rol.setSitio(elem.getAsString());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rol;
	}

	private JsonElement getValor(JsonElement json, String key) {
		JsonElement elem = json.getAsJsonObject().get(key);
		if ((elem instanceof JsonNull)) {
			elem = null;
		}
		return elem;
	}
}