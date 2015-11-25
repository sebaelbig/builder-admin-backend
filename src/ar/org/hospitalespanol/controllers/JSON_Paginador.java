package ar.org.hospitalespanol.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.org.hospitalespanol.vo.I_ValueObject;

@Component
public class JSON_Paginador {

	private Integer paginaActual = Integer.valueOf(1);
	private Integer cantPorPagina = Integer.valueOf(10);
	private Integer totalPaginas = Integer.valueOf(1);
	private Boolean hayPaginaAnterior = false;
	private Boolean hayPaginaSiguiente = false;
	protected List elementos;
	private String mensaje;

	/*****************************************************************************/
	
	public JSON_Paginador(){}
	
	public JSON_Paginador(List lista) {
		this.setElementos(lista);
	}

	public JSON_Paginador(int cantidadPorDefecto) {
		setCantPorPagina(Integer.valueOf(cantidadPorDefecto));
	}

	/**
	 * 
	 */
	public static JSON_Paginador solo(I_ValueObject valueObject) {
		
		List lista = new ArrayList(); 
		lista.add(valueObject); 
		
		return new JSON_Paginador(lista);
	}
	
	/*****************************************************************************/

	public List getElementos() {
		return this.elementos;
	}

	public void setElementos(List objetos) {
		this.elementos = objetos;
	}

	public Integer getCantidadElementos() {
		return Integer.valueOf(getElementos().size());
	}

	public Integer getCantPorPagina() {
		return this.cantPorPagina;
	}

	public void setCantPorPagina(Integer cantPorPagina) {
		this.cantPorPagina = cantPorPagina;
	}

	public Integer getTotalPaginas() {
		return this.totalPaginas;
	}

	public void setTotalPaginas(Integer totalPaginas) {
		this.totalPaginas = totalPaginas;
	}

	public Integer getPaginaActual() {
		return this.paginaActual;
	}

	public void setPaginaActual(Integer pagina) {
		this.paginaActual = pagina;
	}

	public Boolean getHayPaginaAnterior() {
		return this.hayPaginaAnterior;
	}

	public void setHayPaginaAnterior(Boolean hayPaginaAnterior) {
		this.hayPaginaAnterior = hayPaginaAnterior;
	}

	public Boolean getHayPaginaSiguiente() {
		return this.hayPaginaSiguiente;
	}

	public void setHayPaginaSiguiente(Boolean hayPaginaSiguiente) {
		this.hayPaginaSiguiente = hayPaginaSiguiente;
	}

	public String getMensaje() {
		return this.mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public I_ValueObject getElementoUnico() {
		if (!getElementos().isEmpty()){
			return (I_ValueObject) getElementos().get(0);
		}else{
			return null;
		}
	}

}