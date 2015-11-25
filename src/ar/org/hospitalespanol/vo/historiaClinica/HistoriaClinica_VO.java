package ar.org.hospitalespanol.vo.historiaClinica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.org.hospitalespanol.model.historiaClinica.HistoriaClinica;
import ar.org.hospitalespanol.model.historiaClinica.antecedentes.AntecedenteFamiliarHistoriaClinica;
import ar.org.hospitalespanol.model.historiaClinica.episodios.Episodio;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.core.usuarios.roles.pacientes.Paciente_VO;
import ar.org.hospitalespanol.vo.historiaClinica.antecedentes.AntecedenteFamiliarHistoriaClinica_VO;
import ar.org.hospitalespanol.vo.historiaClinica.antecedentes.AntecedentesNoPatologicos_VO;
import ar.org.hospitalespanol.vo.historiaClinica.antecedentes.AntecedentesPatologicos_VO;
import ar.org.hospitalespanol.vo.historiaClinica.episodios.Episodio_VO;

public class HistoriaClinica_VO implements I_ValueObject<HistoriaClinica> {

	private String numero;

	private String numeroInterno;

	private Date fechaCreacion;

	private Long id; private Boolean borrado = false;@Override
	public Boolean getBorrado(){return this.borrado;}@Override
	public void setBorrado(Boolean b){this.borrado=b;}

	private Integer version;

	private List<Episodio_VO> episodios;

	private Paciente_VO paciente;

	// Antecedentes
	private List<AntecedenteFamiliarHistoriaClinica_VO> antecedentesFamiliares = new ArrayList<AntecedenteFamiliarHistoriaClinica_VO>();

	private AntecedentesNoPatologicos_VO antecedenteNoPatologico = new AntecedentesNoPatologicos_VO();

	private AntecedentesPatologicos_VO antecedentePatologicos = new AntecedentesPatologicos_VO();

	public HistoriaClinica_VO() {
		setFechaCreacion(new Date());
		setEpisodios(new ArrayList<Episodio_VO>());
	}

	public HistoriaClinica_VO(HistoriaClinica hc) {
		this.setObject(hc);
	}

	public HistoriaClinica_VO(HistoriaClinica hc, int profundidadActual,
			int profundidadDeseada) {
		this.setObject(hc, profundidadActual, profundidadDeseada);
	}

	@Override
	public void setObject(HistoriaClinica hc) {

		this.setId(hc.getId());
		this.setVersion(hc.getVersion());
		this.setNumero(hc.getNumero());
		this.setNumeroInterno(hc.getNumeroInterno());
		this.setFechaCreacion(hc.getFechaCreacion());

		this.setEpisodios(new ArrayList<Episodio_VO>());
		for (Episodio ep : hc.getEpisodios()) {
			this.getEpisodios().add(
					ep.toValueObject(I_ValueObject.PROFUNDIDAD_BASE, 2));
		}

		if (hc != null) {
			if (hc.getPaciente() != null) {
				this.setPaciente(hc.getPaciente().toValueObject(
						I_ValueObject.PROFUNDIDAD_BASE, 0));
			}

			if (hc.getAntecedentes().size() > 0) {
				for (AntecedenteFamiliarHistoriaClinica af : hc
						.getAntecedentes()) {
					this.getAntecedentesFamiliares().add(af.toValueObject());
				}
			}

			if (hc.getAntecedentePatologicos() != null) {
				this.setAntecedentePatologicos(hc.getAntecedentePatologicos()
						.toValueObject());
			}

			if (hc.getAntecedenteNoPatologico() != null) {
				this.setAntecedenteNoPatologico(hc.getAntecedenteNoPatologico()
						.toValueObject());
			}
		}
	}

	@Override
	public void setObject(HistoriaClinica hc, int profundidadActual,
			int profundidadDeseada) {
		this.setId(hc.getId());
		this.setVersion(hc.getVersion());
		this.setNumero(hc.getNumero());
		this.setNumeroInterno(hc.getNumeroInterno());
		this.setFechaCreacion(hc.getFechaCreacion());

		this.setEpisodios(new ArrayList<Episodio_VO>());
		if (profundidadActual < profundidadDeseada) {

			for (Episodio ep : hc.getEpisodios()) {
				this.getEpisodios().add(
						ep.toValueObject(profundidadActual + 1,
								profundidadDeseada));
			}
			if (hc.getPaciente() != null)
				this.setPaciente(hc.getPaciente().toValueObject(
						I_ValueObject.PROFUNDIDAD_BASE, 0));

		}

		if (hc.getAntecedentes().size() > 0) {
			this.setAntecedentesFamiliares(new ArrayList<AntecedenteFamiliarHistoriaClinica_VO>());
			for (AntecedenteFamiliarHistoriaClinica af : hc.getAntecedentes()) {
				this.getAntecedentesFamiliares().add(af.toValueObject());
			}
		}

		if (hc.getAntecedentePatologicos() != null) {
			this.setAntecedentePatologicos(hc.getAntecedentePatologicos()
					.toValueObject());
		}

		if (hc.getAntecedenteNoPatologico() != null) {
			this.setAntecedenteNoPatologico(hc.getAntecedenteNoPatologico()
					.toValueObject());
		}

	}

