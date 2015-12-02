package ar.com.builderadmin.ldap.vo;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;

import ar.com.builderadmin.ldap.modelo.RolDeUsuarioHE;

public class RolDeUsuarioHESimpleAdapter implements JsonDeserializer<RolDeUsuarioHE> {
	@Override
	public RolDeUsuarioHE deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		RolDeUsuarioHE rol = new RolDeUsuarioHE();
		try {
			JsonElement elem = null;

			elem = getValor(json, "rol");
			if (elem != null) {
				rol.setRol(elem.getAsString());
			}
			elem = getValor(json, "id_rol");
			if (elem != null) {
				rol.setId_rol(elem.getAsString());
			}
			elem = getValor(json, "codigo_tipo_id");
			if (elem != null) {
				rol.setCodigo_tipo_id(elem.getAsString());
			}
			elem = getValor(json, "idhe");
			if (elem != null) {
				rol.setIdhe(elem.getAsString());
			}
			elem = getValor(json, "usuario");
			if (elem != null) {
				rol.setUsuario(elem.getAsString());
			}
			elem = getValor(json, "estado");
			if (elem != null) {
				rol.setEstado(elem.getAsString());
			}
			elem = getValor(json, "login");
			if (elem != null) {
				rol.setLogin(elem.getAsString());
			}
			//String(‘dd/MM/yyy HH:mm’)
			elem = getValor(json, "fecha");
			if (elem != null) {
				rol.setFecha(elem.getAsString());
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