package ar.org.hospitalespanol.vo.core.usuarios.roles.perfiles;

import java.lang.reflect.Type;
import java.util.List;

import ar.org.hospitalespanol.vo.FuncionHorus_VO;
import ar.org.hospitalespanol.vo.core.usuarios.FuncionHorusBDSimpleAdapter;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class PerfilBDSimpleAdapter implements JsonDeserializer<Perfil_VO> {
	@Override
	public Perfil_VO deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		Perfil_VO perfil = new Perfil_VO();
		try {
			JsonElement elem = null;


			elem = getValor(json, "id");
			if (elem != null) {
				perfil.setId(elem.getAsLong());
			}
			elem = getValor(json, "version");
			if (elem != null) {
				perfil.setVersion(elem.getAsInt());
			}
			elem = getValor(json, "codigo");
			if (elem != null) {
				perfil.setCodigo(elem.getAsString());
			}
			elem = getValor(json, "nombre");
			if (elem != null) {
				perfil.setNombre(elem.getAsString());
			}
			elem = getValor(json, "funciones");
			if (elem != null) {
				List<FuncionHorus_VO> funciones = 
						(List<FuncionHorus_VO>) 
						new GsonBuilder()
							.registerTypeAdapter(FuncionHorus_VO.class, new FuncionHorusBDSimpleAdapter())
							.create()
							.fromJson(elem, new TypeToken<List<FuncionHorus_VO>>() {}.getType());
				
				
				perfil.setFunciones(funciones);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return perfil;
	}

	private JsonElement getValor(JsonElement json, String key) {
		JsonElement elem = json.getAsJsonObject().get(key);
		if ((elem instanceof JsonNull)) {
			elem = null;
		}
		return elem;
	}
}