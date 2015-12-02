package ar.com.builderadmin.model;

import java.math.BigDecimal;
import java.util.Date;

import ar.com.builderadmin.model.core.areas.servicios.Servicio;

public interface I_Consumo {

	String PRESTACION = "Prestaci�n";
	String TURNO = "Turno";
	
	public Long getIdConsumo();
	
	public String getClaseConsumo();
	
	public String getDescripcion();
	public void setDescripcion(String descripcionNueva);
	
	public String getNombre();
	public void setNombre(String nombreNuevo);
	
	public Date getFechaPrecio();
	public void setFechaPrecio(Date nuevaFechaPrecio);
	
	public Float getIVA();
	public void setIVA(Float nuevoIVA);
	
	public Integer getCantidad();
	public void setCantidad(Integer cantidadNueva);
	
	public BigDecimal getPrecioUnitario();
	public void setPrecioUnitario(BigDecimal precioNuevo);
	
	public Servicio getServicio();
	public void setServicio(Servicio servicio);
	
}