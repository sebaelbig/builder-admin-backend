package ar.org.hospitalespanol.vo.core.datosSalud;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.org.hospitalespanol.model.core.datosSalud.ItemModulo;
import ar.org.hospitalespanol.model.core.datosSalud.VersionItemsModulo;
import ar.org.hospitalespanol.vo.I_ValueObject;

public class VersionItemsModulo_VO implements I_ValueObject<VersionItemsModulo> {

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Integer version;
	private Date fechaDesde;
	private Date fechaHasta;
	private String fechaDesde_str;
	private String fechaHasta_str;

	private Long idModulo;

	private List<ItemModulo_VO> items = new ArrayList<ItemModulo_VO>();

	public VersionItemsModulo_VO() {
	}

	public VersionItemsModulo_VO(VersionItemsModulo versionItemsModulo) {

		this.setId(versionItemsModulo.getId());
		this.setVersion(versionItemsModulo.getVersion());
		this.setFechaDesde(versionItemsModulo.getFechaDesde());
		this.setFechaHasta(versionItemsModulo.getFechaHasta());

		this.setItems(new ArrayList<ItemModulo_VO>());

		for (ItemModulo im : versionItemsModulo.getItems()) {
			this.getItems().add(im.toValueObject());
		}

	}

	public VersionItemsModulo_VO(Modulo_VO modulo, List<ItemModulo_VO> lista) {
		this.setFechaDesde(new Date());
		this.setFechaHasta(null);
		this.setItems(lista);
		this.setIdModulo(modulo.getId());
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

	public void setFechaDesde(Date date) {
		this.fechaDesde = date;
		if (date != null) {
			this.setFechaDesde_str(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
					.format(date));
		}
	}

	public void setFechaHasta(Date date) {
		this.fechaHasta = date;
		if (date != null) {
			this.setFechaHasta_str(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
					.format(date));
		}
	}

	@Override
	public VersionItemsModulo toObject() {
		VersionItemsModulo v = new VersionItemsModulo();

		v.setId(this.getId());
		v.setVersion(this.getVersion());

		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		Date fd = null;
		try {
			fd = f.parse(this.getFechaDesde_str());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		v.setFechaDesde(fd);

		Date fh = null;

		if (this.getFechaHasta() != null)
			try {
				fh = f.parse(this.getFechaHasta_str());
			} catch (ParseException e) {
				e.printStackTrace();
			}

		v.setFechaHasta(fh);

		for (ItemModulo_VO ivo : this.getItems()) {
			v.getItems().add(ivo.toObject());
		}

		return v;
	}

	public List<ItemModulo_VO> getItems() {
		return items;
	}

	public void setItems(List<ItemModulo_VO> items) {
		this.items = items;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	@Override
	public void setObject(VersionItemsModulo objeto) {

		this.setId(objeto.getId());
		this.setVersion(objeto.getVersion());

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		this.setFechaDesde_str(sdf.format(objeto.getFechaDesde()));
		this.setFechaHasta_str(sdf.format(objeto.getFechaHasta()));

		this.setItems(new ArrayList<ItemModulo_VO>());
		for (ItemModulo im : objeto.getItems()) {

			this.getItems().add(new ItemModulo_VO(im));

		}

	}

	@Override
	public void setObject(VersionItemsModulo objeto, int profundidadActual,
			int profundidadDeseada) {

		this.setId(objeto.getId());
		this.setVersion(objeto.getVersion());

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		this.setFechaDesde_str(sdf.format(objeto.getFechaDesde()));
		this.setFechaHasta_str(sdf.format(objeto.getFechaHasta()));

		if (profundidadActual < profundidadDeseada) {
			this.setItems(new ArrayList<ItemModulo_VO>());

			for (ItemModulo im : objeto.getItems()) {
				this.getItems().add(
						im.toValueObject(profundidadActual + 1,
								profundidadDeseada));
			}
		}

	}

	public String getFechaDesde_str() {
		return fechaDesde_str;
	}

	public void setFechaDesde_str(String fechaDesde_str) {
		this.fechaDesde_str = fechaDesde_str;
	}

	public String getFechaHasta_str() {
		return fechaHasta_str;
	}

	public void setFechaHasta_str(String fechaHasta_str) {
		this.fechaHasta_str = fechaHasta_str;
	}

	public Long getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(Long idModulo) {
		this.idModulo = idModulo;
	}

	@Override
	public String toString() {
		return "Version del m�dulo habilitada desde: "
				+ this.getFechaDesde_str() + "hasta: "
				+ this.getFechaHasta_str();
	}

	public VersionItemsModulo toObject(int profundidadActual,
			int profundidadDeseada) {

		return null;
	}

}
