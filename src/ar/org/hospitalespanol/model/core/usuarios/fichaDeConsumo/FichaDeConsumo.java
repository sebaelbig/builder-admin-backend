package ar.org.hospitalespanol.model.core.usuarios.fichaDeConsumo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.org.hospitalespanol.model.core.usuarios.Usuario;
import ar.org.hospitalespanol.vo.core.usuarios.fichaDeConsumo.FichaDeConsumo_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
@Entity @Table( name = "ficha_de_consumo")
public class FichaDeConsumo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_ficha_de_consumo", name = "seq_ficha_de_consumo", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ficha_de_consumo")
	private Long id;

	@Version
	private Integer version;

	@OneToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@OneToMany(mappedBy = "fichaDeConsumo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ItemFichaDeConsumo> items = new ArrayList<ItemFichaDeConsumo>();

	public FichaDeConsumo(Usuario usuario2) {
		this.setUsuario(usuario2);
	}

	public FichaDeConsumo() {
	}

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<ItemFichaDeConsumo> getItems() {
		return items;
	}

	public void setItems(List<ItemFichaDeConsumo> items) {
		this.items = items;
	}

	public FichaDeConsumo_VO toValueObject() {
		return new FichaDeConsumo_VO(this);
	}

	public FichaDeConsumo_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new FichaDeConsumo_VO(this, profundidadActual,
				profundidadDeseada);
	}

}