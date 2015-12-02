package ar.com.builderadmin.vo.core.usuarios.fichaDeConsumo;

import java.util.ArrayList;
import java.util.List;

import ar.com.builderadmin.model.core.usuarios.Usuario;
import ar.com.builderadmin.model.core.usuarios.fichaDeConsumo.FichaDeConsumo;
import ar.com.builderadmin.model.core.usuarios.fichaDeConsumo.ItemFichaDeConsumo;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.vo.core.usuarios.Usuario_VO;

public class FichaDeConsumo_VO implements I_ValueObject<FichaDeConsumo> {

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}

	private Integer version;

	private Long idUsuario;

	private Usuario_VO usuario;

	private List<ItemFichaDeConsumo_VO> items = new ArrayList<ItemFichaDeConsumo_VO>();

	public FichaDeConsumo_VO() {
	}

	public FichaDeConsumo_VO(FichaDeConsumo ficha) {
		this.setObject(ficha);
	}

	public FichaDeConsumo_VO(FichaDeConsumo ficha, int profundidadActual,
			int profundidadDeseada) {
		this.setObject(ficha, profundidadActual, profundidadDeseada);
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

	public Usuario_VO getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario_VO usuario) {
		this.usuario = usuario;
	}

	public List<ItemFichaDeConsumo_VO> getItems() {
		return items;
	}

	public void setItems(List<ItemFichaDeConsumo_VO> items) {
		this.items = items;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public void setObject(FichaDeConsumo ficha) {

		this.setId(ficha.getId());
		this.setVersion(ficha.getVersion());

		// this.setUsuario(ficha.getUsuario().toValueObject());
		this.cargarDatosUtilesDeUsuario(ficha.getUsuario());

		for (ItemFichaDeConsumo item : ficha.getItems()) {
			this.getItems().add(item.toValueObject());
		}

	}

	@Override
	public FichaDeConsumo toObject() {
		FichaDeConsumo resul = new FichaDeConsumo();

		resul.setId(this.getId());
		resul.setVersion(this.getVersion());

		for (ItemFichaDeConsumo_VO item : this.getItems()) {
			ItemFichaDeConsumo i = item.toObject();
			i.setFichaDeConsumo(resul);
			resul.getItems().add(i);
		}

		return resul;
	}

	public void agregarItem(ItemFichaDeConsumo_VO item) {
		this.getItems().add(item);

		item.setIdFichaDeConsumo(this.getId());
	}

	public void quitarItem(ItemFichaDeConsumo_VO item) {
		this.getItems().remove(item);
		// item.setIdFichaDeConsumo(null);
	}

	protected void cargarDatosUtilesDeUsuario(Usuario usr) {

		if (this.getUsuario() == null) {
			this.setUsuario(new Usuario_VO());
		}

		this.getUsuario().setId(usr.getId());
		this.getUsuario().setVersion(usr.getVersion());
		this.getUsuario().setApellido(usr.getApellido());
		this.getUsuario().setNombres(usr.getNombres());
		this.getUsuario().setNombreUsuario(usr.getNombreUsuario());
		this.getUsuario().setSexo(usr.getSexo());
		this.getUsuario().setTipoDocumento(usr.getTipoDocumento());
		this.getUsuario().setNroDocumento(usr.getNroDocumento());

	}

	@Override
	public void setObject(FichaDeConsumo ficha, int profundidadActual,
			int profundidadDeseada) {

		this.setId(ficha.getId());
		this.setVersion(ficha.getVersion());

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual != I_ValueObject.PROFUNDIDAD_BASE
				&& profundidadActual < profundidadDeseada) {

			// Como es una relacion para atras, solo se convierte hasta el nivel
			// 0
			this.setUsuario(ficha.getUsuario().toValueObject(
					I_ValueObject.PROFUNDIDAD_BASE, 0));
			// this.cargarDatosUtilesDeUsuario(ficha.getUsuario());

			for (ItemFichaDeConsumo item : ficha.getItems()) {
				this.getItems().add(
						item.toValueObject(profundidadActual + 1,
								profundidadDeseada));
			}

		}

	}

	public FichaDeConsumo toObject(int profundidadActual, int profundidadDeseada) {

		FichaDeConsumo resul = new FichaDeConsumo();

		resul.setId(this.getId());
		resul.setVersion(this.getVersion());

		if (profundidadActual != I_ValueObject.PROFUNDIDAD_BASE
				&& profundidadActual < profundidadDeseada) {

			for (ItemFichaDeConsumo_VO item : this.getItems()) {

				ItemFichaDeConsumo i = item.toObject(profundidadActual + 1,
						profundidadDeseada);
				// i.setFichaDeConsumo(resul);
				resul.getItems().add(i);

			}

			if (this.getUsuario() != null) {
				// Llamada hacia atras
				resul.setUsuario(this.getUsuario().toObject(
						I_ValueObject.PROFUNDIDAD_BASE, 0));
			}

		}
		return resul;

	}

}
