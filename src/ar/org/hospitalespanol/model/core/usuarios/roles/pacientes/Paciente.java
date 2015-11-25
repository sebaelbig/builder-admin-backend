package ar.org.hospitalespanol.model.core.usuarios.roles.pacientes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

import ar.org.hospitalespanol.model.core.obrasSociales.roles.ProductoObraSocialPaciente;
import ar.org.hospitalespanol.model.core.usuarios.Usuario;
import ar.org.hospitalespanol.model.core.usuarios.roles.Rol;
import ar.org.hospitalespanol.model.historiaClinica.HistoriaClinica;
import ar.org.hospitalespanol.vo.core.usuarios.roles.pacientes.Paciente_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:12 a.m.
 */
@Entity 
@DiscriminatorValue("rol_paciente")
public class Paciente extends Rol implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Historias clinicas del paciente
	 * 
	 * 1 Historia clinica para cada
	 * 
	 * @OneToMany(mappedBy="paciente", cascade=CascadeType.ALL) private
	 *                                 List<HistoriaClinica> historiasClinicas;
	 */

	/**
	 * Obras sociales del paciente
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente")
	@JoinFetch(JoinFetchType.OUTER)
	private List<ProductoObraSocialPaciente> productos_OSPaciente = new ArrayList<ProductoObraSocialPaciente>();

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_historia_clinica")
	@JoinFetch(JoinFetchType.INNER)
	private HistoriaClinica historiaClinica;

	// Constructores
	public Paciente() {
		super(new Usuario());
		setNombre(Rol.PACIENTE);
		setCodigo(Rol.PACIENTE);
	}

//	public Paciente(Usuario usuarioActual) {
//		super(usuarioActual);
//		setNombre(Rol.PACIENTE);
//		setCodigo(Rol.PACIENTE);
//	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Paciente) {
			Paciente o = (Paciente) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public List<ProductoObraSocialPaciente> getProductos_OSPaciente() {
		return productos_OSPaciente;
	}

	public void setProductos_OSPaciente(
			List<ProductoObraSocialPaciente> productos_OSPaciente) {
		this.productos_OSPaciente = productos_OSPaciente;
	}

	public void agregarProducto_OSAceptada(
			ProductoObraSocialPaciente obraSocialNueva) {
		obraSocialNueva.setPaciente(this);
		getProductos_OSPaciente().add(obraSocialNueva);
	}

	public void eliminarObraSocial(ProductoObraSocialPaciente obra) {
		getProductos_OSPaciente().remove(obra);
	}

	public void eliminarObraSocial(Integer indiceObra) {
		getProductos_OSPaciente().remove(indiceObra.intValue());
	}

	public void activarObraSocial(ProductoObraSocialPaciente obra) {
		getProductos_OSPaciente().get(getProductos_OSPaciente().indexOf(obra))
				.setActiva(true);
	}

	public void activarObraSocial(Integer indiceObra) {
		getProductos_OSPaciente().get(indiceObra).setActiva(true);
	}

	public void suspenderObraSocial(ProductoObraSocialPaciente obra) {
		getProductos_OSPaciente().get(getProductos_OSPaciente().indexOf(obra))
				.setActiva(false);
	}

	public void suspenderObraSocial(Integer indiceObra) {
		getProductos_OSPaciente().get(indiceObra).setActiva(false);
	}

	public boolean yaTieneObraSocial(String nombreOS) {
		boolean resul = false;
		Iterator<ProductoObraSocialPaciente> oss = getProductos_OSPaciente()
				.iterator();

		while ((!resul) && (oss.hasNext())) {
			resul = oss.next().getProductoObraSocial().getNombre()
					.equals(nombreOS);
		}

		return resul;
	}

//	public boolean esProvisional() {
//		return this.getUsuario().getNroDocumento().startsWith("P");
//	}

	public HistoriaClinica getHistoriaClinica() {
		return historiaClinica;
	}

	public void setHistoriaClinica(HistoriaClinica historiaClinica) {
		this.historiaClinica = historiaClinica;
	}

	@Override
	public Paciente_VO toValueObject() {
//		return new Paciente_VO(this, this.getUsuario());
		return new Paciente_VO(this);
	}

	@Override
	public Paciente_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new Paciente_VO(this, profundidadActual, profundidadDeseada);
	}

	public void actualizarProductos_OS() {

		for (ProductoObraSocialPaciente prod : this.getProductos_OSPaciente()) {
			prod.setPaciente(this);
		}

	}

	
}