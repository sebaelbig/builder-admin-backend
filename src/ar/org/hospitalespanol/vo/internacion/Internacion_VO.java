package ar.org.hospitalespanol.vo.internacion;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.org.hospitalespanol.model.core.datosSalud.diagnosticos.Diagnostico;
import ar.org.hospitalespanol.model.historiaClinica.episodios.Episodio;
import ar.org.hospitalespanol.model.internacion.Internacion;
import ar.org.hospitalespanol.model.internacion.Reserva;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.core.datosSalud.Modulo_VO;
import ar.org.hospitalespanol.vo.core.datosSalud.diagnosticos.Diagnostico_VO;
import ar.org.hospitalespanol.vo.core.obrasSociales.Producto_OS_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.pacientes.Paciente_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.profesionales.Profesional_VO;
import ar.org.hospitalespanol.vo.historiaClinica.episodios.Episodio_VO;
import ar.org.hospitalespanol.vo.internacion.altas.AltaInternacion_VO;

public class Internacion_VO implements Serializable, I_ValueObject<Internacion> {

	private static final long serialVersionUID = 1L;
	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Long numero;

	private Integer version;

	private Date fechaAsignacion;

	private Date fechaInicio;
	private Date fechaFin;
	private String str_fechaInicio;
	private String str_fechaFin;

	private Date horaInicio;
	private Date horaFin;
	private String str_horaInicio;
	private String str_horaFin;

	private Integer cantHoraInicio;
	private Integer cantHoraFin;

	private Boolean activo = true;

	private List<Episodio_VO> episodios = new ArrayList<Episodio_VO>();

	private Modulo_VO modulo;

	// Datos internacion
	private List<Diagnostico_VO> diagnosticos;

	private String tratamiento;

	private String tipoInternacion;

	private String motivoInternacion;

	// Datos HC y productos OS
	private Producto_OS_VO producto;

	// Datos paciente
	private Paciente_VO paciente;

	private Profesional_VO profesionalSolicitante;
	private Profesional_VO profesionalCirujano;
	private Profesional_VO profesionalDerivacion;

	private AltaInternacion_VO altaInternacion;

	private Boolean dadoDeAlta = false;
	private Boolean cierreAdministrativo = false;

	private List<Reserva_VO> reservas;

	public Internacion_VO(Internacion i) {
		this.setObject(i);
	}

	public Internacion_VO(Internacion i, int profundidadActual,
			int profundidadDeseada) {
		this.setObject(i, profundidadActual, profundidadDeseada);
	}

	public Internacion_VO() {
	}

	@Override
	public void setObject(Internacion i) {
		this.setId(i.getId());
		this.setVersion(i.getVersion());

		this.setActivo(i.getActivo());
		this.setCantHoraFin(i.getCantHoraFin());
		this.setCantHoraInicio(i.getCantHoraInicio());
		this.setCierreAdminstrativo(i.getCierreAdministrativo());
		this.setDadoDeAlta(i.getDadoDeAlta());
		this.setFechaAsignacion(i.getFechaAsignacion());
		this.setFechaFin(i.getFechaFin());
		this.setFechaInicio(i.getFechaInicio());
		this.setHoraFin(i.getHoraFin());
		this.setHoraInicio(i.getHoraInicio());

		this.setTratamiento(i.getTratamiento());
		this.setTipoInternacion(i.getTipoInternacion());
		this.setMotivoInternacion(i.getMotivoInternacion());

		this.setPaciente(i.getPaciente().toValueObject());

		if (i.getModulo() != null)
			this.setModulo(i.getModulo().toValueObject());
		if (i.getProfesionalSolicitante() != null)
			this.setProfesionalSolicitante(i.getProfesionalSolicitante()
					.toValueObject());
		if (i.getProfesionalCirujano() != null)
			this.setProfesionalCirujano(i.getProfesionalCirujano()
					.toValueObject());
		if (i.getProfesionalDerivacion() != null)
			this.setProfesionalDerivacion(i.getProfesionalDerivacion()
					.toValueObject());
		if (i.getProductoOS() != null)
			this.setProducto(i.getProductoOS().toValueObject());
		if (i.getAltaInternacion() != null)
			this.setAltaInternacion(i.getAltaInternacion().toValueObject());

		List<Episodio_VO> epis = new ArrayList<Episodio_VO>();
		for (Episodio epi : i.getEpisodios()) {
			epis.add(epi.toValueObject());
		}
		this.setEpisodios(epis);

		List<Diagnostico_VO> diags = new ArrayList<Diagnostico_VO>();
		for (Diagnostico diag : i.getDiagnosticos()) {
			diags.add(diag.toValueObject());
		}
		this.setDiagnosticos(diags);

		List<Reserva_VO> ress = new ArrayList<Reserva_VO>();
		for (Reserva res : i.getReservas()) {
			ress.add(res.toValueObject(I_ValueObject.PROFUNDIDAD_BASE, 0));
		}
		this.setReservas(ress);

	}

