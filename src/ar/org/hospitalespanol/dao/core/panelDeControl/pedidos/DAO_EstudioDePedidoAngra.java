package ar.org.hospitalespanol.dao.core.panelDeControl.pedidos;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.model.historiaClinica.pedidos.EstudioDePedidoAngra;
import ar.org.hospitalespanol.model.historiaClinica.pedidos.PedidoFiltrado;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.estudios.EstudioDePedidoAngra_VO;

@Service
public class DAO_EstudioDePedidoAngra extends DAO<EstudioDePedidoAngra_VO> {
	
	public DAO_EstudioDePedidoAngra() {
		
		this.setQueryEncabezado("SELECT new "
				+ EstudioDePedidoAngra_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		this.setQueryFinal(" ORDER BY " + this.getIdClass() + ".accesionNumber, " + this.getIdClass() + ".fecha ");

		this.setQueryCondiciones("");
		
	}

	@Override
	public Class getClazz() {
		return EstudioDePedidoAngra.class;
	}

	@Override
	public String getIdClass() {
		return "epa";
	}

	public EstudioDePedidoAngra_VO getByAccessionNumber(String an) {

		return (EstudioDePedidoAngra_VO) getEntityManager()
					.createQuery("SELECT new "+ EstudioDePedidoAngra_VO.class.getCanonicalName() + " (epa) " +
							"  FROM "+EstudioDePedidoAngra.class.getCanonicalName()+" epa "
							+" WHERE epa.accesionNumber = '"+an+"' AND epa.borrado=false ").getSingleResult();
	}
	
	public List<EstudioDePedidoAngra_VO> listarPedidosPorFiltro(PedidoFiltrado pedidoFiltrado, String usuarioAccion) {

		this.resetQuery();
        String where = "WHERE ";
        String params = "";
        
		if (pedidoFiltrado.getAccessionNumber()!=null ) {
			params = params + this.getIdClass()+".accesionNumber LIKE :an AND ";
			this.getCondiciones().put("an", "%"+pedidoFiltrado.getAccessionNumber()+"%");
		}
		
		if (params != null) {
			where = where + params;
		}
		
		if(pedidoFiltrado.getOrden() != null){
			/*PedidioDePacienteCtrl  lo usa*/
			this.setQueryFinal(" ORDER BY " + this.getIdClass() + "."+pedidoFiltrado.getOrden() + " DESC");
		}
		
		System.out.print(params);
		this.setQueryCondiciones(where + this.getIdClass()+".fecha >= :fechaDesde AND "  
	                                   + this.getIdClass()+".fecha <= :fechaHasta");				
		this.getCondiciones().put("fechaDesde", DAO_Utils.parseDateHour(pedidoFiltrado.getFechaDesde() + " 00:00:00"));
		this.getCondiciones().put("fechaHasta", DAO_Utils.parseDateHour(pedidoFiltrado.getFechaHasta() + " 23:59:59"));
		
		List<EstudioDePedidoAngra_VO> pedidos = this.listarTodo();
		
		this.resetQuery();
		return pedidos;
	}

	
	
}
