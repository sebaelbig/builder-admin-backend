package ar.org.hospitalespanol.vo.historiaClinica.episodios;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.org.hospitalespanol.model.core.datosSalud.Prestacion;
import ar.org.hospitalespanol.model.historiaClinica.episodios.Episodio;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.core.areas.Sucursal_VO;
import ar.org.hospitalespanol.vo.core.datosSalud.Prestacion_VO;
import ar.org.hospitalespanol.vo.core.obrasSociales.ProductoObraSocialPaciente_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.Rol_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.pacientes.Paciente_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.profesionales.Profesional_VO;

public class Episodio_VO implements I_ValueObject<Episodio> {

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Integer version;

	private Paciente_VO paciente;

	private ProductoObraSocialPaciente_VO producto_OSPaciente;

	private List<Prestacion_VO> prestaciones = new ArrayList<Prestacion_VO>();

	private String informeInstitucional;

	private String notaPrivada;

	private Date fechaRealizado;

	private Profesional_VO profesionalSolicitante;

	private Rol_VO profesionalActuante;

	private String tipoProfesional = "Institucional";

	private Boolean pacienteInternado = false;

	private Integer nroCamaPacienteInternado;

	private String nroEpisodio;

	private Long id_episodioOrigen;

	private Boolean cerrado = false;

	private Sucursal_VO sucursal;

	public Episodio_VO() {
	}

	public Episodio_VO(Episodio episodio) {
		setObject(episodio);
	}

	public Episodio_VO(Episodio episodio, int profundidadActual,
			int profundidadDeseada) {
		setObject(episodio, profundidadActual, profundidadDeseada);
	}

	@Override
	public void setObject(Episodio episodio) {
		setId(episodio.getId());
		setVersion(episodio.getVersion());
		setId_episodioOrigen(episodio.getId_episodioOrigen());

		setPaciente(new Paciente_VO(episodio.getPaciente(), episodio
				.getPaciente().getUsuario()));
		if (episodio.getProducto_OSPaciente() != null) {
			setProducto_OSPaciente(new ProductoObraSocialPaciente_VO(
					episodio.getProducto_OSPaciente()));
		}

		if (episodio.getPrestaciones() != null) {

			for (Prestacion p : episodio.getPrestaciones()) {

				this.getPrestaciones().add(new Prestacion_VO(p));
			}

			// setArea(episodio.getPrestacion().getCodigoArea());
		}

		setFechaRealizado(episodio.getFechaRealizado());
		if (episodio.getProfesionalSolicitante() != null) {
			setProfesionalSolicitante(new Profesional_VO(
					episodio.getProfesionalSolicitante()));
		}
		if (episodio.getProfesionalActuante() != null) {
			setProfesionalActuante(episodio.getProfesionalActuante()
					.toValueObject());
		}

		if (episodio.getNroEpisodio() != null) {
			setNroEpisodio(episodio.getNroEpisodio());
		}

		setInformeInstitucional(episodio.getInformeInstitucional());

		if (episodio.getInformeInstitucional() != null
				&& !episodio.getInformeInstitucional().equals("")) {
			// Si ya esta escrito el informe, se da por cerrado el episodio
			this.setCerrado(true);
		}

	}

	@Override
	public void setObject(Episodio episodio, int profundidadActual,
			int profundidadDeseada) {
		setId(episodio.getId());
		setVersion(episodio.getVersion());
		setId_episodioOrigen(episodio.getId_episodioOrigen());
		setFechaRealizado(episodio.getFechaRealizado());
		setInformeInstitucional(episodio.getInformeInstitucional());

		if (episodio.getNroEpisodio() != null) {
			setNroEpisodio(episodio.getNroEpisodio());
		}

		if (episodio.getInformeInstitucional() != null
				&& !episodio.getInformeInstitucional().equals("")) {
			// Si ya esta escrito el informe, se da por cerrado el episodio
			this.setCerrado(true);
		}

		if (profundidadActual < profundidadDeseada) {

			setPaciente(episodio.getPaciente().toValueObject(
					profundidadActual + 1, profundidadDeseada));

			if (episodio.getProducto_OSPaciente() != null) {
				setProducto_OSPaciente(episodio.getProducto_OSPaciente()
						.toValueObject(profundidadActual + 1,
								profundidadDeseada));
			}

			if (episodio.getPrestaciones() != null) {
				for (Prestacion p : episodio.getPrestaciones()) {
					this.getPrestaciones().add(
							p.toValueObject(profundidadActual + 1,
									profundidadDeseada));
				}
			}

			if (episodio.getProfesionalSolicitante() != null) {
				setProfesionalSolicitante(episodio.getProfesionalSolicitante()
						.toValueObject(profundidadActual + 1,
								profundidadDeseada));
			}

			if (episodio.getProfesionalActuante() != null) {
				setProfesionalActuante(episodio.getProfesionalActuante()
						.toValueObject(profundidadActual + 1,
								profundidadDeseada));
			}

		}

	}

