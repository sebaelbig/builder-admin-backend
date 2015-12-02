package ar.com.builderadmin.vo.core.obrasSociales;

import ar.com.builderadmin.model.core.obrasSociales.roles.ProductoObraSocialProfesional;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.vo.core.usuarios.roles.profesionales.Profesional_VO;

public class ProductoObraSocialProfesional_VO implements
		I_ValueObject<ProductoObraSocialProfesional> {

	private Long id;
	private Boolean borrado = false;
	private Integer version;

	private Producto_OS_VO producto;
	
	private Profesional_VO profesional;

	private Integer cantidadDeTurnosPorBloqueTurno = 0;

	private Boolean rechazada = false;

	private Boolean borrar = false;

	public ProductoObraSocialProfesional_VO() {
	}

	public ProductoObraSocialProfesional_VO(
			ProductoObraSocialProfesional productoObraSocialProfesional) {
		this.setObject(productoObraSocialProfesional);
	}

	public ProductoObraSocialProfesional_VO(ProductoObraSocialProfesional prod,
			int profundidadActual, int profundidadDeseada) {
		this.setObject(prod, profundidadActual, profundidadDeseada);
	}

	public ProductoObraSocialProfesional_VO(PlanNoAceptado_VO plan) {
		Producto_OS_VO prd = new Producto_OS_VO();
		prd.setCodigo(plan.getCodigo().toString());
		prd.setNombre(plan.getNombre());
	}

	public Producto_OS_VO getProducto() {
		return producto;
	}

	public void setProducto(Producto_OS_VO producto) {
		this.producto = producto;
	}

	@Override
	public Integer getVersion() {
		return version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCantidadDeTurnosPorBloqueTurno() {
		return cantidadDeTurnosPorBloqueTurno;
	}

	public void setCantidadDeTurnosPorBloqueTurno(
			Integer cantidadDeTurnosPorBloqueTurno) {
		this.cantidadDeTurnosPorBloqueTurno = cantidadDeTurnosPorBloqueTurno;
	}

	public Boolean getRechazada() {
		return rechazada;
	}

	public void setRechazada(Boolean rechazada) {
		this.rechazada = rechazada;
	}

	public Profesional_VO getProfesional() {
		return profesional;
	}

	public void setProfesional(Profesional_VO profesional) {
		this.profesional = profesional;
	}

	@Override
	public void setObject(ProductoObraSocialProfesional prod) {

		this.setId(prod.getId());
		this.setVersion(prod.getVersion());
		this.setCantidadDeTurnosPorBloqueTurno(prod
				.getCantidadDeTurnosPorBloqueTurno());
		this.setRechazada(prod.getRechazada());

		this.setProducto(prod.getProductoObraSocial().toValueObject(
				I_ValueObject.PROFUNDIDAD_BASE, 0));
		// Para "atras" es solo simple SIEMPRE
		this.setProfesional(prod.getProfesional().toValueObject(
				I_ValueObject.PROFUNDIDAD_BASE, 0));
	}

	@Override
	public void setObject(ProductoObraSocialProfesional prod,
			int profundidadActual, int profundidadDeseada) {

		this.setId(prod.getId());
		this.setVersion(prod.getVersion());
		this.setCantidadDeTurnosPorBloqueTurno(prod
				.getCantidadDeTurnosPorBloqueTurno());
		this.setRechazada(prod.getRechazada());

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada) {
			this.setProducto(prod.getProductoObraSocial().toValueObject(
					profundidadActual + 1, profundidadDeseada));

			// Para "atras" es solo simple SIEMPRE
			this.setProfesional(prod.getProfesional().toValueObject(
					I_ValueObject.PROFUNDIDAD_BASE, 0));
		}

	}

	public ProductoObraSocialProfesional toObject(int profundidadActual,
			int profundidadDeseada) {

		ProductoObraSocialProfesional resul = new ProductoObraSocialProfesional();
		resul.setId(this.getId());
		resul.setVersion(this.getVersion());

		resul.setRechazada(this.getRechazada());
		resul.setCantidadDeTurnosPorBloqueTurno(this
				.getCantidadDeTurnosPorBloqueTurno());

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual != I_ValueObject.PROFUNDIDAD_BASE
				&& profundidadActual < profundidadDeseada) {

			// Llamada para atras
			if (this.getProfesional() != null)
				resul.setProfesional(this.getProfesional().toObject(
						I_ValueObject.PROFUNDIDAD_BASE, 0));

			if (this.getProducto() != null)
				resul.setProductoObraSocial(this.getProducto().toObject(
						profundidadActual + 1, profundidadDeseada));
		}

		return resul;
	}

	@Override
	public ProductoObraSocialProfesional toObject() {

		ProductoObraSocialProfesional resul = new ProductoObraSocialProfesional();
		resul.setId(this.getId());
		resul.setVersion(this.getVersion());

		resul.setRechazada(this.getRechazada());
		resul.setCantidadDeTurnosPorBloqueTurno(this
				.getCantidadDeTurnosPorBloqueTurno());

		// Llamada para atras
		if (this.getProfesional() != null)
			resul.setProfesional(this.getProfesional().toObject(
					I_ValueObject.PROFUNDIDAD_BASE, 0));

		if (this.getProducto() != null)
			resul.setProductoObraSocial(this.getProducto().toObject());

		return resul;
	}

	public String getNombreObraSocial() {
		return this.getProducto().getNombre();
	}

	@Override
	public String toString() {
		String resul = this.getNombreObraSocial();

		if (this.getRechazada()) {
			resul += " rechazada.";
		} else {
			resul += " limitada a " + this.getCantidadDeTurnosPorBloqueTurno()
					+ " turnos por franja horaria";
		}

		return resul;
	}

	public Boolean getBorrar() {
		return borrar;
	}

	public void setBorrar(Boolean borrar) {
		this.borrar = borrar;
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {

		boolean resul = false;

		if (objeto instanceof ProductoObraSocialProfesional_VO) {
			ProductoObraSocialProfesional_VO o = (ProductoObraSocialProfesional_VO) objeto;

			if (o.getId() == null) {

				// Si no tiene id es porque es nuevo
				resul = this.toString().equalsIgnoreCase(o.toString());

			} else {

				// Si tiene id, comparo por los mismos
				resul = (o.getId().equals(this.getId()));

			}

		}

		return resul;
	}
	
	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado = b;
	}
}
