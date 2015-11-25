package ar.org.hospitalespanol.dao.historiaClinica.pedidos.estudios;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.model.core.datosSalud.TipoPrestacionHorus;
import ar.org.hospitalespanol.model.historiaClinica.pedidos.EstudioDePedidoAngra;
import ar.org.hospitalespanol.model.historiaClinica.pedidos.Pedido;
import ar.org.hospitalespanol.model.historiaClinica.pedidos.estudios.EstudioDePedido;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.Pedido_VO;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.estudios.EstudioDePedidoAngra_VO;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.estudios.EstudioDePedido_VO;
import ar.org.hospitalespanol.ws.RespuestaCorta;

import com.google.gson.Gson;

@Service
public class DAO_EstudioDePedido extends DAO<EstudioDePedido_VO> {
	
	public DAO_EstudioDePedido() {
		
		this.setQueryEncabezado("SELECT new "
				+ EstudioDePedido_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		this.setQueryFinal(" ORDER BY " + this.getIdClass() + ".pedido.numero, " + this.getIdClass() + ".estudio.nombre ");

		this.setQueryCondiciones("");
		
	}

	@Override
	public Class getClazz() {
		return EstudioDePedido.class;
	}

	@Override
	public String getIdClass() {
		return "estudioDePedido";
	}
	
	
	@Override
	protected Object getObjeto(I_ValueObject valueObject) {
		Object o = null;
		
		//Si estoy persistiendo un perfil en particular, el usuario no se convertira cuando le haga toObject
		if (valueObject instanceof EstudioDePedido_VO) {

			EstudioDePedido_VO ep_vo = (EstudioDePedido_VO) valueObject;
			EstudioDePedido ep = ep_vo.toObject();
			
			TipoPrestacionHorus est =  this.getEntityManager().find(TipoPrestacionHorus.class, ep_vo.getIdEstudio());
			ep.setEstudio(est);
			
			Pedido ped = this.getEntityManager	().find(Pedido.class, ep_vo.getPedido().getId());
			ep.setPedido(ped);
			
			o= ep;
			
		}else{
			o = valueObject.toObject();
		}
		
		return o;
	}

	public RespuestaCorta anularEstudio(EstudioDePedido_VO estudio,  String usuario) {
		
		RespuestaCorta resul = null;
		Connection con = getConexionHE(BD_USER_HORUS_PEDIDOS, BD_PASS_HORUS_PEDIDOS );
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_set_anularestudio(?,?,?,?,?,?)}");
				
				pstmt.setInt(1, Integer.parseInt(estudio.getPedido().getNumero()));
				pstmt.setInt(2, estudio.getNumeroItem());
				pstmt.setString(3, estudio.getMotivo());
				pstmt.setString(4, usuario);
				String fecha = DAO_Utils.getStrDate();
				pstmt.setString(5, fecha);
				
				pstmt.registerOutParameter(6, -1);
				pstmt.setString(6, "");
				
				DAO_Utils.info(log, "DAO_EstudioDePedido", "anularEstudio",getUsuarioAccion(), "Llamando: 1.3.3	sp_horus_set_anularestudio(" + estudio.getPedido().getNumero()
						+", "+estudio.getCodigoEstudio()+ ", "+estudio.getMotivo()+ ", "+usuario+", "+fecha+")");
				
				pstmt.execute();
				String sp_horus_set_anularestudio = pstmt.getString(6);
				
				DAO_Utils.info(log, "DAO_EstudioDePedido", "anularEstudio", getUsuarioAccion(),"Se llama a sp_horus_set_anularestudio y se obtiene: "+sp_horus_set_anularestudio);
				
				resul = new Gson().fromJson(sp_horus_set_anularestudio, RespuestaCorta.class);
			}
			
		} catch (NoResultException e) {
			e.printStackTrace();
			cerrarConexion(con);
		} catch (Exception e1) {
			e1.printStackTrace();
			cerrarConexion(con);
		} finally {
			cerrarConexion(con);
		}
		
		return resul;
		
	}
	public RespuestaCorta informarEstudio(EstudioDePedido_VO estudio, String usuario) {
		
		RespuestaCorta resul = null;
		Connection con = getConexionHE(BD_USER_HORUS_PEDIDOS, BD_PASS_HORUS_PEDIDOS );
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_set_informarestudio(?,?,?,?,?)}");

				pstmt.setInt(1, Integer.parseInt(estudio.getPedido().getNumero()));
				pstmt.setInt(2, estudio.getNumeroItem());
				pstmt.setString(3, usuario);
				String fecha = DAO_Utils.getStrDate();
				pstmt.setString(4, fecha);

				pstmt.registerOutParameter(5, -1);
				pstmt.setString(5, "");

				DAO_Utils.info(log, "DAO_EstudioDePedido", "informarEstudio", getUsuarioAccion(),"Llamando: 1.3.3	sp_horus_set_informarestudio(" + estudio.getPedido().getNumero()
								+", "+estudio.getCodigoEstudio()+ ", "+usuario+", "+fecha+")");
				
				pstmt.execute();
				String sp_horus_set_informarestudio = pstmt.getString(5);

				DAO_Utils.info(log, "DAO_EstudioDePedido", "informarEstudio", getUsuarioAccion(),"Se llama a sp_horus_set_informarestudio y se obtiene: "+sp_horus_set_informarestudio);

				resul = new Gson().fromJson(sp_horus_set_informarestudio, RespuestaCorta.class);
			}

		} catch (NoResultException e) {
			e.printStackTrace();
			cerrarConexion(con);
		} catch (Exception e1) {
			e1.printStackTrace();
			cerrarConexion(con);
		} finally {
			cerrarConexion(con);
		}

		return resul;
		
	}

	public boolean existePedido(Long id) {
		
		EstudioDePedido ped = (EstudioDePedido) this.findById(id);
		
		return ped!=null && !ped.getBorrado();
	}

	public List<EstudioDePedido_VO> getEstudiosDePedido(Pedido_VO pedido,
			String usuario) {
		
		this.resetQuery();
		
		this.setQueryCondiciones(
				" WHERE " + this.getIdClass()
						+ ".pedido.id = :idPedido ");

		this.getCondiciones().put("idPedido",
				pedido.getId());
		
		return this.listarTodo();
	}

	public EstudioDePedido_VO getEstudioPorAccessionNumber(String an) {
		
		//Obtengo el Esutio pedido angra
		this.setQueryEncabezado("SELECT new "
				+ EstudioDePedido_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		EstudioDePedidoAngra epa = (EstudioDePedidoAngra) getEntityManager().createQuery("FROM "+EstudioDePedidoAngra.class.getCanonicalName()
					+" WHERE accesionNumber =  "+an).getSingleResult();
		
		//Obtengo el estudio segun el AN
		return this.getEntityManager().find(EstudioDePedido.class, epa.getIdEstudio()).toValueObject();
	}

	public EstudioDePedidoAngra_VO buscarPorAccessionNumber(String an) {
		// TODO Auto-generated method stub
		return null;
	}

}
