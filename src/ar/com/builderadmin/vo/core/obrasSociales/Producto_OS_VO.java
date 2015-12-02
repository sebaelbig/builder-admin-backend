package ar.com.builderadmin.vo.core.obrasSociales;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ar.com.builderadmin.model.core.obrasSociales.ContratoDeProducto;
import ar.com.builderadmin.model.core.obrasSociales.EstadoProducto_OS;
import ar.com.builderadmin.model.core.obrasSociales.Producto_OS;
import ar.com.builderadmin.model.core.obrasSociales.TipoCoeficiente;
import ar.com.builderadmin.utils.comparators.core.obrasSociales.EstadoProductoOSComparator;
import ar.com.builderadmin.vo.I_ValueObject;

public class Producto_OS_VO implements I_ValueObject<Producto_OS> {

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}

	private Integer version;

	private String codigo;

	private List<ContratoDeProducto_VO> contratosHistoricos;

	private ContratoDeProducto_VO contratoVigente;

	private String nombre;

	private Long idObraSocial;

	private List<EstadoProducto_OS_VO> estados;

	private Boolean habilitada = true;

	private EstadoProducto_OS_VO ultimoEstado;

	private String motivo;

	private List<TipoCoeficiente_VO> tiposCoeficientes;

	private ObraSocial_VO obraSocial;

	public Producto_OS_VO() {
		setEstados(new ArrayList<EstadoProducto_OS_VO>());
	}
	
	public Producto_OS_VO(String name) {
		setEstados(new ArrayList<EstadoProducto_OS_VO>());
		this.setNombre(name);
	}


	public Producto_OS_VO(Producto_OS producto_OS) {
		setObject(producto_OS);
	}

	public Producto_OS_VO(Producto_OS objeto, int profundidadActual,
			int profundidadDeseada) {

		setCodigo(objeto.getCodigo());
		setId(objeto.getId());
		setNombre(objeto.getNombre());
		setVersion(objeto.getVersion());

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada) {

			if (objeto.getContratoVigente() != null) {
				setContratoVigente(objeto.getContratoVigente().toValueObject(
						profundidadActual + 1, profundidadDeseada));
			}

			if (objeto.getContratosHistoricos() != null) {

				List<ContratoDeProducto_VO> contratos = new ArrayList<ContratoDeProducto_VO>();

				for (ContratoDeProducto contrato : objeto
						.getContratosHistoricos()) {
					contratos.add(contrato.toValueObject());
				}

				setContratosHistoricos(contratos);
			}

		}

	}

	@Override
	public void setObject(Producto_OS objeto) {
		setCodigo(objeto.getCodigo());
		setId(objeto.getId());
		setNombre(objeto.getNombre());
		setVersion(objeto.getVersion());

		if (objeto.getContratoVigente() != null) {
			setContratoVigente(objeto.getContratoVigente().toValueObject());
		}

		if (objeto.getContratosHistoricos() != null) {

			List<ContratoDeProducto_VO> contratos = new ArrayList<ContratoDeProducto_VO>();

			for (ContratoDeProducto contrato : objeto.getContratosHistoricos()) {
				contratos.add(contrato.toValueObject());
			}

			setContratosHistoricos(contratos);
		}

		this.setMotivo("Ingrese el motivo de suspensi�n.");

		List<EstadoProducto_OS_VO> estados = new ArrayList<EstadoProducto_OS_VO>();
		for (EstadoProducto_OS estadoProductoOS : objeto.getEstados()) {
			estados.add(estadoProductoOS.toValueObject());
		}

		// setEstados(estados);

		if (estados.size() > 0) {

			Collections.sort(estados, new EstadoProductoOSComparator());

			setUltimoEstado(estados.get(estados.size() - 1));

			if (getUltimoEstado().habilitada()) {
				setHabilitada(true);
			} else {
				this.setMotivo(((Producto_OSSuspendido_VO) this
						.getUltimoEstado()).getMotivo());
				setHabilitada(false);
			}
		}

		setTiposCoeficientes(new ArrayList<TipoCoeficiente_VO>());

		for (TipoCoeficiente tipo : objeto.getTiposCoeficientes()) {

			getTiposCoeficientes().add(tipo.toValueObject());
		}

		if (objeto.getObraSocial() != null) {
			this.setIdObraSocial(objeto.getObraSocial().getId());

			this.setObraSocial(objeto.getObraSocial().toValueObject());
		}
	}

	public List<TipoCoeficiente_VO> getTiposCoeficientes() {
		return tiposCoeficientes;
	}

	public void setTiposCoeficientes(List<TipoCoeficiente_VO> tiposCoeficientes) {
		this.tiposCoeficientes = tiposCoeficientes;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public void setIdObraSocial(Long idObraSocial) {
		this.idObraSocial = idObraSocial;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<ContratoDeProducto_VO> getContratosHistoricos() {
		return contratosHistoricos;
	}

	public void setContratosHistoricos(
			List<ContratoDeProducto_VO> contratosHistoricos) {
		this.contratosHistoricos = contratosHistoricos;
	}

	public ContratoDeProducto_VO getContratoVigente() {
		return contratoVigente;
	}

	public void setContratoVigente(ContratoDeProducto_VO contratoVigente) {
		this.contratoVigente = contratoVigente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Long getIdObraSocial() {
		return this.idObraSocial;
	}

	@Override
	public Long getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	@Override
	public String toString() {
		return this.getCodigo() + ", " + this.getNombre();
	}

	public List<EstadoProducto_OS_VO> getEstados() {
		return estados;
	}

	public void setEstados(List<EstadoProducto_OS_VO> estados) {
		this.estados = estados;
	}

	public Boolean getHabilitada() {
		return habilitada;
	}

	public void setHabilitada(Boolean habilitada) {
		this.habilitada = habilitada;
	}

	public EstadoProducto_OS_VO getUltimoEstado() {
		return ultimoEstado;
	}

	public void setUltimoEstado(EstadoProducto_OS_VO ultimoEstado) {
		this.ultimoEstado = ultimoEstado;
	}

	@Override
	public void setObject(Producto_OS objeto, int profundidadActual,
			int profundidadDeseada) {
		// TODO Auto-generated method stub

	}

	public Producto_OS toObject(int profundidadActual, int profundidadDeseada) {

		Producto_OS producto = new Producto_OS(getId(), getVersion(),
				getCodigo(), getNombre());

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual != I_ValueObject.PROFUNDIDAD_BASE
				&& profundidadActual < profundidadDeseada) {

			if (getContratoVigente() != null)
				producto.setContratoVigente(this.getContratoVigente().toObject(
						profundidadActual + 1, profundidadDeseada));

			if (this.getContratosHistoricos() != null) {

				for (ContratoDeProducto_VO contrato : getContratosHistoricos()) {

					ContratoDeProducto cont = contrato.toObject(
							profundidadActual + 1, profundidadDeseada);
					cont.setProducto(producto);
					producto.getContratosHistoricos().add(cont);
				}

			}

			if (getEstados() != null) {
				for (EstadoProducto_OS_VO estado : getEstados()) {

					EstadoProducto_OS est = estado.toObject(
							profundidadActual + 1, profundidadDeseada);
					est.setProducto(producto);
					producto.getEstados().add(est);

				}
			}

			producto.setTiposCoeficientes(new ArrayList<TipoCoeficiente>());

			if (getTiposCoeficientes() != null) {
				for (TipoCoeficiente_VO tipo : getTiposCoeficientes()) {
					producto.getTiposCoeficientes().add(tipo.toObject());
				}
			}
		}

		return producto;

	}

	@Override
	public Producto_OS toObject() {

		Producto_OS producto = new Producto_OS(getId(), getVersion(),
				getCodigo(), getNombre());

		if (getContratoVigente() != null) {
			producto.setContratoVigente(this.getContratoVigente().toObject());
		}

		if (this.getContratosHistoricos() != null) {
			for (ContratoDeProducto_VO contrato : getContratosHistoricos()) {
				ContratoDeProducto cont = contrato.toObject();
				cont.setProducto(producto);
				producto.getContratosHistoricos().add(cont);
			}
		}

		if (getEstados() != null) {
			for (EstadoProducto_OS_VO estado : getEstados()) {
				EstadoProducto_OS est = estado.toObject();
				est.setProducto(producto);
				producto.getEstados().add(est);
			}
		}

		producto.setTiposCoeficientes(new ArrayList<TipoCoeficiente>());

		if (getTiposCoeficientes() != null) {
			for (TipoCoeficiente_VO tipo : getTiposCoeficientes()) {
				producto.getTiposCoeficientes().add(tipo.toObject());
			}
		}

		return producto;
	}

	public ObraSocial_VO getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(ObraSocial_VO obraSocial) {
		this.obraSocial = obraSocial;
	}

}