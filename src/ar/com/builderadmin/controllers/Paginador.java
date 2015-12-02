package ar.com.builderadmin.controllers;

import java.util.List;

import org.springframework.stereotype.Component;

import ar.com.builderadmin.dao.DAO;

@Component
public class Paginador<I_ValueObject> {
	
	private Integer cantPorPagina = Integer.valueOf(10);
	private Integer paginaActual = Integer.valueOf(0);
	private Integer totalPaginas;
	private String mensaje;
	protected List<I_ValueObject> elementos;
	private Integer totalElementos;

	public Paginador() {
		this.totalPaginas = Integer.valueOf(0);
	}

	public List<I_ValueObject> listar(DAO<I_ValueObject> dao) {
		setElementos(dao.listar(getPaginaActual(), getCantPorPagina()));
		setTotalElementos(dao.contarTotal());
		if (dao.getResp_listar() != null) {
			setMensaje(dao.getResp_listar().getMensaje());
		} else if (dao.contarTotal().intValue() == 0) {
			setMensaje("No se han encontrado elementos para listar.");
		}
		calcularTotalDePaginas();

		return getElementos();
	}

	protected void calcularTotalDePaginas() {
		Integer totalPaginas = Integer.valueOf(getTotalElementos().intValue()
				/ getCantPorPagina().intValue());
		if (getTotalElementos().intValue() % getCantPorPagina().intValue() != 0) {
			totalPaginas = Integer.valueOf(totalPaginas.intValue() + 1);
		}
		setTotalPaginas(totalPaginas);
	}

	public void iniciar(Integer pagina, Integer cantidad) {
		this.paginaActual = pagina;
		this.cantPorPagina = cantidad;
	}

	public Boolean getHayAnterior() {
		if (this.paginaActual.intValue() > 1) {
			return Boolean.valueOf(true);
		}
		return Boolean.valueOf(false);
	}

	public Boolean getHaySiguiente() {
		if (this.paginaActual.intValue() < this.totalPaginas.intValue()) {
			return Boolean.valueOf(true);
		}
		return Boolean.valueOf(false);
	}

	public Integer getCantPorPagina() {
		return this.cantPorPagina;
	}

	public void setCantPorPagina(Integer cantPorPagina) {
		this.cantPorPagina = cantPorPagina;
	}

	public Integer getPaginaActual() {
		return this.paginaActual;
	}

	public void setPaginaActual(Integer paginaActual) {
		this.paginaActual = paginaActual;
	}

	public Integer getTotalPaginas() {
		return this.totalPaginas;
	}

	public void setTotalPaginas(Integer totalPaginas) {
		this.totalPaginas = totalPaginas;
	}

	public List<I_ValueObject> getElementos() {
		return this.elementos;
	}

	public void setElementos(List<I_ValueObject> elementos) {
		this.elementos = elementos;
	}

	public Integer getTotalElementos() {
		return this.totalElementos;
	}

	public void setTotalElementos(Integer totalElementos) {
		this.totalElementos = totalElementos;
	}

	public String getMensaje() {
		return this.mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}