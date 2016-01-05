package ar.com.builderadmin.vo.core.usuarios;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.List;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import ar.com.builderadmin.vo.core.usuarios.roles.RolBDSimpleAdapter;
import ar.com.builderadmin.vo.core.usuarios.roles.Rol_VO;

public class UsuarioBDSimpleAdapter implements JsonDeserializer<Usuario_VO>{
			
	@Override
	public Usuario_VO deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		SimpleDateFormat diaFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
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
			elem = getValor(json, "apellido");
			if (elem != null) {
				resul.setApellido(elem.getAsString());
			}
			elem = getValor(json, "domicilio");
			if (elem != null) {
				resul.setDomicilio(elem.getAsString());
			}
			elem = getValor(json, "telefonos");
			if (elem != null) {
				resul.setTelefonos(elem.getAsString());
			}
			elem = getValor(json, "nro_documento");
			if (elem != null) {
				resul.setNroDocumento(elem.getAsString());
			}
			elem = getValor(json, "tipo_documento");
			if (elem != null) {
				resul.setTipoDocumento(elem.getAsString());
			}
			elem = getValor(json, "foto");
			if (elem != null) {
				resul.setFoto(elem.getAsString());
			}
			elem = getValor(json, "usuario");
			if (elem != null) {
				resul.setUsuario(elem.getAsString());
			}
			elem = getValor(json, "nombres");
			if (elem != null) {
				resul.setNombres(elem.getAsString());
			}
			elem = getValor(json, "codigo_postal");
			if (elem != null) {
				resul.setCodigoPostal(elem.getAsString());
			}
			elem = getValor(json, "email");
			if (elem != null) {
				resul.setEmail(elem.getAsString());
			}
			elem = getValor(json, "fecha_nacimiento");
			if (elem != null) {
				resul.setFechaNacimiento(diaFormat.parse(elem.getAsString()));
			}
			elem = getValor(json, "fecha_ultimo_ingreso");
			if (elem != null) {
				resul.setFechaUltimoIngreso(diaFormat.parse(elem.getAsString()));
			}
			elem = getValor(json, "localidad");
			if (elem != null) {
				resul.setLocalidad(elem.getAsString());
			}
			elem = getValor(json, "nro_cuit");
			if (elem != null) {
				resul.setNroCUIT(elem.getAsString());
			}
			elem = getValor(json, "respuesta_seguridad");
			if (elem != null) {
				resul.setRespuestaSeguridad(elem.getAsString());
			}
			elem = getValor(json, "pregunta_seguridad");
			if (elem != null) {
				resul.setRespuestaSeguridad(elem.getAsString());
			}
			elem = getValor(json, "cantidad_intentos");
			if (elem != null) {
				resul.setCantidadIntentos(elem.getAsInt());
			}

			elem = getValor(json, "roles");
			if (elem != null) {
				List<Rol_VO> roles = 
						(List<Rol_VO>) 
						new GsonBuilder()
							.registerTypeAdapter(Rol_VO.class, new RolBDSimpleAdapter())
							.create()
							.fromJson(elem, new TypeToken<List<Rol_VO>>() {}.getType());
				
				
				resul.setRoles(roles);
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

}