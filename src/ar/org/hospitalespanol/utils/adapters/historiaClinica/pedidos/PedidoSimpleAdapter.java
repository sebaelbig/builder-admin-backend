package ar.org.hospitalespanol.utils.adapters.historiaClinica.pedidos;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ar.org.hospitalespanol.vo.core.datosSalud.TipoPrestacionHorus_VO;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.Pedido_VO;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.estudios.EstudioDePedido_VO;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class PedidoSimpleAdapter implements JsonDeserializer<Pedido_VO>{

	@Override
	public Pedido_VO deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {

		Pedido_VO pedido = new Pedido_VO();

		try {
			JsonElement elem = null;

//			tdp    tipoDniPaciente 
			elem = this.getValor(json, "tdp");
			if (elem != null) {
				pedido.setTipoDniPaciente(elem.getAsString());
			}
//				ndp  nroDniPaciente 
			elem = this.getValor(json, "ndp");
			if (elem != null) {
				pedido.setNroDniPaciente(elem.getAsString());
			}
			
//				nc  nroCarpeta 
			elem = this.getValor(json, "nc");
			if (elem != null) {
				pedido.setNroCarpeta(elem.getAsString());
			}
			
//				f  fechaPedida 
			elem = this.getValor(json, "f");
			if (elem != null) {
				pedido.setFechaPedida(elem.getAsString());
				
				pedido.setDt_fechaPedida(new SimpleDateFormat("dd/MM/yyyy").parse(elem.getAsString()));
			}
			
//				mps  matriculaProfesionalSolicitante 
			elem = this.getValor(json, "mps");
			if (elem != null) {
				pedido.setMatriculaProfesionalSolicitante(elem.getAsString());
			}
			
//				mpa  matriculaProfesionalActuante 
			elem = this.getValor(json, "mpa");
			if (elem != null) {
				pedido.setMatriculaProfesionalActuante(elem.getAsString());
			}
			
//				u  usuario 
			elem = this.getValor(json, "u");
			if (elem != null) {
				pedido.setUsuario(elem.getAsString());
			}
//				e  estado
			elem = this.getValor(json, "e");
			if (elem != null) {
				pedido.setEstado(elem.getAsString());
			}
//				m  modalidad 
			elem = this.getValor(json, "m");
			if (elem != null) {
				pedido.setModalidad(elem.getAsString());
			}
//				eq  equipo 
			elem = this.getValor(json, "eq");
			if (elem != null) {
				pedido.setEquipo(elem.getAsString());
			}
//				s  servicio 
			elem = this.getValor(json, "s");
			if (elem != null) {
				pedido.setNombreServicio(elem.getAsString());
			}
			
//			urg  urgente
			elem = this.getValor(json, "urg");
			if (elem != null) {
				pedido.setUrgente(elem.getAsString().equalsIgnoreCase("s")?true:false);
			}
			
//			ms  mailSolicitante
			elem = this.getValor(json, "ms");
			if (elem != null) {
				pedido.setMailSolicitante(elem.getAsString());
			}
			
//				ep  estudiosPaciente
			elem = this.getValor(json, "ep");
			if (elem != null) {

				Type listType = new TypeToken<List<TipoPrestacionHorus_VO>>() {
				}.getType();

				List<TipoPrestacionHorus_VO> prestaciones = 
						(List<TipoPrestacionHorus_VO>) 
						new GsonBuilder()
							.registerTypeAdapter(TipoPrestacionHorus_VO.class, new TipoPrestacionHorusHESimpleAdapter())
							.create()
							.fromJson(elem, listType);
				
				//Transformo los estudios en EstudiosDePedido
				List<EstudioDePedido_VO> estudiosPaciente = new ArrayList<>();
				EstudioDePedido_VO ep = null;
				for (TipoPrestacionHorus_VO tp : prestaciones) {
					ep = new EstudioDePedido_VO();
					
					//TODO Agregar segun la especificacion
					ep.setCodigoEstudio(tp.getCodigo());
					ep.setNombreEstudio(tp.getNombre());
					ep.setNumeroItem(tp.getNumeroItem());
					
					ep.setFecha(pedido.getFechaPedida());
					
					
					ep.setPedido(pedido);
					estudiosPaciente.add(ep);
				}
//				Type listType = new TypeToken<List<EstudioDePedido_VO>>() {
//				}.getType();
				
//				List<EstudioDePedido_VO> estudios = 
//						(List<EstudioDePedido_VO>) 
//						new GsonBuilder()
//							.registerTypeAdapter(EstudioDePedido_VO.class, new EstudioDePedidoSimpleAdapter())
//							.create()
//							.fromJson(elem, listType);
						
				pedido.setEstudiosPaciente(estudiosPaciente);

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
