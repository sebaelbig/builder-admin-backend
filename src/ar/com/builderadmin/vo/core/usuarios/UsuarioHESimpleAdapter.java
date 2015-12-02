package ar.com.builderadmin.vo.core.usuarios;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.TipoDePerfil_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.Rol_VO;

public class UsuarioHESimpleAdapter implements JsonDeserializer<Usuario_VO>,
		JsonSerializer<Usuario_VO> {
			
	@Override
	public Usuario_VO deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		Usuario_VO resul = new Usuario_VO();
		try {
			JsonElement elem = getValor(json, "id");
			if (elem != null) {
				resul.setId(Long.valueOf(elem.getAsLong()));
			}
			elem = getValor(json, "version");
			if (elem != null) {
				resul.setVersion(Integer.valueOf(elem.getAsInt()));
			}
			elem = getValor(json, "email");
			if (elem != null) {
				resul.setEmail(elem.getAsString());
			}
			elem = getValor(json, "domicilio");
			if (elem != null) {
				resul.setDomicilio(elem.getAsString());
			}
			elem = getValor(json, "localidad");
			if (elem != null) {
				resul.setLocalidad(elem.getAsString());
			}
			elem = getValor(json, "codigoPostal");
			if (elem != null) {
				resul.setCodigoPostal(elem.getAsString());
			}
			elem = getValor(json, "telefonoParticular");
			if (elem != null) {
				resul.setTelefonoParticular(elem.getAsString());
			}
			elem = getValor(json, "masculino");
			if (elem != null) {
				
				if (elem.getAsString().equalsIgnoreCase("TRUE")){
					resul.setMasculino(true);
					resul.setSexo("Masculino");
				}else{
					resul.setMasculino(false);
					resul.setSexo("Femenino");
				}
			}
			elem = getValor(json, "socioNro");
			if (elem != null) {
				resul.setApellido(elem.getAsString());
			}
			elem = getValor(json, "nombres");
			if (elem != null) {
				resul.setNombres(elem.getAsString());
			}
			elem = getValor(json, "apellidos");
			if (elem != null) {
				resul.setApellido(elem.getAsString());
			}
			elem = getValor(json, "fechaNacimiento");
			if (elem != null) {
				resul.setFechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").parse(elem.getAsString()));
			}
			elem = getValor(json, "usuario");
			if (elem != null) {
				resul.setUsuario(elem.getAsString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resul;
	}

	private JsonElement getValor(JsonElement json, String key) {
		JsonElement elem = json.getAsJsonObject().get(key);
		if ((elem instanceof JsonNull)) {
			elem = null;
		}
		return elem;
	}

	public Usuario_VO acortarUsuario(Usuario_VO usuario) {

		if (usuario != null && (usuario.getRoles().size() > 0)) {

			TipoDePerfil_VO tp = null;

			for (Rol_VO r : usuario.getRoles()) {

				for (Perfil_VO per : r.getPerfiles()) {

					tp = per.getTipoPerfil();

					if (tp != null) {

						tp.setFunciones(null);

					}
				}

			}

		}

		return usuario;

	}

	@Override
	public JsonElement serialize(Usuario_VO usuario, Type arg1,
			JsonSerializationContext arg2) {
		return new Gson().toJsonTree(acortarUsuario(usuario));
	}
}