	@Override
	public void setObject(Internacion i, int profundidadActual,
			int profundidadDeseada) {
		this.setId(i.getId());
		this.setVersion(i.getVersion());

		this.setActivo(i.getActivo());
		this.setCantHoraFin(i.getCantHoraFin());
		this.setCantHoraInicio(i.getCantHoraInicio());
		this.setCierreAdminstrativo(i.getCierreAdministrativo());
		this.setDadoDeAlta(i.getDadoDeAlta());
		this.setFechaAsignacion(i.getFechaAsignacion());
		this.setFechaFin(i.getFechaFin());
		this.setFechaInicio(i.getFechaInicio());
		this.setHoraFin(i.getHoraFin());
		this.setHoraInicio(i.getHoraInicio());

		this.setTratamiento(i.getTratamiento());
		this.setTipoInternacion(i.getTipoInternacion());
		this.setMotivoInternacion(i.getMotivoInternacion());

		List<Episodio_VO> epis = new ArrayList<Episodio_VO>();
		List<Diagnostico_VO> diags = new ArrayList<Diagnostico_VO>();
		List<Reserva_VO> ress = new ArrayList<Reserva_VO>();
		if (profundidadActual < profundidadDeseada) {
			this.setPaciente(i.getPaciente().toValueObject(
					profundidadActual + 1, profundidadDeseada));

			if (i.getModulo() != null)
				this.setModulo(i.getModulo().toValueObject(
						profundidadActual + 1, profundidadDeseada));
			if (i.getProfesionalSolicitante() != null)
				this.setProfesionalSolicitante(i.getProfesionalSolicitante()
						.toValueObject(profundidadActual + 1,
								profundidadDeseada));
			if (i.getProfesionalCirujano() != null)
				this.setProfesionalCirujano(i.getProfesionalCirujano()
						.toValueObject(profundidadActual + 1,
								profundidadDeseada));
			if (i.getProfesionalDerivacion() != null)
				this.setProfesionalDerivacion(i.getProfesionalDerivacion()
						.toValueObject(profundidadActual + 1,
								profundidadDeseada));
			if (i.getProductoOS() != null)
				this.setProducto(i.getProductoOS().toValueObject(
						profundidadActual + 1, profundidadDeseada));
			if (i.getAltaInternacion() != null)
				this.setAltaInternacion(i.getAltaInternacion().toValueObject(
						profundidadActual + 1, profundidadDeseada));

			for (Episodio epi : i.getEpisodios()) {
				epis.add(epi.toValueObject(profundidadActual + 1,
						profundidadDeseada));
			}

			for (Diagnostico diag : i.getDiagnosticos()) {
				diags.add(diag.toValueObject(profundidadActual + 1,
						profundidadDeseada));
			}

			for (Reserva res : i.getReservas()) {
				ress.add(res.toValueObject(I_ValueObject.PROFUNDIDAD_BASE, 0));
			}
		}
		this.setEpisodios(epis);
		this.setDiagnosticos(diags);
		this.setReservas(ress);
	}

