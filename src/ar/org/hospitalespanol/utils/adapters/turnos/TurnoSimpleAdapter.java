package ar.org.hospitalespanol.utils.adapters.turnos;

import java.lang.reflect.Type;

import ar.org.hospitalespanol.vo.turnos.BloqueTurno_VO;
import ar.org.hospitalespanol.vo.turnos.Fecha_VO;
import ar.org.hospitalespanol.vo.turnos.Turno_VO;
import ar.org.hospitalespanol.vo.turnos.estados.EstadoTurno_VO;
import ar.org.hospitalespanol.vo.turnos.estados.TurnoPresente_VO;
import ar.org.hospitalespanol.vo.turnos.estados.TurnoReservado_VO;
import ar.org.hospitalespanol.vo.turnos.estados.TurnoSinReservar_VO;
import ar.org.hospitalespanol.vo.turnos.estados.TurnoTomado_VO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class TurnoSimpleAdapter implements JsonDeserializer<Turno_VO>,
		JsonSerializer<Turno_VO> {

	@Override
	public Turno_VO deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {

		Turno_VO turno = new Turno_VO();
		Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();

		try {
			JsonElement elem = null;

			// private Long id;
			elem = this.getValor(json, "id");
			if (elem != null) {
				turno.setId(elem.getAsLong());
			}

			// private Integer version;
			elem = this.getValor(json, "version");
			if (elem != null) {
				turno.setVersion(elem.getAsInt());
			}

			// private Integer duracion;
			elem = this.getValor(json, "duracion");
			if (elem != null) {
				turno.setDuracion(elem.getAsInt());
			}

			// private Integer numero;
			elem = this.getValor(json, "numero");
			if (elem != null) {
				turno.setNumero(elem.getAsInt());
			}

			// private Boolean sobreTurno;
			elem = this.getValor(json, "sobreTurno");
			if (elem != null) {
				turno.setSobreTurno(elem.getAsBoolean());
			}

			// private Fecha_VO fecha
			elem = this.getValor(json, "fecha");
			if (elem != null) {
				turno.setFecha(g.fromJson(elem, Fecha_VO.class));
			}

			// private BloqueTurno_VO bloqueTurno;
			elem = this.getValor(json, "bloqueTurno");
			if (elem != null) {
				turno.setBloqueTurno(g.fromJson(elem, BloqueTurno_VO.class));
			}

			// private EstadoTurno_VO estado
			elem = this.getValor(json, "estado");
			if (elem != null) {

				// Intento recuperar el estado exacto
				EstadoTurno_VO temp = g.fromJson(elem, EstadoTurno_VO.class);

				if (temp.getNombre().equals(EstadoTurno_VO.TOMADO)) {

					turno.setEstado(g.fromJson(elem, TurnoTomado_VO.class));

				} else if (temp.getNombre().equals(EstadoTurno_VO.PRESENTE)) {

					turno.setEstado(g.fromJson(elem, TurnoPresente_VO.class));

				} else if (temp.getNombre().equals(EstadoTurno_VO.RESERVADO)) {

					turno.setEstado(g.fromJson(elem, TurnoReservado_VO.class));

				} else {
					turno.setEstado(g.fromJson(elem, TurnoSinReservar_VO.class));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return turno;
	}

	private JsonElement getValor(JsonElement json, String key) {
		JsonElement elem = json.getAsJsonObject().get(key);

		if (elem instanceof JsonNull) {
			elem = null;
		}

		return elem;
	}

	@Override
	public JsonElement serialize(Turno_VO turno, Type arg1,
			JsonSerializationContext arg2) {
		return new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm").create().toJsonTree(turno);
	/*
		// JsonNull.INSTANCE
		DateFormat dia = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat hora = new SimpleDateFormat("HH:mm");

		// Calculo la hora en base a: horaDesde + numeroTurno * frecuencia
		JsonElementWriter writer = new JsonElementWriter();

		Gson g = new GsonBuilder()
				.setDateFormat("dd/MM/yyyy")
				.registerTypeAdapter(BloqueTurno_VO.class,
						new BloqueTurnoSimpleAdapter()).create();

		try {
			writer.beginObject();

			// private Long id;
			writer.name("id").value(turno.getId());
			// private Integer version;
			writer.name("version").value(turno.getVersion());
			// private Integer duracion;
			writer.name("duracion").value(turno.getDuracion());
			// private Integer numero;
			writer.name("numero").value(turno.getNumero());
			// private Boolean sobreTurno;
			writer.name("sobreTurno").value(turno.getSobreTurno());
			// private Fecha_VO fecha
			writer.name("fecha").value(g.toJson(turno.getFecha()));
			
			// private BloqueTurno_VO bloqueTurno;
			writer.name("bloqueTurno");
			BloqueTurnoSimpleAdapter.serializarEn(turno.getBloqueTurno(), writer, arg1, arg2);
			
			// Intento recuperar el estado exacto
			// EstadoTurno_VO temp = g.fromJson(elem, EstadoTurno_VO.class);
			/*
			writer.name("estado");
			writer.beginObject();

			EstadoTurno_VO temp = turno.getEstado();
			if (temp.getNombre().equals(EstadoTurno_VO.TOMADO)) {
				TurnoTomado_VO tt = (TurnoTomado_VO) temp;

				// * Datos del PRESENTE
				// private Date horaPresentoPaciente;
				writer.name("horaPresentoPaciente").value(
						hora.format(tt.getHoraPresentoPaciente()));
				// private Rol_VO personalPresentoTurno;
				writer.name("personalPresentoTurno");
				writer.beginObject();
				writer.name("nombre").value(tt.getperso);
				writer.endObject();
				// private String nombrePersonalRegistroPresencia;
				//
				// * Datos del TOMADO
				// private Date horaTomoPaciente;
				// private Rol_VO personalTomoTurno;
				// private String nombrePersonalRegistroToma;
				//
				// private String tiempoDeEspera;
			} else if (temp.getNombre().equals(EstadoTurno_VO.PRESENTE)) {

				turno.setEstado(g.fromJson(elem, TurnoPresente_VO.class));

			} else if (temp.getNombre().equals(EstadoTurno_VO.RESERVADO)) {

				turno.setEstado(g.fromJson(elem, TurnoReservado_VO.class));

			} else {
				turno.setEstado(g.fromJson(elem, TurnoSinReservar_VO.class));
			}

			writer.name("nombre").value(turno.getEstado().getNombre());
			writer.endObject();
			
			writer.endObject();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return writer.get();
	 */
	}
		
}
