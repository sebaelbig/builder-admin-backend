package ar.com.builderadmin.vo.core.obrasSociales;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ar.com.builderadmin.model.core.obrasSociales.EstadoObraSocial;
import ar.com.builderadmin.model.core.obrasSociales.ObraSocial;
import ar.com.builderadmin.utils.comparators.core.obrasSociales.EstadoObraSocialComparator;
import ar.com.builderadmin.vo.I_ValueObject;

public class ObraSocial_VO implements I_ValueObject<ObraSocial> {

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}

	private Integer version;

	private String codigo;

	private String cuit;

	private String denominacionProducto;

	private String denominacionSubproducto;

	private String descripcion;

	private String direccion;

	private Boolean liquidaHonorariosAmbulatorio;

	private Boolean liquidaHonorariosInternacion;

	private String localidad;

	private Boolean modulada;

	private String nombre;

	private Integer nroGanancias;

	private Integer nroInscripcion;

	private String telefono;

	private List<EstadoObraSocial_VO> estados;

	private Boolean habilitada = true;

	private EstadoObraSocial_VO ultimoEstado;

	public ObraSocial_VO(ObraSocial obraSocial) {
		setObject(obraSocial);
	}

	public ObraSocial_VO() {
		setEstados(new ArrayList<EstadoObraSocial_VO>());
	}

	@Override
	public void setObject(ObraSocial objeto) {
		setCodigo(objeto.getCodigo());
		setCuit(objeto.getCuit());
		setDenominacionProducto(objeto.getDenominacionProducto());
		setDenominacionSubproducto(objeto.getDenominacionSubproducto());
		setDescripcion(objeto.getDescripcion());
		setDireccion(objeto.getDireccion());
		setId(objeto.getId());
		setLiquidaHonorariosAmbulatorio(objeto
				.getLiquidaHonorariosAmbulatorio());
		setLiquidaHonorariosInternacion(objeto
				.getLiquidaHonorariosInternacion());
		setLocalidad(objeto.getLocalidad());
		setModulada(objeto.getModulada());
		setNombre(objeto.getNombre());
		setNroGanancias(objeto.getNroGanancias());
		setNroInscripcion(objeto.getNroInscripcion());
		setTelefono(objeto.getTelefono());
		setVersion(objeto.getVersion());

		List<EstadoObraSocial_VO> estados = new ArrayList<EstadoObraSocial_VO>();

		for (EstadoObraSocial estadoObraSocial : objeto.getEstados()) {
			estados.add(estadoObraSocial.toValueObject());
		}

		setEstados(estados);

		if (estados.size() > 0) {

			Collections.sort(getEstados(), new EstadoObraSocialComparator());

			setUltimoEstado(estados.get(estados.size() - 1));

			setHabilitada(getUltimoEstado().habilitada());
		}
	}

	@Override
	public void setObject(ObraSocial objeto, int profundidadActual,
			int profundidadDeseada) {
		setCodigo(objeto.getCodigo());
		setCuit(objeto.getCuit());
		setDenominacionProducto(objeto.getDenominacionProducto());
		setDenominacionSubproducto(objeto.getDenominacionSubproducto());
		setDescripcion(objeto.getDescripcion());
		setDireccion(objeto.getDireccion());
		setId(objeto.getId());
		setLiquidaHonorariosAmbulatorio(objeto
				.getLiquidaHonorariosAmbulatorio());
		setLiquidaHonorariosInternacion(objeto
				.getLiquidaHonorariosInternacion());
		setLocalidad(objeto.getLocalidad());
		setModulada(objeto.getModulada());
		setNombre(objeto.getNombre());
		setNroGanancias(objeto.getNroGanancias());
		setNroInscripcion(objeto.getNroInscripcion());
		setTelefono(objeto.getTelefono());
		setVersion(objeto.getVersion());

		List<EstadoObraSocial_VO> estados = new ArrayList<EstadoObraSocial_VO>();

		if (profundidadActual < profundidadDeseada) {

			for (EstadoObraSocial estadoObraSocial : objeto.getEstados()) {
				estados.add(estadoObraSocial.toValueObject(
						profundidadActual + 1, profundidadDeseada));
			}

			setEstados(estados);

			if (estados.size() > 0) {

				Collections
						.sort(getEstados(), new EstadoObraSocialComparator());

				setUltimoEstado(estados.get(estados.size() - 1));

				setHabilitada(getUltimoEstado().habilitada());
			}
		}

	}

	public ObraSocial toObject(int profundidadActual, int profundidadDeseada) {
		ObraSocial obraSocial = new ObraSocial(getId(), getVersion(),
				getCodigo(), getCuit(), getDenominacionProducto(),
				getDenominacionSubproducto(), getDescripcion(), getDireccion(),
				getLiquidaHonorariosAmbulatorio(),
				getLiquidaHonorariosInternacion(), getLocalidad(),
				getModulada(), getNombre(), getNroGanancias(),
				getNroInscripcion(), getTelefono());

		if (profundidadActual < profundidadDeseada) {

			for (EstadoObraSocial_VO estado : this.getEstados()) {
				EstadoObraSocial est = estado.toObject(profundidadActual + 1,
						profundidadDeseada);
				est.setObraSocial(obraSocial);
				obraSocial.getEstados().add(est);
			}

		}

		return obraSocial;
	}

	@Override
	public ObraSocial toObject() {
		ObraSocial obraSocial = new ObraSocial(getId(), getVersion(),
				getCodigo(), getCuit(), getDenominacionProducto(),
				getDenominacionSubproducto(), getDescripcion(), getDireccion(),
				getLiquidaHonorariosAmbulatorio(),
				getLiquidaHonorariosInternacion(), getLocalidad(),
				getModulada(), getNombre(), getNroGanancias(),
				getNroInscripcion(), getTelefono());

		for (EstadoObraSocial_VO estado : getEstados()) {
			EstadoObraSocial est = estado.toObject();
			est.setObraSocial(obraSocial);
			obraSocial.getEstados().add(est);
		}

		return obraSocial;
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof ObraSocial_VO) {
			ObraSocial_VO o = (ObraSocial_VO) objeto;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getDenominacionProducto() {
		return denominacionProducto;
	}

	public void setDenominacionProducto(String denominacionProducto) {
		this.denominacionProducto = denominacionProducto;
	}

	public String getDenominacionSubproducto() {
		return denominacionSubproducto;
	}

	public void setDenominacionSubproducto(String denominacionSubproducto) {
		this.denominacionSubproducto = denominacionSubproducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Boolean getLiquidaHonorariosAmbulatorio() {
		return liquidaHonorariosAmbulatorio;
	}

	public void setLiquidaHonorariosAmbulatorio(
			Boolean liquidaHonorariosAmbulatorio) {
		this.liquidaHonorariosAmbulatorio = liquidaHonorariosAmbulatorio;
	}

	public Boolean getLiquidaHonorariosInternacion() {
		return liquidaHonorariosInternacion;
	}

	public void setLiquidaHonorariosInternacion(
			Boolean liquidaHonorariosInternacion) {
		this.liquidaHonorariosInternacion = liquidaHonorariosInternacion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public Boolean getModulada() {
		return modulada;
	}

	public void setModulada(Boolean modulada) {
		this.modulada = modulada;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNroGanancias() {
		return nroGanancias;
	}

	public void setNroGanancias(Integer nroGanancias) {
		this.nroGanancias = nroGanancias;
	}

	public Integer getNroInscripcion() {
		return nroInscripcion;
	}

	public void setNroInscripcion(Integer nroInscripcion) {
		this.nroInscripcion = nroInscripcion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<EstadoObraSocial_VO> getEstados() {
		return estados;
	}

	public void setEstados(List<EstadoObraSocial_VO> estados) {
		this.estados = estados;
	}

	public Boolean getHabilitada() {
		return habilitada;
	}

	public void setHabilitada(Boolean habilitada) {
		this.habilitada = habilitada;
	}

	public EstadoObraSocial_VO getUltimoEstado() {
		return ultimoEstado;
	}

	public void setUltimoEstado(EstadoObraSocial_VO ultimoEstado) {
		this.ultimoEstado = ultimoEstado;
	}

	@Override
	public String toString() {
		return this.getNombre() + " (" + getCodigo() + ")";
	}

}
