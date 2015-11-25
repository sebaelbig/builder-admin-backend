package ar.org.hospitalespanol.vo.core.usuarios.roles.profesionales;

import ar.org.hospitalespanol.model.core.usuarios.roles.profesionales.FirmaProfesional;
import ar.org.hospitalespanol.model.core.usuarios.roles.profesionales.TipoDeMatricula;
import ar.org.hospitalespanol.vo.I_ValueObject;

public class FirmaProfesional_VO implements I_ValueObject<FirmaProfesional> {

	/**
	 * Entity ID.
	 */
	private Long id;
	private Integer version;
	private Boolean borrado = false;

	private String texto;
	private String usuario;

	private String nroMatricula;
	private TipoDeMatricula tipoDeMatricula;

	private String especialidad;
	private String especialidad_renglon;
	private String email = "";
	private String nombreApellido;

	public FirmaProfesional_VO() {
	}

	public FirmaProfesional_VO(FirmaProfesional f) {
		this.setObject(f);
	}

	public FirmaProfesional_VO(FirmaProfesional f, int actual, int deseado) {
		this.setObject(f);
	}

	public FirmaProfesional_VO(ProfesionalHE_VO profeActuanteHE, String usuario) {
		
		this.setNroMatricula(profeActuanteHE.getNroMatricula().toString());
		this.setNombreApellido(profeActuanteHE.getApellido());
		this.setUsuario(usuario);
		
		if (profeActuanteHE.getTipoMatricula() == "M.P") {
			this.setTipoDeMatricula(tipoDeMatricula.MATRICULA_PROVINCIAL);
		} else {
			if (profeActuanteHE.getTipoMatricula() == "M.N") {
				this.setTipoDeMatricula(tipoDeMatricula.MATRICULA_PROVINCIAL);
			} else {
				this.setTipoDeMatricula(TipoDeMatricula.UNDEFINED);
			}
		}
		this.setEspecialidad(profeActuanteHE.getEspecialidadFirma());
		this.setEspecialidad_renglon(profeActuanteHE.getEspecialidad_renglon());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 * 
	 * tiene que ser XHTML
	 */
	@Override
	public String toString() {
		String segrenglon = "";
		if (this.getTipoDeMatricula() == null) {
			this.setTipoDeMatricula(TipoDeMatricula.UNDEFINED);
		}
		if (this.getEspecialidad() == null) {
			this.setEspecialidad("");
		} else {
		}
		if (this.getEspecialidad_renglon() != null) {
			segrenglon = "<p style='border:0; margin:0;'>"
					+ this.getEspecialidad_renglon() + "</p>";
		}
		return this.getNombreApellido() + "<p style='border:0; margin:0;'>"
				+ this.getEspecialidad() + "</p>"
				/* +"<br/>" */
				+ segrenglon + this.getTipoDeMatricula().abreviar() + "  "
				+ this.getNroMatricula().toString();

		// +this.getEmail()
	}

	@Override
	public FirmaProfesional toObject() {
		FirmaProfesional f = new FirmaProfesional();

		f.setId(this.getId());
		f.setVersion(this.getVersion());
		f.setBorrado(this.getBorrado());
		f.setNroMatricula(this.getNroMatricula());
		f.setEmail(this.getEmail());
		f.setNombreApellido(this.getNombreApellido());
		f.setTipoDeMatricula(this.getTipoDeMatricula());
		f.setEspecialidad(this.getEspecialidad());
		f.setEspecialidad_renglon(this.getEspecialidad_renglon());
		return f;
	}

	@Override
	public void setObject(FirmaProfesional f) {
		this.setId(f.getId());
		this.setVersion(f.getVersion());
		this.setBorrado(f.getBorrado());
		this.setNroMatricula(f.getNroMatricula());
		this.setEmail(f.getEmail());
		this.setNombreApellido(f.getNombreApellido());
		this.setTipoDeMatricula(f.getTipoDeMatricula());
		this.setEspecialidad(f.getEspecialidad());
		this.setEspecialidad_renglon(f.getEspecialidad_renglon());
	}

	@Override
	public void setObject(FirmaProfesional paramT, int profundidadActual,
			int profundidadDeseada) {
		this.setObject(paramT);
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public Integer getVersion() {
		return version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado = b;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the nroMatricula
	 */
	public String getNroMatricula() {
		return nroMatricula;
	}

	/**
	 * @param nroMatricula
	 *            the nroMatricula to set
	 */
	public void setNroMatricula(String nroMatricula) {
		this.nroMatricula = nroMatricula;
	}

	@Override
	public boolean equals(Object object) {
		if ((object instanceof FirmaProfesional_VO)) {
			FirmaProfesional_VO f = (FirmaProfesional_VO) object;
			return f.getId().equals(getId())
					&& f.getVersion().equals(getVersion());
		}
		return false;
	}

	/**
	 * @return the tipoDeMatricula
	 */
	public TipoDeMatricula getTipoDeMatricula() {
		return tipoDeMatricula;
	}

	/**
	 * @param tipoDeMatricula
	 *            the tipoDeMatricula to set
	 */
	public void setTipoDeMatricula(TipoDeMatricula tipoDeMatricula) {
		this.tipoDeMatricula = tipoDeMatricula;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the nombreApellido
	 */
	public String getNombreApellido() {
		return nombreApellido;
	}

	/**
	 * @param nombreApellido
	 *            the nombreApellido to set
	 */
	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	/**
	 * @return the especialidad
	 */
	public String getEspecialidad() {
		return especialidad;
	}

	/**
	 * @param especialidad
	 *            the especialidad to set
	 */
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getEspecialidad_renglon() {
		return especialidad_renglon;
	}

	public void setEspecialidad_renglon(String espFirmaRenglon) {
		this.especialidad_renglon = espFirmaRenglon;
	}

}