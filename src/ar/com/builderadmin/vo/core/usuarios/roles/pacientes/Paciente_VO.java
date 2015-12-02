package ar.com.builderadmin.vo.core.usuarios.roles.pacientes;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import ar.com.builderadmin.model.core.obrasSociales.roles.ProductoObraSocialPaciente;
import ar.com.builderadmin.model.core.usuarios.Usuario;
import ar.com.builderadmin.model.core.usuarios.roles.pacientes.Paciente;
import ar.com.builderadmin.vo.core.obrasSociales.ProductoObraSocialPaciente_VO;
import ar.com.builderadmin.vo.core.usuarios.Usuario_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.Rol_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.TipoDeRol_VO;
import ar.com.builderadmin.vo.historiaClinica.HistoriaClinica_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:12 a.m.
 */
public class Paciente_VO extends Rol_VO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ProductoObraSocialPaciente_VO> productosObrasSocialPaciente;

	private HistoriaClinica_VO historiaClinica;

	public Paciente_VO(Usuario_VO usr) {
		this.setUsuario(usr);
	}

	public Paciente_VO() {
		super();
	}

	public Paciente_VO(Paciente pac) {
		this.setObject(pac);
	}

	public Paciente_VO(Paciente pac, int profundidadActual,
			int profundidadDeseada) {
		this.setObject(pac, profundidadActual, profundidadDeseada);
	}

	public Paciente_VO(Paciente pac, Usuario usr) {
		this.setObject(pac);
	}

	public Paciente_VO(TipoDeRol_VO tr, Usuario_VO usr) {
		// Agrego la informacion del usuario
		this(usr);

		// Agrego la informacion del tipo de rol
		this.setTipoRol(tr);

		/******* Historia Clinica *****/
		// Instancio una nueva Historia Clinica
		this.setHistoriaClinica(new HistoriaClinica_VO());
		this.getHistoriaClinica()
				.setNumero(this.getUsuario().getNroDocumento());
		this.getHistoriaClinica().setFechaCreacion(new Date());
	}

	public List<ProductoObraSocialPaciente_VO> getProductosObrasSocialPaciente() {
		return productosObrasSocialPaciente;
	}

	public void setProductosObrasSocialPaciente(
			List<ProductoObraSocialPaciente_VO> productoObrasSocialPaciente) {
		this.productosObrasSocialPaciente = productoObrasSocialPaciente;
	}

	public HistoriaClinica_VO getHistoriaClinica() {
		return historiaClinica;
	}

	public void setHistoriaClinica(HistoriaClinica_VO historiaClinica) {
		this.historiaClinica = historiaClinica;
	}

	@Override
	public Paciente toObject(int profundidadActual, int profundidadDeseada) {

		Paciente resul = new Paciente();

		resul.setId(this.getId());
		resul.setVersion(this.getVersion());
		resul.setBorrado(getBorrado());
		
		resul.setNombre(this.getNombre());
		resul.setCodigo(this.getCodigo());

		if (profundidadActual < profundidadDeseada) {
			if (this.getHistoriaClinica() != null) {
				resul.setHistoriaClinica(this.getHistoriaClinica().toObject(
						profundidadActual + 1, profundidadDeseada));
			}

			if (this.getUsuario() != null) {
				resul.setUsuario(this.getUsuario().toObject(
						profundidadActual + 1, profundidadDeseada));
			}

			if (this.getTipoRol() != null) {
				resul.setTipoRol(this.getTipoRol().toObject(
						profundidadActual + 1, profundidadDeseada));
			}
			
			if (this.getTipoId() != null) {
				resul.setTipoID(this.getTipoId().toObject());
				resul.setValorTipoID(getValorTipoID());
			}

			for (Perfil_VO p_vo : this.getPerfiles()) {
				resul.getPerfiles()
						.add(p_vo.toObject(profundidadActual + 1,
								profundidadDeseada));
			}

			if (this.getProductosObrasSocialPaciente() != null) 
				for (ProductoObraSocialPaciente_VO prod_vo : this
						.getProductosObrasSocialPaciente()) {
					resul.getProductos_OSPaciente().add(prod_vo.toObject());
				}
			
		} else {
			if (this.getUsuario() != null) {
				resul.setUsuario(this.getUsuario().toObject(PROFUNDIDAD_BASE,
						PROFUNDIDAD_BASE));
			}
		}

		return resul;

	}

	@Override
	public Paciente toObject() {

		Paciente resul = new Paciente();

		resul.setId(this.getId());
		resul.setVersion(this.getVersion());
		resul.setBorrado(getBorrado());
		
		resul.setNombre(this.getNombre());
		resul.setCodigo(this.getCodigo());

		if (this.getHistoriaClinica() != null) {
			resul.setHistoriaClinica(this.getHistoriaClinica().toObject());
		}

		if (this.getUsuario() != null) {
			resul.setUsuario(this.getUsuario().toObject());
		}

		if (this.getTipoRol() != null) {
			resul.setTipoRol(this.getTipoRol().toObject());
		}

		if (this.getTipoId() != null) {
			resul.setTipoID(this.getTipoId().toObject());
			resul.setValorTipoID(getValorTipoID());
		}
		
		for (Perfil_VO p_vo : this.getPerfiles()) {
			resul.getPerfiles().add(p_vo.toObject());
		}

		if (this.getProductosObrasSocialPaciente() != null) 
			for (ProductoObraSocialPaciente_VO prod_vo : this
					.getProductosObrasSocialPaciente()) {
				resul.getProductos_OSPaciente().add(prod_vo.toObject());
			}

		return resul;

	}

	public void setObject(Paciente p) {

		super.setObject(p);

		if (p.getHistoriaClinica() != null) {
			this.setHistoriaClinica(p.getHistoriaClinica().toValueObject());
		}

		if (p.getProductos_OSPaciente() != null) 
			for (ProductoObraSocialPaciente prod : p.getProductos_OSPaciente()) {
				this.getProductosObrasSocialPaciente().add(prod.toValueObject());
			}

	}

	public void setObject(Paciente p, int profundidadActual,
			int profundidadDeseada) {

		super.setObject(p, profundidadActual, profundidadDeseada);

		if (profundidadActual < profundidadDeseada) {

			if (p.getHistoriaClinica() != null) {

				this.setHistoriaClinica(p.getHistoriaClinica().toValueObject(
						profundidadActual + 1, profundidadDeseada));

			}

			if (p.getProductos_OSPaciente() != null) 
				for (ProductoObraSocialPaciente prod : p.getProductos_OSPaciente()) {
	
					this.getProductosObrasSocialPaciente().add(
							prod.toValueObject(profundidadActual + 1,
									profundidadDeseada));
	
				}

		}

	}

}