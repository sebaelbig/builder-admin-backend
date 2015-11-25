package ar.org.hospitalespanol.utils.adapters.historiaClinica.episodios;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.List;

import ar.org.hospitalespanol.utils.adapters.core.usuarios.roles.PacienteSimpleAdapter;
import ar.org.hospitalespanol.utils.adapters.core.usuarios.roles.ProfesionalHESimpleAdapter;
import ar.org.hospitalespanol.vo.core.datosSalud.Prestacion_VO;
import ar.org.hospitalespanol.vo.core.obrasSociales.ProductoObraSocialPaciente_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.pacientes.Paciente_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.profesionales.ProfesionalHE_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.profesionales.Profesional_VO;
import ar.org.hospitalespanol.vo.historiaClinica.episodios.Episodio_VO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

public class EpisodioSimpleAdapter implements JsonDeserializer<Episodio_VO>,
		JsonSerializer<Episodio_VO> {

	@Override
	public Episodio_VO deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {

		Episodio_VO epi = new Episodio_VO();

		try {
			JsonElement elem = null;

			/*
			 * Propio del Paciente VO "{ 'id':null, 'version':0,
			 * 'fechaRealizado': fechaElegida 'nroEpisodio': 0
			 * 'producto_OSPaciente': {}", 'prestacion':{}",
			 * 'profesionalSolicitante':{}, 'informeInstitucional':'' }
			 */

			// private Long id;
			elem = this.getValor(json, "id");
			if (elem != null) {
				epi.setId(elem.getAsLong());
			}

			// private Integer version;
			elem = this.getValor(json, "version");
			if (elem != null) {
				epi.setVersion(elem.getAsInt());
			}

			// private Integer version;
			elem = this.getValor(json, "fechaRealizado");
			if (elem != null) {
				epi.setFechaRealizado(new SimpleDateFormat("dd/MM/yyyy")
						.parse(elem.getAsString()));
			}

			// private Long nroEpisodio;
			elem = this.getValor(json, "nroEpisodio");
			if (elem != null) {
				epi.setNroEpisodio(elem.getAsString());
			}

			// Paciente_VO paciente;
			elem = this.getValor(json, "paciente");
			if (elem != null) {

				// deserializo el usuario
				epi.setPaciente(new GsonBuilder()
						.registerTypeAdapter(Paciente_VO.class,
								new PacienteSimpleAdapter()).create()
						.fromJson(elem, Paciente_VO.class));
			}

			// ProductoObraSocialPaciente_VO producto_OSPaciente;
			elem = this.getValor(json, "producto_OSPaciente");
			if (elem != null) {

				// deserializo el usuario
				epi.setProducto_OSPaciente(new Gson().fromJson(elem,
						ProductoObraSocialPaciente_VO.class));
			}

			// List<Prestacion_VO> prestaciones
			elem = this.getValor(json, "prestaciones");
			if (elem != null) {

				Type listType = new TypeToken<List<Prestacion_VO>>() {
				}.getType();

				List<Prestacion_VO> list = (List<Prestacion_VO>) new Gson()
						.fromJson(elem, listType);
				epi.setPrestaciones(list);

			}

			// Rol_VO profesionalSolicitante;
			elem = this.getValor(json, "profesionalSolicitante");
			if (elem != null) {

				// deserializo el usuario
				epi.setProfesionalSolicitante(new GsonBuilder()
						.registerTypeAdapter(ProfesionalHE_VO.class,
								new ProfesionalHESimpleAdapter()).create()
						.fromJson(elem, Profesional_VO.class));
			}

			// String informeInstitucional;
			elem = this.getValor(json, "informeInstitucional");
			if (elem != null) {
				epi.setInformeInstitucional(elem.getAsString());
			}

			// Long id_episodioOrigen;
			elem = this.getValor(json, "id_EpisodioOrigen");
			if (elem != null) {
				epi.setId_episodioOrigen(elem.getAsLong());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return epi;
	}

	private JsonElement getValor(JsonElement json, String key) {
		JsonElement elem = json.getAsJsonObject().get(key);

		if (elem instanceof JsonNull) {
			elem = null;
		}

		return elem;
	}

	// Cuando serializo el usuario le quito el tipo de rol y todas las funciones
	// asignadas
	@Override
	public JsonElement serialize(Episodio_VO epi, Type tipo,
			JsonSerializationContext contexto) {

		for (Prestacion_VO p : epi.getPrestaciones()) {
			p.setTipoPrestacion(null);
			p.setNomenclador(null);
		}

		GsonBuilder gsbldr = new GsonBuilder().registerTypeAdapter(
				Paciente_VO.class, new PacienteSimpleAdapter());

		gsbldr.registerTypeAdapter(ProfesionalHE_VO.class,
				new ProfesionalHESimpleAdapter());

		epi.getPaciente().getHistoriaClinica().setEpisodios(null);

		return gsbldr.create().toJsonTree(epi);

	}

}
