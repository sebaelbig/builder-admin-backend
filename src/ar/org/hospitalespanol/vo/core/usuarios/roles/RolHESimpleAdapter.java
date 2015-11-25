package ar.org.hospitalespanol.vo.core.usuarios.roles;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;

public class RolHESimpleAdapter implements JsonDeserializer<RolHE_VO> {
	@Override
	public RolHE_VO deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		RolHE_VO rol = new RolHE_VO();
		try {
			JsonElement elem = null;

			elem = getValor(json, "rol");
			if (elem != null) {
				rol.setRol(elem.getAsString());
			}
			elem = getValor(json, "id_rol");
			if (elem != null) {
				rol.setId_rol(elem.getAsString());
			}else{
				elem = getValor(json, "id");
				if (elem != null) {
					rol.setId_rol(elem.getAsString());
				}
			}
			elem = getValor(json, "estado");
			if (elem != null) {
				rol.setEstado(elem.getAsString());
			}
			elem = getValor(json, "login");
			if (elem != null) {
				rol.setLogin(elem.getAsString());
			}
			elem = getValor(json, "prioridad");
			if (elem != null) {
				rol.setPrioridad(elem.getAsString());
			}
			elem = getValor(json, "codigoID_x_default");
			if (elem != null) {
				rol.setCodigoID_x_default(elem.getAsString());
			}
			//String(‘dd/MM/yyy HH:mm’)
			elem = getValor(json, "fecha");
			if (elem != null) {
				rol.setFechaModifico(elem.getAsString());
			}
			elem = getValor(json, "sitio");
			if (elem != null) {
				rol.setSitio(elem.getAsString());
			}
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