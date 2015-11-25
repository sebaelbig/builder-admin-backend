package ar.org.hospitalespanol.utils.adapters.historiaClinica.pedidos;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.List;

import ar.org.hospitalespanol.model.core.DatosAccion;
import ar.org.hospitalespanol.utils.adapters.historiaClinica.pedidos.estudios.EstudioDePedidoBDSimpleAdapter;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.Pedido_VO;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.estudios.EstudioDePedido_VO;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class PedidoBDSimpleAdapter implements JsonDeserializer<Pedido_VO>{

	@SuppressWarnings("unchecked")
	@Override
	public Pedido_VO deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {

		Pedido_VO pedido = new Pedido_VO();

		try {
			JsonElement elem = null;

			elem = this.getValor(json, "p_id");
			if (elem != null) {
				pedido.setId(elem.getAsLong());
			}
			
			elem = this.getValor(json, "tipo_dni_paciente");
			if (elem != null) {
				pedido.setTipoDniPaciente(elem.getAsString());
			}
//				ndp  nroDniPaciente 
			elem = this.getValor(json, "nro_dni_paciente");
			if (elem != null) {
				pedido.setNroDniPaciente(elem.getAsString());
			}
			
//				nc  nroCarpeta 	
			elem = this.getValor(json, "nro_carpeta");
			if (elem != null) {
				pedido.setNroCarpeta(elem.getAsString());
			}
			
			elem = this.getValor(json, "numero");
			if (elem != null) {
				pedido.setNumero(elem.getAsString());
			}
			
//				f  fechaPedida 
			elem = this.getValor(json, "fecha_pedida");
			if (elem != null) {
				pedido.setFechaPedida(elem.getAsString());
				
				pedido.setDt_fechaPedida(new SimpleDateFormat("dd/MM/yyyy").parse(elem.getAsString()));
			}
			
			elem = this.getValor(json, "fecha");
			if (elem != null) {
				pedido.setFecha(elem.getAsString());
				
				pedido.setDt_fecha(new SimpleDateFormat("dd/MM/yyyy").parse(elem.getAsString()));
			}
			
//				mps  matriculaProfesionalSolicitante 
			elem = this.getValor(json, "matricula_profesional_solicitante");
			if (elem != null) {
				pedido.setMatriculaProfesionalSolicitante(elem.getAsString());
			}
			
			elem = this.getValor(json, "solicitante");
			if (elem != null) {
				pedido.setSolicitante(elem.getAsString());
			}
			elem = this.getValor(json, "actuante");
			if (elem != null) {
				pedido.setActuante(elem.getAsString());
			}
			elem = this.getValor(json, "paciente");
			if (elem != null) {
				pedido.setPaciente(elem.getAsString());
			}
			
//				mpa  matriculaProfesionalActuante 
			elem = this.getValor(json, "matricula_profesional_actuante");
			if (elem != null) {
				pedido.setMatriculaProfesionalActuante(elem.getAsString());
			}
			
//				u  usuario 
			elem = this.getValor(json, "usuario");
			if (elem != null) {
				pedido.setUsuario(elem.getAsString());
			}
//				e  estudios
			elem = this.getValor(json, "estudios");
			if (elem != null) {
				pedido.setEstudios(elem.getAsString());
			}
			
//				e  estado
			elem = this.getValor(json, "estado");
			if (elem != null) {
				pedido.setEstado(elem.getAsString());
			}
//				m  modalidad 
			elem = this.getValor(json, "modalidad");
			if (elem != null) {
				pedido.setModalidad(elem.getAsString());
			}
//				eq  equipo 
			elem = this.getValor(json, "equipo");
			if (elem != null) {
				pedido.setEquipo(elem.getAsString());
			}

//			urg  urgente
			elem = this.getValor(json, "urgente");
			if (elem != null) {
				pedido.setUrgente(elem.getAsBoolean());
			}
			
//			ms  mailSolicitante
			elem = this.getValor(json, "mail_solicitante");
			if (elem != null) {
				pedido.setMailSolicitante(elem.getAsString());
			}
			
			/*Servicio */
			elem = this.getValor(json, "id_servicio");
			if (elem != null) {
				pedido.setIdServicio(elem.getAsLong());
			}
			elem = this.getValor(json, "codigo_servicio");
			if (elem != null) {
				pedido.setCodigoServicio(elem.getAsString());
			}
			elem = this.getValor(json, "nombre_servicio");
			if (elem != null) {
				pedido.setNombreServicio(elem.getAsString());
			}
			elem = this.getValor(json, "un_est_x_srv");
			if (elem != null) {
				pedido.setUnEstudioPorPedido(elem.getAsBoolean());
			}
			
			elem = this.getValor(json, "bloqueado");
			if (elem != null) {
				pedido.setBloqueado(elem.getAsBoolean());
				
				if (pedido.getBloqueado()){
					String time = this.getValor(json, "da_timestamp").getAsString();
					String usr = this.getValor(json, "da_user").getAsString();
					String det = this.getValor(json, "da_detalle").getAsString();
					pedido.setDatosAccion(new DatosAccion(time,usr, det));
				}
			}
			
			elem = this.getValor(json, "bloqueado");
			if (elem != null) {
				pedido.setBloqueado(elem.getAsBoolean());
			}
			
		
//				ep  estudiosPaciente
			elem = this.getValor(json, "estudios_paciente");
			if (elem != null) {

				Type listType = new TypeToken<List<EstudioDePedido_VO>>() {
				}.getType();

				List<EstudioDePedido_VO> estudios = 
						(List<EstudioDePedido_VO>) 
						new GsonBuilder()
							.registerTypeAdapter(EstudioDePedido_VO.class, new EstudioDePedidoBDSimpleAdapter())
							.create()
							.fromJson(elem, listType);

				pedido.setEstudiosPaciente(estudios);

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pedido;
	}

	private JsonElement getValor(JsonElement json, String key) {
		JsonElement elem = json.getAsJsonObject().get(key);

		if (elem instanceof JsonNull) {
			elem = null;
		}

		return elem;
	}


}
