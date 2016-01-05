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

import ar.com.builderadmin.vo.core.usuarios.TipoIDHE_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.perfiles.PerfilBDSimpleAdapter;

public class RolBDSimpleAdapter implements JsonDeserializer<Rol_VO> {
	@Override
	public Rol_VO deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		Rol_VO rol = new Rol_VO();
		try {
			JsonElement elem = null;

			elem = getValor(json, "version");
			if (elem != null) {
				rol.setVersion(elem.getAsInt());
			}
			elem = getValor(json, "id");
			if (elem != null) {
				rol.setId(elem.getAsLong());
			}
			elem = getValor(json, "codigo");
			if (elem != null) {
				rol.setCodigo(elem.getAsString());
			elem = getValor(json, "nombre");
			}
			if (elem != null) {
				rol.setNombre(elem.getAsString());
			}
			
			elem = getValor(json, "tipo_id");
			if (elem != null) {
				rol.setValorTipoID(elem.getAsString());
				
				rol.setTipoId(new TipoIDHE_VO());
				rol.getTipoId().setTipoID(elem.getAsString());
				
				elem = getValor(json, "id_tipo_id");
				rol.getTipoId().setId_tipoId(elem.getAsString());
				
				elem = getValor(json, "ti_id");
				rol.getTipoId().setId(elem.getAsLong());

				elem = getValor(json, "ti_version");
				rol.getTipoId().setVersion(elem.getAsInt());

				elem = getValor(json, "ti_estado");
				rol.getTipoId().setEstado(elem.getAsString());

				elem = getValor(json, "ti_login");
				rol.getTipoId().setLogin((elem!=null)?elem.getAsString():null);
			}

			/*Tipo Rol*/
			rol.setTipoRol(new TipoDeRol_VO());
			
			elem = getValor(json, "id_tipo_rol");
			if (elem != null) {
				rol.setId(elem.getAsLong());
				rol.getTipoRol().setId(elem.getAsLong());
			}
			elem = getValor(json, "prioridad");
			if (elem != null) {
				rol.setPrioridad(elem.getAsString());
				rol.getTipoRol().setPrioridad(elem.getAsString());
			}
			elem = getValor(json, "sitio");
			if (elem != null) {
				rol.setSitio(elem.getAsString());
				rol.getTipoRol().setSitio(elem.getAsString());
			}
			elem = getValor(json, "estado");
			if (elem != null) {
				rol.setEstado(elem.getAsString());
				rol.getTipoRol().setEstado(elem.getAsString());
			}
			elem = getValor(json, "codigoid_x_default");
			if (elem != null) {
				rol.setCodigoID_x_default(elem.getAsString());
				rol.getTipoRol().setCodigoID_x_default(elem.getAsString());
			}
			
			elem = getValor(json, "id_rol");
			if (elem != null) {
				rol.setId_rol(elem.getAsString());
				rol.getTipoRol().setId_rol(elem.getAsString());
			}
			elem = getValor(json, "login");
			if (elem != null) {
				rol.setLogin(elem.getAsString());
				rol.getTipoRol().setLogin(elem.getAsString());
			}
//			//String(‘dd/MM/yyy HH:mm’)
			elem = getValor(json, "fecha_modifico");
			if (elem != null) {
				rol.setFechaModifico(elem.getAsString());
				rol.getTipoRol().setFechaModifico(elem.getAsString());
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