	@Override
	public HistoriaClinica toObject() {
		HistoriaClinica resul = new HistoriaClinica();

		resul.setId(this.getId());
		resul.setVersion(this.getVersion());
		resul.setNumero(this.getNumero());
		resul.setNumeroInterno(this.getNumeroInterno());
		resul.setFechaCreacion(this.getFechaCreacion());
		resul.setEpisodios(new ArrayList<Episodio>());

		for (Episodio_VO ep : getEpisodios()) {
			resul.getEpisodios().add(ep.toObject());
		}

		if (this.getAntecedentesFamiliares() != null) {
			for (AntecedenteFamiliarHistoriaClinica_VO aFam : this
					.getAntecedentesFamiliares()) {
				resul.getAntecedentes().add(aFam.toObject());
			}
		}
		resul.setAntecedentePatologicos(this.getAntecedentePatologicos()
				.toObject());
		resul.setAntecedenteNoPatologico(this.getAntecedenteNoPatologico()
				.toObject());

//		resul.setPaciente(this.getPaciente().toObject(PROFUNDIDAD_BASE,
//				PROFUNDIDAD_BASE));

		return resul;
	}

	public HistoriaClinica toObject(int profundidadActual,
			int profundidadDeseada) {
		HistoriaClinica resul = new HistoriaClinica();

		resul.setId(this.getId());
		resul.setVersion(this.getVersion());
		resul.setNumero(this.getNumero());
		resul.setNumeroInterno(this.getNumeroInterno());
		resul.setFechaCreacion(this.getFechaCreacion());

		resul.setEpisodios(new ArrayList<Episodio>());

		if (profundidadActual < profundidadDeseada) {

			for (Episodio_VO ep : getEpisodios()) {
				resul.getEpisodios().add(ep.toObject());
			}

			if (this.getAntecedentesFamiliares() != null) {
				for (AntecedenteFamiliarHistoriaClinica_VO aFam : this
						.getAntecedentesFamiliares()) {
					resul.getAntecedentes().add(
							aFam.toObject(profundidadActual + 1,
									profundidadDeseada));
				}
			}

			resul.setAntecedentePatologicos(this.getAntecedentePatologicos()
					.toObject(profundidadActual + 1, profundidadDeseada));
			resul.setAntecedenteNoPatologico(this.getAntecedenteNoPatologico()
					.toObject(profundidadActual + 1, profundidadDeseada));

		}

		return resul;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNumeroInterno() {
		return numeroInterno;
	}

	public void setNumeroInterno(String numeroInterno) {
		this.numeroInterno = numeroInterno;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Integer getVersion() {
		return version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<Episodio_VO> getEpisodios() {
		return episodios;
	}

	public void setEpisodios(List<Episodio_VO> episodios) {
		this.episodios = episodios;
	}

	public void agregarEpisodio(Episodio_VO episodio_VO) {
		getEpisodios().add(episodio_VO);

	}

	public Paciente_VO getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente_VO paciente) {
		this.paciente = paciente;
		// if (paciente!=null){
		// this.setNumero(paciente.getUsuario().getNroDocumento());
		// }
	}

	public List<AntecedenteFamiliarHistoriaClinica_VO> getAntecedentesFamiliares() {
		return antecedentesFamiliares;
	}

	public void setAntecedentesFamiliares(
			List<AntecedenteFamiliarHistoriaClinica_VO> antecedentesFamiliares) {
		this.antecedentesFamiliares = antecedentesFamiliares;
	}

	public AntecedentesNoPatologicos_VO getAntecedenteNoPatologico() {
		return antecedenteNoPatologico;
	}

	public void setAntecedenteNoPatologico(
			AntecedentesNoPatologicos_VO antecedenteNoPatologico) {
		this.antecedenteNoPatologico = antecedenteNoPatologico;
	}

	public AntecedentesPatologicos_VO getAntecedentePatologicos() {
		return antecedentePatologicos;
	}

	public void setAntecedentePatologicos(
			AntecedentesPatologicos_VO antecedentePatologicos) {
		this.antecedentePatologicos = antecedentePatologicos;
	}

}
