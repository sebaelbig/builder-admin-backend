package ar.com.builderadmin.vo.core.usuarios.fichaDeConsumo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import ar.com.builderadmin.model.core.usuarios.fichaDeConsumo.ItemFichaDeConsumo;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.vo.core.areas.servicios.Servicio_VO;
import ar.com.builderadmin.vo.core.usuarios.fichaDeConsumo.estados.EstadoItemFichaDeConsumo_VO;
import ar.com.builderadmin.vo.core.usuarios.fichaDeConsumo.estados.ItemFichaDeConsumo_SinRendir_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.Rol_VO;

public class ItemFichaDeConsumo_VO implements I_ValueObject<ItemFichaDeConsumo> {

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}

	private Integer version;

	/**
	 * Fecha en la que se realizo el consuo
	 */
	private Date fecha;
	private String str_fecha;

	/**
	 * Id de la clase que implementa I_Consumo
	 */
	private Long idConsumo;

	/**
	 * Canonical Name de la clase que implementa I_Consumo
	 */
	private String claseConsumo;

	/**
	 * Nombre del consumo
	 */
	private String nombre;

	/**
	 * Descripcion del consumo
	 */
	private String descripcion;

	/**
	 * Rol que posee la FX_Crear/ModificarItemFichaDeConsumo y que registro este
	 * consumo
	 */
	private Rol_VO rolRegistro;

	/**
	 * Estado en el que se encuentra este item
	 */
	private EstadoItemFichaDeConsumo_VO estado;

	/**
	 * Ficha a la cual pertenece este item
	 */
	private Long idFichaDeConsumo;
	private FichaDeConsumo_VO fichaDeConsumo;

	/**
	 * Fecha en la cual era actual el precio unitario
	 */
	private Date fechaPrecio;

	/**
	 * Precio unitario del item
	 */
	private BigDecimal precioUnitario;

	/**
	 * Cantidad del item
	 */
	private Integer cantidad;

	/**
	 * El iva que se le debe aplica a este consumo
	 */
	private Float iva;

	private Servicio_VO servicio;

	public ItemFichaDeConsumo_VO(ItemFichaDeConsumo item) {
		this.setObject(item);
	}

	public ItemFichaDeConsumo_VO(ItemFichaDeConsumo item,
			int profundidadActual, int profundidadDeseada) {
		this.setObject(item, profundidadActual, profundidadDeseada);
	}

	public ItemFichaDeConsumo_VO() {
		this.setEstado(new ItemFichaDeConsumo_SinRendir_VO());
	}

	@Override
	public ItemFichaDeConsumo toObject() {
		ItemFichaDeConsumo resul = new ItemFichaDeConsumo();

		resul.setId(this.getId());
		resul.setVersion(this.getVersion());

		resul.setCantidad(this.getCantidad());
		resul.setClaseConsumo(this.getClaseConsumo());
		resul.setDescripcion(this.getDescripcion());
		resul.setEstado(this.getEstado().toObject());
		resul.setFecha(this.getFecha());
		resul.setFechaPrecio(this.getFechaPrecio());
		resul.setIdConsumo(this.getIdConsumo());
		resul.setIVA(this.getIva());
		resul.setNombre(this.getNombre());
		resul.setPrecioUnitario(this.getPrecioUnitario());
		resul.setRolRegistro(this.getRolRegistro().toObject());

		return resul;
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof ItemFichaDeConsumo_VO) {
			ItemFichaDeConsumo_VO o = (ItemFichaDeConsumo_VO) objeto;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
		if (fecha != null) {
			this.setStr_fecha(new SimpleDateFormat("dd/MM/yyyy HH:mm")
					.format(fecha));
		}
	}

	public String getStr_fecha() {
		return str_fecha;
	}

	public void setStr_fecha(String str_fecha) {
		this.str_fecha = str_fecha;
	}

	public Long getIdConsumo() {
		return idConsumo;
	}

	public void setIdConsumo(Long idConsumo) {
		this.idConsumo = idConsumo;
	}

	public String getClaseConsumo() {
		return claseConsumo;
	}

	public void setClaseConsumo(String claseConsumo) {
		this.claseConsumo = claseConsumo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Rol_VO getRolRegistro() {
		return rolRegistro;
	}

	public void setRolRegistro(Rol_VO rolRegistro) {
		this.rolRegistro = rolRegistro;
	}

	public Date getFechaPrecio() {
		return fechaPrecio;
	}

	public void setFechaPrecio(Date fechaPrecio) {
		this.fechaPrecio = fechaPrecio;
	}

	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Float getIva() {
		return iva;
	}

	public void setIva(Float iva) {
		this.iva = iva;
	}

	public EstadoItemFichaDeConsumo_VO getEstado() {
		return estado;
	}

	public void setEstado(EstadoItemFichaDeConsumo_VO estado) {
		this.estado = estado;
	}

	public Long getIdFichaDeConsumo() {
		// return idFichaDeConsumo;
		return this.getFichaDeConsumo().getId();
	}

	public void setIdFichaDeConsumo(Long idFichaDeConsumo) {
		this.idFichaDeConsumo = idFichaDeConsumo;
	}

	public Servicio_VO getServicio() {
		return servicio;
	}

	public void setServicio(Servicio_VO servicio) {
		this.servicio = servicio;
	}

	public FichaDeConsumo_VO getFichaDeConsumo() {
		return fichaDeConsumo;
	}

	public void setFichaDeConsumo(FichaDeConsumo_VO fichaDeConsumo) {
		this.fichaDeConsumo = fichaDeConsumo;
	}

	@Override
	public void setObject(ItemFichaDeConsumo item) {
		this.setIdConsumo(item.getIdConsumo());
		this.setClaseConsumo(item.getClaseConsumo());
		this.setNombre(item.getNombre());
		this.setDescripcion(item.getDescripcion());
		this.setFecha(item.getFecha());
		this.setFechaPrecio(item.getFecha());
		this.setIdFichaDeConsumo(item.getFichaDeConsumo().getId());

		this.setCantidad(item.getCantidad());
		this.setPrecioUnitario(item.getPrecioUnitario());
		this.setIva(item.getIVA());
		this.setRolRegistro(item.getRolRegistro().toValueObject());

		this.setVersion(item.getVersion());
		this.setId(item.getId());

		this.setEstado(item.getEstado().toValueObject());
		this.setServicio(item.getServicio().toValueObject());
	}

	@Override
	public void setObject(ItemFichaDeConsumo item, int profundidadActual,
			int profundidadDeseada) {

		this.setIdConsumo(item.getIdConsumo());
		this.setClaseConsumo(item.getClaseConsumo());
		this.setNombre(item.getNombre());
		this.setDescripcion(item.getDescripcion());
		this.setFecha(item.getFecha());
		this.setFechaPrecio(item.getFecha());
		this.setCantidad(item.getCantidad());
		this.setPrecioUnitario(item.getPrecioUnitario());
		this.setIva(item.getIVA());

		this.setVersion(item.getVersion());
		this.setId(item.getId());

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual != I_ValueObject.PROFUNDIDAD_BASE
				&& profundidadActual < profundidadDeseada) {

			// Como es una relacion para atras, solo se convierte hasta el nivel
			// 0
			this.setFichaDeConsumo(item.getFichaDeConsumo().toValueObject(
					I_ValueObject.PROFUNDIDAD_BASE, 0));
			// this.setIdFichaDeConsumo(item.getFichaDeConsumo().getId());

			this.setRolRegistro(item.getRolRegistro().toValueObject(
					profundidadActual + 1, profundidadDeseada));

			this.setEstado(item.getEstado().toValueObject());
			this.setServicio(item.getServicio().toValueObject());

		}
	}

	public ItemFichaDeConsumo toObject(int profundidadActual,
			int profundidadDeseada) {

		ItemFichaDeConsumo resul = new ItemFichaDeConsumo();

		resul.setId(this.getId());
		resul.setVersion(this.getVersion());

		resul.setCantidad(this.getCantidad());
		resul.setClaseConsumo(this.getClaseConsumo());
		resul.setDescripcion(this.getDescripcion());
		resul.setFecha(this.getFecha());
		resul.setFechaPrecio(this.getFechaPrecio());
		resul.setIdConsumo(this.getIdConsumo());
		resul.setIVA(this.getIva());
		resul.setNombre(this.getNombre());
		resul.setPrecioUnitario(this.getPrecioUnitario());

		if (profundidadActual != I_ValueObject.PROFUNDIDAD_BASE
				&& profundidadActual < profundidadDeseada) {

			resul.setEstado(this.getEstado().toObject(profundidadActual + 1,
					profundidadDeseada));
			resul.setRolRegistro(this.getRolRegistro().toObject(
					profundidadActual + 1, profundidadDeseada));

			// Como es una relacion para atras, solo se convierte hasta el nivel
			// 0
			resul.setFichaDeConsumo(this.getFichaDeConsumo().toObject(
					I_ValueObject.PROFUNDIDAD_BASE, 0));

		}

		return resul;
	}

}
