package ar.com.builderadmin.model.core.usuarios.roles.profesionales;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;

import ar.com.builderadmin.model.core.obrasSociales.ObraSocial;
import ar.com.builderadmin.model.core.usuarios.Usuario;
import ar.com.builderadmin.model.core.usuarios.roles.Rol;
import ar.com.builderadmin.vo.core.usuarios.roles.profesionales.Profesional_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:12 a.m.
 */
//@Entity
@DiscriminatorValue("rol_profesional")
public class Profesional extends Rol implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(length = 10, name = "nro_matricula_nacional")
	private String nroMatriculaNacional;

	@Column(length = 10, name = "nro_matricula_provincial")
	private String nroMatriculaProvincial;

	/**
	 * Contratos del profesional
	 */
//	@OneToMany(mappedBy = "profesional")
//	@JoinFetch(JoinFetchType.OUTER)
//	private List<ContratoProfesional> contratos = new ArrayList<ContratoProfesional>();

	/**
	 * Productos particulares del profesional
	 */
//	@OneToMany(mappedBy = "profesional")
//	private List<ProductoObraSocialProfesional> obrasSocialesLimitadas = new ArrayList<ProductoObraSocialProfesional>();

	// Constructores
	public Profesional(Long id, Integer version, Boolean borrado) {
		super(id, version, borrado);
		init();
	}

	public Profesional() {
		super(new Usuario());
		init();

	}

	private void init() {
		setNombre(Rol.PROFESIONAL);
		setCodigo(Rol.PROFESIONAL);
	}

	public Profesional(Usuario usuarioActual) {
		super(usuarioActual);
		init();
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Profesional) {
			Profesional o = (Profesional) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public String getNroMatriculaNacional() {
		return nroMatriculaNacional;
	}

	public void setNroMatriculaNacional(String nroMatriculaNacional) {
		this.nroMatriculaNacional = nroMatriculaNacional;
	}

	public String getNroMatriculaProvincial() {
		return nroMatriculaProvincial;
	}

	public void setNroMatriculaProvincial(String nroMatriculaProvincial) {
		this.nroMatriculaProvincial = nroMatriculaProvincial;
	}

//	public List<ContratoProfesional> getContratos() {
//		return contratos;
//	}
//
//	public void setContratos(List<ContratoProfesional> contratos) {
//		this.contratos = contratos;
//	}
//
//	public void agregarContrato(ContratoProfesional contrato) {
//		contrato.setProfesional(this);
//		getContratos().add(contrato);
//	}
//
//	public ContratoProfesional getUltimoContrato() {
//		if (getContratos().size() > 0) {
//			return getContratos().get(getContratos().size() - 1);
//		}
//		return null;
//	}

	public boolean obraSocialYaLimitada(ObraSocial obraSocialNueva) {
		boolean esta = false;
//		for (ProductoObraSocialProfesional obraLim : getObrasSocialesLimitadas()) {
//			if (obraLim.getNombreObraSocial().equals(
//					obraSocialNueva.getNombre())) {
//				esta = true;
//			}
//		}
		return esta;
	}

	// Por ahora solo chequeo las fechas
	public boolean contratoValido(ContratoProfesional contrato) {
		return contrato.valido();
	}
//
//	public List<ProductoObraSocialProfesional> getObrasSocialesLimitadas() {
//		return obrasSocialesLimitadas;
//	}
//
//	public void setObrasSocialesLimitadas(
//			List<ProductoObraSocialProfesional> obrasSocialesLimitadas) {
//		this.obrasSocialesLimitadas = obrasSocialesLimitadas;
//	}

	@Override
	public Profesional_VO toValueObject() {
		return new Profesional_VO(this);
	}

	@Override
	public Profesional_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new Profesional_VO(this, profundidadActual, profundidadDeseada);
	}

	/**
	 * @param valorTipoID the valorTipoID to set
	 */
	@Override
	public void setValorTipoID(String valorTipoID) {
		super.setValorTipoID(valorTipoID);
		this.setNroMatriculaProvincial(valorTipoID);
	}
	
}