	@Override
	public Internacion toObject() {
		Internacion i = new Internacion();

		i.setId(this.getId());
		i.setVersion(this.getVersion());

		i.setActivo(this.getActivo());
		i.setCantHoraFin(this.getCantHoraFin());
		i.setCantHoraInicio(this.getCantHoraInicio());
		i.setCierreAdministrativo(this.getCierreAdminstrativo());
		i.setDadoDeAlta(this.getDadoDeAlta());
		i.setFechaAsignacion(this.getFechaAsignacion());
		i.setFechaFin(this.getFechaFin());
		i.setFechaInicio(this.getFechaInicio());
		i.setHoraFin(this.getHoraFin());
		i.setHoraInicio(this.getHoraInicio());

		i.setTratamiento(this.getTratamiento());
		i.setTipoInternacion(this.getTipoInternacion());
		i.setMotivoInternacion(this.getMotivoInternacion());

		if (this.getModulo() != null)
			i.setModulo(this.getModulo().toObject());
		if (this.getProfesionalSolicitante() != null)
			i.setProfesionalSolicitante(this.getProfesionalSolicitante()
					.toObject());
		if (this.getProfesionalCirujano() != null)
			i.setProfesionalCirujano(this.getProfesionalCirujano().toObject());
		if (this.getProfesionalDerivacion() != null)
			i.setProfesionalDerivacion(this.getProfesionalDerivacion()
					.toObject());
		if (this.getProducto() != null)
			i.setProductoOS(this.getProducto().toObject());
		if (this.getAltaInternacion() != null)
			i.setAltaInternacion(this.getAltaInternacion().toObject());

		List<Episodio> epis = new ArrayList<Episodio>();
		List<Diagnostico> diags = new ArrayList<Diagnostico>();
		List<Reserva> ress = new ArrayList<Reserva>();

		for (Episodio_VO epi : this.getEpisodios()) {
			epis.add(epi.toObject());
		}
		i.setEpisodios(epis);

		for (Diagnostico_VO diag : this.getDiagnosticos()) {
			diags.add(diag.toObject());
		}
		i.setDiagnosticos(diags);

		for (Reserva_VO res : this.getReservas()) {
			ress.add(res.toObject(I_ValueObject.PROFUNDIDAD_BASE, 0));
		}
		i.setReservas(ress);

		return i;
	}

	public Internacion toObject(int profundidadActual, int profundidadDeseada) {
		Internacion i = new Internacion();

		i.setId(this.getId());
		i.setVersion(this.getVersion());

		i.setActivo(this.getActivo());
		i.setCantHoraFin(this.getCantHoraFin());
		i.setCantHoraInicio(this.getCantHoraInicio());
		i.setCierreAdministrativo(this.getCierreAdminstrativo());
		i.setDadoDeAlta(this.getDadoDeAlta());
		i.setFechaAsignacion(this.getFechaAsignacion());
		i.setFechaFin(this.getFechaFin());
		i.setFechaInicio(this.getFechaInicio());
		i.setHoraFin(this.getHoraFin());
		i.setHoraInicio(this.getHoraInicio());

		i.setTratamiento(this.getTratamiento());
		i.setTipoInternacion(this.getTipoInternacion());
		i.setMotivoInternacion(this.getMotivoInternacion());

		List<Episodio> epis = new ArrayList<Episodio>();
		List<Diagnostico> diags = new ArrayList<Diagnostico>();
		List<Reserva> ress = new ArrayList<Reserva>();

		if (profundidadActual < profundidadDeseada) {
			if (this.getModulo() != null)
				i.setModulo(this.getModulo().toObject(profundidadActual + 1,
						profundidadDeseada));
			if (this.getPaciente() != null)
				i.setPaciente(this.getPaciente().toObject(
						profundidadActual + 1, profundidadDeseada));
			if (this.getProfesionalSolicitante() != null)
				i.setProfesionalSolicitante(this.getProfesionalSolicitante()
						.toObject());
			if (this.getProfesionalCirujano() != null)
				i.setProfesionalCirujano(this.getProfesionalCirujano()
						.toObject());
			if (this.getProfesionalDerivacion() != null)
				i.setProfesionalDerivacion(this.getProfesionalDerivacion()
						.toObject());
			if (this.getProducto() != null)
				i.setProductoOS(this.getProducto().toObject());
			if (this.getAltaInternacion() != null)
				i.setAltaInternacion(this.getAltaInternacion().toObject());

			for (Episodio_VO epi : this.getEpisodios()) {
				epis.add(epi
						.toObject(profundidadActual + 1, profundidadDeseada));
			}

			for (Diagnostico_VO diag : this.getDiagnosticos()) {
				diags.add(diag.toObject(profundidadActual + 1,
						profundidadDeseada));
			}

			for (Reserva_VO res : this.getReservas()) {
				ress.add(res.toObject(I_ValueObject.PROFUNDIDAD_BASE, 0));
			}
		}
		i.setEpisodios(epis);
		i.setDiagnosticos(diags);
		i.setReservas(ress);

		return i;
	}

	public int compareTo(Internacion_VO bt) {
		return 0;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
		this.setNumero(id);
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
		if (horaFin != null) {
			this.setStr_horaFin(new SimpleDateFormat("HH:mm").format(horaFin));
		}
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
		if (horaInicio != null) {
			this.setStr_horaInicio(new SimpleDateFormat("HH:mm")
					.format(horaInicio));
		}
	}

	public Integer getCantHoraInicio() {
		return cantHoraInicio;
	}

