package ar.org.hospitalespanol.utils.adapters.turnos;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

import ar.org.hospitalespanol.vo.turnos.estados.TurnoReservado_VO;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;

public class TurnoReservadoSimpleAdapter implements
		JsonDeserializer<TurnoReservado_VO> {

	@Override
	public TurnoReservado_VO deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {

		TurnoReservado_VO turno = new TurnoReservado_VO();
		
		SimpleDateFormat horaF = new SimpleDateFormat("HH:mm");
		SimpleDateFormat diaF = new SimpleDateFormat("dd/MM/yyy");
		
		try {
			JsonElement elem = null;

			// private Long id;
			elem = this.getValor(json, "id");
			if (elem != null) {
				turno.setId(elem.getAsLong());
			}

			// private Date fecha;
			elem = this.getValor(json, "fecha");
			if (elem != null) {
				turno.setStr_fecha(elem.getAsString());
				turno.setFecha(diaF.parse(elem.getAsString()));
			}

			// private Date hora;
			elem = this.getValor(json, "hora");
			if (elem != null) {
				turno.setStr_hora(elem.getAsString());
				turno.setHora(horaF.parse(elem.getAsString()));
			}
			
			// private Integer numero;;
			elem = this.getValor(json, "numero");
			if (elem != null) {
				turno.setNumero(elem.getAsInt());
			}
			
			// private Boolean libre
			elem = this.getValor(json, "libre");
			if (elem != null) {
				turno.setLibre(elem.getAsBoolean());
				
				if (!turno.getLibre()){
					
					// private String paciente;;
					elem = this.getValor(json, "paciente");
					if (elem != null) {
						turno.setStr_paciente(elem.getAsString().toLowerCase());
					}
					
					// private String tipo Documento;;
					elem = this.getValor(json, "tipoDoc");
					if (elem != null) {
						turno.setTipoDoc(elem.getAsString().toLowerCase());
					}
					
					// private String numero Documento;;
					elem = this.getValor(json, "numDoc");
					if (elem != null) {
						turno.setNumeroDoc(elem.getAsString().toLowerCase());
					}
					
					// private String telefono;;
					elem = this.getValor(json, "telefono");
					if (elem != null) {
						turno.setTelefono(elem.getAsString());
					}
					
					
				}
				
			}

			// private String nombreDia;
//			elem = this.getValor(json, "nombreDia");
//			if (elem != null) {
//				turno.setNombreDia(elem.getAsString());
//			}

			// private String estado;
//			elem = this.getValor(json, "estado");
//			if (elem != null) {
//				turno.setEstado(elem.getAsString());
//			}

			// private String apellido;
//			elem = this.getValor(json, "apellido");
//			if (elem != null) {
//				turno.setApellido(elem.getAsString());
//			}

			// private String nombres;
//			elem = this.getValor(json, "nombres");
//			if (elem != null) {
//				turno.setNombres(elem.getAsString());
//			}

			// private String especialidad;
//			elem = this.getValor(json, "especialidad");
//			if (elem != null) {
//				turno.setEspecialidad(elem.getAsString());
//			}

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

}