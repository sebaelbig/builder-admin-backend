package ar.org.hospitalespanol.vo.core.areas;

import java.util.ArrayList;
import java.util.List;

import ar.org.hospitalespanol.model.core.areas.CirculoDeConfianza;
import ar.org.hospitalespanol.model.core.areas.Sucursal;
import ar.org.hospitalespanol.vo.I_ValueObject;

public class CirculoDeConfianza_VO implements I_ValueObject<CirculoDeConfianza> {

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Integer version;
	private String nombre;
	private String codigo;

	private Boolean modificar = false;
	
	private List<Sucursal_VO> sucursales = new ArrayList<Sucursal_VO>();
	
	public CirculoDeConfianza_VO() {
		setSucursales(new ArrayList<Sucursal_VO>());
	}

	public CirculoDeConfianza_VO(CirculoDeConfianza circulo) {
		setSucursales(new ArrayList<Sucursal_VO>());
		setObject(circulo);
	}

	public CirculoDeConfianza_VO(CirculoDeConfianza circulo, int profundidadActual, int profundidadDeseada) {
		setObject(circulo, profundidadActual, profundidadDeseada);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	public boolean equals(Object objeto) {
		if (objeto instanceof CirculoDeConfianza_VO) {
			CirculoDeConfianza_VO o = (CirculoDeConfianza_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}
	
	@Override
	public String toString(){
		return "("+getCodigo()+") "+getNombre();
	}

	@Override
	public void setObject(CirculoDeConfianza circulo) {
		this.setId(circulo.getId());
		this.setVersion(circulo.getVersion());
		this.setNombre(circulo.getNombre());
		this.setCodigo(circulo.getCodigo());
		this.setModificar(circulo.getModificar());
		
		setSucursales(new ArrayList<Sucursal_VO>());
		if ( circulo.getSucursales() != null){
			
			for (Sucursal s : circulo.getSucursales()){
				this.getSucursales().add(s.toValueObject(I_ValueObject.PROFUNDIDAD_BASE, 0));
			}
			
		}
	}
	
	@Override
	public void setObject(CirculoDeConfianza circulo, int profundidadActual, int profundidadDeseada) {
		
		this.setId(circulo.getId());
		this.setVersion(circulo.getVersion());
		this.setNombre(circulo.getNombre());
		this.setCodigo(circulo.getCodigo());
		this.setModificar(circulo.getModificar());
		
		if (profundidadActual < profundidadDeseada){
			for (Sucursal s : circulo.getSucursales()){
				this.getSucursales().add(s.toValueObject(I_ValueObject.PROFUNDIDAD_BASE, 0));
			}
		}
		
	}

	public CirculoDeConfianza toObject(int profundidadActual, int profundidadDeseada) {
		CirculoDeConfianza circulo =  new CirculoDeConfianza(this.getId(), this.getVersion(), this.getNombre(),
				this.getCodigo());
		
		circulo.setModificar(getModificar());
		
		if (profundidadActual < profundidadDeseada){
			for (Sucursal_VO s : getSucursales()){
				circulo.getSucursales().add(s.toObject(I_ValueObject.PROFUNDIDAD_BASE, 0));
			}
		}
		
		return circulo;
	}

	@Override
	public CirculoDeConfianza toObject() {
		CirculoDeConfianza circulo =  new CirculoDeConfianza(this.getId(), this.getVersion(), this.getNombre(),
				this.getCodigo());
		
		circulo.setModificar(getModificar());
		
		for (Sucursal_VO s : getSucursales()){
			circulo.getSucursales().add(s.toObject(I_ValueObject.PROFUNDIDAD_BASE, 0));
		}
		
		return circulo;
	}
	
	public List<Sucursal_VO> getSucursales() {
		return sucursales;
	}

	public void setSucursales(List<Sucursal_VO> sucursales) {
		this.sucursales = sucursales;
	}

	public Boolean getModificar() {
		return modificar;
	}

	public void setModificar(Boolean modificar) {
		this.modificar = modificar;
	}


}