	public void setCantHoraInicio(Integer cantHoraInicio) {
		this.cantHoraInicio = cantHoraInicio;
	}

	public Integer getCantHoraFin() {
		return cantHoraFin;
	}

	public void setCantHoraFin(Integer cantHoraFin) {
		this.cantHoraFin = cantHoraFin;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
		if (fechaInicio != null) {
			this.setStr_fechaInicio(new SimpleDateFormat("dd/MM/yyyy")
					.format(fechaInicio));
		}
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
		if (fechaFin != null) {
			this.setStr_fechaFin(new SimpleDateFormat("dd/MM/yyyy")
					.format(fechaFin));
		}
	}

	public String getStr_fechaInicio() {
		return str_fechaInicio;
	}

	public void setStr_fechaInicio(String str_fechaInicio) {
		this.str_fechaInicio = str_fechaInicio;
	}

	public String getStr_fechaFin() {
		return str_fechaFin;
	}

	public void setStr_fechaFin(String str_fechaFin) {
		this.str_fechaFin = str_fechaFin;
	}

	public String getStr_horaInicio() {
		return str_horaInicio;
	}

	public void setStr_horaInicio(String str_horaInicio) {
		this.str_horaInicio = str_horaInicio;
	}

	public String getStr_horaFin() {
		return str_horaFin;
	}

	public void setStr_horaFin(String str_horaFin) {
		this.str_horaFin = str_horaFin;
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Internacion_VO) {
			Internacion_VO o = (Internacion_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	public List<Episodio_VO> getEpisodios() {
		return episodios;
	}

	public void setEpisodios(List<Episodio_VO> episodios) {
		this.episodios = episodios;
	}

	public Modulo_VO getModulo() {
		return modulo;
	}

	public void setModulo(Modulo_VO modulo) {
		this.modulo = modulo;
	}

	public Producto_OS_VO getProducto() {
		return producto;
	}

	public void setProducto(Producto_OS_VO producto) {
		this.producto = producto;
	}

	public Paciente_VO getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente_VO paciente) {
		this.paciente = paciente;
	}

	public AltaInternacion_VO getAltaInternacion() {
		return altaInternacion;
	}

	public void setAltaInternacion(AltaInternacion_VO altaInternacion) {
		this.altaInternacion = altaInternacion;
		this.setDadoDeAlta(dadoDeAlta != null);
	}

	public Boolean getCierreAdminstrativo() {
		return cierreAdministrativo;
	}

	public void setCierreAdminstrativo(Boolean cierreAdminstrativo) {
		this.cierreAdministrativo = cierreAdminstrativo;
	}

	public Date getHoraFin() {
		return horaFin;
	}

	public Boolean getDadoDeAlta() {
		return dadoDeAlta;
	}

	public void setDadoDeAlta(Boolean dadoDeAlta) {
		this.dadoDeAlta = dadoDeAlta;
	}

	public Boolean getCierreAdministrativo() {
		return cierreAdministrativo;
	}

	public void setCierreAdministrativo(Boolean cierreAdministrativo) {
		this.cierreAdministrativo = cierreAdministrativo;
	}

	public List<Diagnostico_VO> getDiagnosticos() {
		return diagnosticos;
	}

	public void setDiagnosticos(List<Diagnostico_VO> diagnosticos) {
		this.diagnosticos = diagnosticos;
	}

	public Profesional_VO getProfesionalSolicitante() {
		return profesionalSolicitante;
	}

	public void setProfesionalSolicitante(Profesional_VO profesionalSolicitante) {
		this.profesionalSolicitante = profesionalSolicitante;
	}

	public Profesional_VO getProfesionalCirujano() {
		return profesionalCirujano;
	}

	public void setProfesionalCirujano(Profesional_VO profesionalCirujano) {
		this.profesionalCirujano = profesionalCirujano;
	}

	public Profesional_VO getProfesionalDerivacion() {
		return profesionalDerivacion;
	}

	public void setProfesionalDerivacion(Profesional_VO profesionalDerivacion) {
		this.profesionalDerivacion = profesionalDerivacion;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	public String getTipoInternacion() {
		return tipoInternacion;
	}

	public void setTipoInternacion(String tipoInternacion) {
		this.tipoInternacion = tipoInternacion;
	}

	public String getMotivoInternacion() {
		return motivoInternacion;
	}

	public void setMotivoInternacion(String motivoInternacion) {
		this.motivoInternacion = motivoInternacion;
	}

	public List<Reserva_VO> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva_VO> reservas) {
		this.reservas = reservas;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

}