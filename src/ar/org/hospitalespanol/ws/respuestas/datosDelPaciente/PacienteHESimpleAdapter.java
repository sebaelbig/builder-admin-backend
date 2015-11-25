package ar.org.hospitalespanol.ws.respuestas.datosDelPaciente;

import java.lang.reflect.Type;

import ar.org.hospitalespanol.vo.core.usuarios.UsuarioHESimpleAdapter;
import ar.org.hospitalespanol.vo.core.usuarios.Usuario_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.pacientes.Paciente_VO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;

public class PacienteHESimpleAdapter implements JsonDeserializer<Paciente_VO> {
	@Override
	public Paciente_VO deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		Paciente_VO pac = new Paciente_VO();
		Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		try {

			Usuario_VO usr = new GsonBuilder()
					.registerTypeAdapter(Usuario_VO.class,
							new UsuarioHESimpleAdapter()).create()
					.fromJson(json, Usuario_VO.class);

			pac.setUsuario(usr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pac;
	}

	private JsonElement getValor(JsonElement json, String key) {
		JsonElement elem = json.getAsJsonObject().get(key);
		if ((elem instanceof JsonNull)) {
			elem = null;
		}
		return elem;
	}

	// class ObraSocialPaciente
	// {
	// private String nroAfiliado;
	// private String nombre;
	//
	// public String getNroAfiliado()
	// {
	// return this.nroAfiliado;
	// }
	//
	// public void setNroAfiliado(String nroAfiliado)
	// {
	// this.nroAfiliado = nroAfiliado;
	// }
	//
	// public String getNombre()
	// {
	// return this.nombre;
	// }
	//
	// public void setNombre(String nombre)
	// {
	// this.nombre = nombre;
	// }
	// }

}