	@Override
	public Episodio toObject() {
		Episodio resul = new Episodio();

		resul.setId(this.getId());
		resul.setId_episodioOrigen(this.getId_episodioOrigen());
		resul.setVersion(this.getVersion());

		resul.setFechaRealizado(this.getFechaRealizado());
		resul.setInformeInstitucional(this.getInformeInstitucional());
		resul.setNroEpisodio(this.getNroEpisodio());
		resul.setPacienteInternado(this.getPacienteInternado());
		resul.setTipoProfesional(this.getTipoProfesional());

		return resul;
	}

	public Episodio toObject(int profundidadActual, int profundidadDeseada) {
		return toObject();
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public Paciente_VO getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente_VO paciente) {
		this.paciente = paciente;
	}

	public String getInformeInstitucional() {
		return informeInstitucional;
	}

	public void setInformeInstitucional(String informeInstitucional) {
		this.informeInstitucional = informeInstitucional;
	}

	public String getNotaPrivada() {
		return notaPrivada;
	}

	public void setNotaPrivada(String notaPrivada) {
		this.notaPrivada = notaPrivada;
	}

	public Date getFechaRealizado() {
		return fechaRealizado;
	}

	public void setFechaRealizado(Date fechaRealizado) {
		this.fechaRealizado = fechaRealizado;
	}

	public Profesional_VO getProfesionalSolicitante() {
		return profesionalSolicitante;
	}

	public void setProfesionalSolicitante(Profesional_VO profesionalSolicitante) {
		this.profesionalSolicitante = profesionalSolicitante;
	}

	public Rol_VO getProfesionalActuante() {
		return profesionalActuante;
	}

	public void setProfesionalActuante(Rol_VO profesionalActuante) {
		this.profesionalActuante = profesionalActuante;
	}

	public String getTipoProfesional() {
		return tipoProfesional;
	}

	public void setTipoProfesional(String tipoProfesional) {
		this.tipoProfesional = tipoProfesional;
	}

	public Boolean getPacienteInternado() {
		return pacienteInternado;
	}

	public void setPacienteInternado(Boolean pacienteInternado) {
		this.pacienteInternado = pacienteInternado;
	}

	public Integer getNroCamaPacienteInternado() {
		return nroCamaPacienteInternado;
	}

	public void setNroCamaPacienteInternado(Integer nroCamaPacienteInternado) {
		this.nroCamaPacienteInternado = nroCamaPacienteInternado;
	}

	public String getNroEpisodio() {
		return nroEpisodio;
	}

	public void setNroEpisodio(String nroEpisodio) {
		this.nroEpisodio = nroEpisodio;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public ProductoObraSocialPaciente_VO getProducto_OSPaciente() {
		return producto_OSPaciente;
	}

	public void setProducto_OSPaciente(
			ProductoObraSocialPaciente_VO producto_OSPaciente) {
		this.producto_OSPaciente = producto_OSPaciente;
	}

	public List<Prestacion_VO> getPrestaciones() {
		return prestaciones;
	}

	public void setPrestaciones(List<Prestacion_VO> prestas) {
		this.prestaciones = prestas;
	}

	public Long getId_episodioOrigen() {
		return id_episodioOrigen;
	}

	public void setId_episodioOrigen(Long id_episodioOrigen) {
		this.id_episodioOrigen = id_episodioOrigen;
	}

	@Override
	public String toString() {
		return "Episodio Nro: " + this.getNroEpisodio();
	}

	public String getDescripcion() {

		String desc = "El paciente " + this.getPaciente().toString();
		desc += " se realiz� el "
				+ new SimpleDateFormat("dd/MM/yy HH:mm").format(this
						.getFechaRealizado());

		// En caso de haber sido pedido, se cita al profesional solicitante
		if (this.getProfesionalSolicitante() != null) {
			desc += " bajo la solicitud del profesional "
					+ this.getProfesionalSolicitante().toString();
		}

		// Listo las prestaciones
		desc += ", las siguientes prestaciones: " + " \n ";
		for (Integer i = 0; i < this.getPrestaciones().size(); i++) {
			desc += i + ") "
					+ this.getPrestaciones().get(i.intValue()).toString()
					+ " \n ";
		}

		// Si o si alguien registro este episodio
		if (this.getProfesionalActuante() != null) {
			desc += " realizadas por "
					+ this.getProfesionalActuante().toString();
		}

		return desc;
	}

	public Boolean getCerrado() {
		return cerrado;
	}

	public void setCerrado(Boolean cerrado) {
		this.cerrado = cerrado;
	}

	public Sucursal_VO getSucursal() {
		return this.sucursal;
	}

	public void setSucursal(Sucursal_VO sucursal) {
		this.sucursal = sucursal;
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Episodio_VO) {
			Episodio_VO o = (Episodio_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

}
