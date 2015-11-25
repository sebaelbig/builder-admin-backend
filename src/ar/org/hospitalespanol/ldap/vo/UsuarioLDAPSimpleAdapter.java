package ar.org.hospitalespanol.ldap.vo;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;

public class UsuarioLDAPSimpleAdapter implements JsonDeserializer<UsuarioLDAP_VO> {
	
	@Override
	public UsuarioLDAP_VO deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		UsuarioLDAP_VO rol = new UsuarioLDAP_VO();
		try {
			JsonElement elem = null;

			elem = getValor(json, "usuario");
			if (elem != null) {
				rol.setUsuario(elem.getAsString());
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
/**
 * new GsonBuilder()
									.registerTypeAdapter(UsuarioLDAP_VO.class, new UsuarioLDAP_VOSimpleAdapter())
									.create()
									.fromJson(usuario_con_roles, UsuarioLDAP_VO.class);
 */
	
	
}
