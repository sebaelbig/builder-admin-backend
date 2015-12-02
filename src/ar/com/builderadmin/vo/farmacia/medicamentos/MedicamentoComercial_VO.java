package ar.com.builderadmin.vo.farmacia.medicamentos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import ar.com.builderadmin.model.farmacia.medicamentos.MedicamentoComercial;
import ar.com.builderadmin.model.farmacia.medicamentos.PrincipioActivo;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.vo.farmacia.Laboratorio_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
public class MedicamentoComercial_VO implements Serializable,
		I_ValueObject<MedicamentoComercial> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}

	private Integer version;

	private String nombre;

	private String composicion;

	private String indicaciones;

	private String dosificacion;

	private String contraindicaciones;

	private String reaccionesAdversas;

	private String presentacion;

	private String conservacion;

	/**
	 * Laboratorio
	 */
	private Laboratorio_VO laboratorio;

	/**
	 * Presentacion de la medicamentoComercial
	 */
	private PresentacionMedicamento_VO presentacionMedicamento;

	/**
	 * Forma farmaceutica
	 */
	private FormaFarmaceutica_VO formaFarmaceutica;

	/**
	 * Es de venta libre
	 */
	private final Boolean ventaLibre = false;

	/**
	 * Principios activos del medicamento
	 */
	private List<PrincipioActivo_VO> principiosActivo = new ArrayList<PrincipioActivo_VO>();

	// Constructores
	public MedicamentoComercial_VO() {

	}

	public MedicamentoComercial_VO(MedicamentoComercial o) {
		this.setObject(o);
	}

	public MedicamentoComercial_VO(MedicamentoComercial o,
			int profundidadActual, int profundidadDeseada) {
		this.setObject(o, profundidadActual, profundidadDeseada);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof MedicamentoComercial) {
			MedicamentoComercial o = (MedicamentoComercial) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public MedicamentoComercial toObject() {
		MedicamentoComercial medCom = new MedicamentoComercial();

		medCom.setId(getId());
		medCom.setVersion(getVersion());

		medCom.setNombre(getNombre());
		medCom.setComposicion(getComposicion());
		medCom.setConservacion(getConservacion());
		medCom.setContraindicaciones(getContraindicaciones());
		medCom.setDosificacion(getDosificacion());
		medCom.setIndicaciones(getIndicaciones());
		medCom.setPresentacion(getPresentacion());

		medCom.setLaboratorio(getLaboratorio().toObject());
		medCom.setFormaFarmaceutica(getFormaFarmaceutica().toObject());
		medCom.setPresentacionMedicamento(getPresentacionMedicamento()
				.toObject());

		for (PrincipioActivo_VO pa : getPrincipiosActivo()) {
			medCom.getPrincipiosActivo().add(pa.toObject());
		}

		medCom.setReaccionesAdversas(getReaccionesAdversas());

		return medCom;
	}

	public MedicamentoComercial toObject(int profundidadActual,
			int profundidadDeseada) {
		MedicamentoComercial medCom = new MedicamentoComercial();

		medCom.setId(getId());
		medCom.setVersion(getVersion());

		medCom.setNombre(getNombre());
		medCom.setComposicion(getComposicion());
		medCom.setConservacion(getConservacion());
		medCom.setContraindicaciones(getContraindicaciones());
		medCom.setDosificacion(getDosificacion());
		medCom.setIndicaciones(getIndicaciones());
		medCom.setPresentacion(getPresentacion());

		medCom.setLaboratorio(getLaboratorio().toObject());
		medCom.setFormaFarmaceutica(getFormaFarmaceutica().toObject());
		medCom.setPresentacionMedicamento(getPresentacionMedicamento()
				.toObject());

		for (PrincipioActivo_VO pa : getPrincipiosActivo()) {
			medCom.getPrincipiosActivo().add(
					pa.toObject(profundidadActual + 1, profundidadDeseada));
		}

		medCom.setReaccionesAdversas(getReaccionesAdversas());

		return medCom;
	}

	@Override
	public void setObject(MedicamentoComercial o) {

		this.setId(o.getId());
		this.setVersion(o.getVersion());

		this.setNombre(o.getNombre());
		this.setComposicion(o.getComposicion());
		this.setConservacion(o.getConservacion());
		this.setContraindicaciones(o.getContraindicaciones());
		this.setDosificacion(o.getDosificacion());
		this.setIndicaciones(o.getIndicaciones());
		this.setPresentacion(o.getPresentacion());

		this.setLaboratorio(o.getLaboratorio().toValueObject());
		this.setFormaFarmaceutica(o.getFormaFarmaceutica().toValueObject());
		this.setPresentacionMedicamento(o.getPresentacionMedicamento()
				.toValueObject());

		for (PrincipioActivo pa : o.getPrincipiosActivo()) {
			this.getPrincipiosActivo().add(pa.toValueObject());
		}

		this.setReaccionesAdversas(o.getReaccionesAdversas());
	}

	@Override
	public void setObject(MedicamentoComercial o, int profundidadActual,
			int profundidadDeseada) {

		this.setId(o.getId());
		this.setVersion(o.getVersion());

		this.setNombre(o.getNombre());
		this.setComposicion(o.getComposicion());
		this.setConservacion(o.getConservacion());
		this.setContraindicaciones(o.getContraindicaciones());
		this.setDosificacion(o.getDosificacion());
		this.setIndicaciones(o.getIndicaciones());
		this.setPresentacion(o.getPresentacion());
		this.setReaccionesAdversas(o.getReaccionesAdversas());

		if (profundidadActual < profundidadDeseada) {

			this.setFormaFarmaceutica(o.getFormaFarmaceutica().toValueObject(
					profundidadActual + 1, profundidadDeseada));
			this.setLaboratorio(o.getLaboratorio().toValueObject(
					profundidadActual + 1, profundidadDeseada));
			this.setPresentacionMedicamento(o.getPresentacionMedicamento()
					.toValueObject(profundidadActual + 1, profundidadDeseada));

			for (PrincipioActivo pa : o.getPrincipiosActivo()) {
				this.getPrincipiosActivo().add(
						pa.toValueObject(profundidadActual + 1,
								profundidadDeseada));
			}
		}

	}

	/**
	 * @return the laboratorio
	 */
	public Laboratorio_VO getLaboratorio() {
		return laboratorio;
	}

	/**
	 * @param laboratorio
	 *            the laboratorio to set
	 */
	public void setLaboratorio(Laboratorio_VO laboratorio) {
		this.laboratorio = laboratorio;
	}

	/**
	 * @return the presentacionMedicamento
	 */
	public PresentacionMedicamento_VO getPresentacionMedicamento() {
		return presentacionMedicamento;
	}

	/**
	 * @param presentacionMedicamento
	 *            the presentacionMedicamento to set
	 */
	public void setPresentacionMedicamento(
			PresentacionMedicamento_VO presentacionMedicamento) {
		this.presentacionMedicamento = presentacionMedicamento;
	}

	/**
	 * @return the composicion
	 */
	public String getComposicion() {
		return composicion;
	}

	/**
	 * @param composicion
	 *            the composicion to set
	 */
	public void setComposicion(String composicion) {
		this.composicion = composicion;
	}

	/**
	 * @return the indicaciones
	 */
	public String getIndicaciones() {
		return indicaciones;
	}

	/**
	 * @param indicaciones
	 *            the indicaciones to set
	 */
	public void setIndicaciones(String indicaciones) {
		this.indicaciones = indicaciones;
	}

	/**
	 * @return the dosificacion
	 */
	public String getDosificacion() {
		return dosificacion;
	}

	/**
	 * @param dosificacion
	 *            the dosificacion to set
	 */
	public void setDosificacion(String dosificacion) {
		this.dosificacion = dosificacion;
	}

	/**
	 * @return the contraindicaciones
	 */
	public String getContraindicaciones() {
		return contraindicaciones;
	}

	/**
	 * @param contraindicaciones
	 *            the contraindicaciones to set
	 */
	public void setContraindicaciones(String contraindicaciones) {
		this.contraindicaciones = contraindicaciones;
	}

	/**
	 * @return the reaccionesAdversas
	 */
	public String getReaccionesAdversas() {
		return reaccionesAdversas;
	}

	/**
	 * @param reaccionesAdversas
	 *            the reaccionesAdversas to set
	 */
	public void setReaccionesAdversas(String reaccionesAdversas) {
		this.reaccionesAdversas = reaccionesAdversas;
	}

	/**
	 * @return the presentacion
	 */
	public String getPresentacion() {
		return presentacion;
	}

	/**
	 * @param presentacion
	 *            the presentacion to set
	 */
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	/**
	 * @return the conservacion
	 */
	public String getConservacion() {
		return conservacion;
	}

	/**
	 * @param conservacion
	 *            the conservacion to set
	 */
	public void setConservacion(String conservacion) {
		this.conservacion = conservacion;
	}

	/**
	 * @return the formaFarmaceutica
	 */
	public FormaFarmaceutica_VO getFormaFarmaceutica() {
		return formaFarmaceutica;
	}

	/**
	 * @param formaFarmaceutica
	 *            the formaFarmaceutica to set
	 */
	public void setFormaFarmaceutica(FormaFarmaceutica_VO formaFarmaceutica) {
		this.formaFarmaceutica = formaFarmaceutica;
	}

	/**
	 * @return the principiosActivo
	 */
	public List<PrincipioActivo_VO> getPrincipiosActivo() {
		return principiosActivo;
	}

	/**
	 * @param principiosActivo
	 *            the principiosActivo to set
	 */
	public void setPrincipiosActivo(List<PrincipioActivo_VO> principiosActivo) {
		this.principiosActivo = principiosActivo;
	}

	/**
	 * @return the ventaLibre
	 */
	public Boolean getVentaLibre() {
		return ventaLibre;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String toJson() {
		return new Gson().toJson(this);
	}

	@Override
	public String toString() {
		return this.getNombre() + " (Labo: "
				+ this.getLaboratorio().getNombre() + ", activos: "
				+ this.getPrincipiosActivo().toString() + ", Presentaci�n: "
				+ this.getPresentacionMedicamento().getNombre() + ", Forma: "
				+ this.getFormaFarmaceutica().getNombre() + ")";
	}
}