package ar.org.hospitalespanol.vo.core.obrasSociales;

import ar.org.hospitalespanol.model.core.obrasSociales.roles.ProductoObraSocialPaciente;
import ar.org.hospitalespanol.model.core.usuarios.roles.pacientes.Paciente;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.core.usuarios.roles.pacientes.Paciente_VO;

public class ProductoObraSocialPaciente_VO implements
		I_ValueObject<ProductoObraSocialPaciente> {

	private Producto_OS_VO producto;
	private Integer version;
	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private String nroAfiliado;
	private Boolean activa = Boolean.valueOf(true);
	private Long idPaciente;
	private Paciente_VO paciente;

	public ProductoObraSocialPaciente_VO() {
	}

	public ProductoObraSocialPaciente_VO(String nroAfiliado2,
			String nombreProducto) {
		setProducto(new Producto_OS_VO(nombreProducto));
	}

	public Producto_OS_VO getProducto() {
		return this.producto;
	}

	public void setProducto(Producto_OS_VO producto) {
		this.producto = producto;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNroAfiliado() {
		return this.nroAfiliado;
	}

	public void setNroAfiliado(String nroAfiliado) {
		this.nroAfiliado = nroAfiliado;
	}

	public Boolean getActiva() {
		return this.activa;
	}

	public void setActiva(Boolean activa) {
		this.activa = activa;
	}

	public Long getIdPaciente() {
		return this.idPaciente;
	}

	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}

	public Paciente_VO getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente_VO paciente) {
		this.paciente = paciente;
	}

	@Override
	public String toString() {
		return getProducto().getNombre();
	}

	@Override
	public boolean equals(Object o) {
		ProductoObraSocialPaciente_VO temp = null;
		boolean resul = false;
		if ((o instanceof ProductoObraSocialPaciente_VO)) {
			temp = (ProductoObraSocialPaciente_VO) o;

			resul = temp.getProducto().getId().equals(getProducto().getId());
		}
		return resul;
	}
	
	public ProductoObraSocialPaciente_VO(ProductoObraSocialPaciente productoObraSocialPaciente) {
		this.setObject(productoObraSocialPaciente);
	}

	public ProductoObraSocialPaciente_VO( ProductoObraSocialPaciente productoObraSocialPaciente, int profundidadActual, int profundidadDeseada) {
		this.setObject(productoObraSocialPaciente, profundidadActual, profundidadDeseada);
	}

	@Override
	public void setObject(ProductoObraSocialPaciente prod) {
		
		this.setId(prod.getId());
		this.setVersion(prod.getVersion());
		
//		Para el paciente, solo me interesa el producto no los contratos
//		prod.getProductoObraSocial().getContratosHistoricos().clear();
//		prod.getProductoObraSocial().setContratoVigente(null);
//		prod.getProductoObraSocial().getTiposCoeficientes().clear();
//		prod.getProductoObraSocial().getEstados().clear();

		//Si estamos transformando un producto de paciente, no necesitamos mas que el producto con un nivel
		this.setProducto( prod.getProductoObraSocial().toValueObject(I_ValueObject.PROFUNDIDAD_BASE, 2));
		this.setActiva(prod.getActiva());
		this.setNroAfiliado(prod.getNroAfiliado());
		
		this.setIdPaciente(prod.getPaciente().getId());
	}

	@Override
	public ProductoObraSocialPaciente toObject() {
		
		ProductoObraSocialPaciente resul = new ProductoObraSocialPaciente();
		resul.setId(this.getId());
		resul.setVersion(this.getVersion());
		resul.setActiva(this.getActiva());
		resul.setNroAfiliado(this.getNroAfiliado());
		
		if (this.getIdPaciente()!=null){
			
			Paciente pac = new Paciente();
			pac.setId(this.getIdPaciente());
			resul.setPaciente(pac);
			
		}
		
		if (this.getProducto() != null){
			resul.setProductoObraSocial(this.getProducto().toObject());
		}
		
		return resul;
	}
	
	@Override
	public void setObject(ProductoObraSocialPaciente prod, int profundidadActual, int profundidadDeseada) {
		
		this.setId(prod.getId());
		this.setVersion(prod.getVersion());
		
		//Para el paciente, solo me interesa el producto no los contratos
//		prod.getProductoObraSocial().getContratosHistoricos().clear();
//		prod.getProductoObraSocial().setContratoVigente(null);
//		prod.getProductoObraSocial().getTiposCoeficientes().clear();
//		prod.getProductoObraSocial().getEstados().clear();
		
		this.setActiva(prod.getActiva());
		this.setNroAfiliado(prod.getNroAfiliado());

		if(prod.getPaciente()!=null)
			this.setIdPaciente(prod.getPaciente().getId());

		//Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada  ){
			this.setProducto(prod.getProductoObraSocial().toValueObject(profundidadActual+1, profundidadDeseada ));
		}
		
	}
}