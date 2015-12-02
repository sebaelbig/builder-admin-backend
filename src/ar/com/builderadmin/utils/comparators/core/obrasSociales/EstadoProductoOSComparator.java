package ar.com.builderadmin.utils.comparators.core.obrasSociales;

import java.util.Comparator;

import ar.com.builderadmin.vo.core.obrasSociales.EstadoProducto_OS_VO;
import ar.com.builderadmin.vo.core.obrasSociales.Producto_OSHabilitado_VO;
import ar.com.builderadmin.vo.core.obrasSociales.Producto_OSSuspendido_VO;

public class EstadoProductoOSComparator implements
		Comparator<EstadoProducto_OS_VO> {
	public int compare(Producto_OSHabilitado_VO o1, Producto_OSHabilitado_VO o2) {
		return o1.getFecha().compareTo(o2.getFecha());
	}

	public int compare(Producto_OSSuspendido_VO o1, Producto_OSSuspendido_VO o2) {
		return o1.getFecha().compareTo(o2.getFecha());
	}

	public int compare(Producto_OSHabilitado_VO o1, Producto_OSSuspendido_VO o2) {
		return o1.getFecha().compareTo(o2.getFecha());
	}

	public int compare(Producto_OSSuspendido_VO o1, Producto_OSHabilitado_VO o2) {
		return o1.getFecha().compareTo(o2.getFecha());
	}

	@Override
	public int compare(EstadoProducto_OS_VO o1, EstadoProducto_OS_VO o2) {
		return 0;
	}
}

/*
 * Location: D:\Horus - Hospital Español\v1.20\horus_fe.zip
 * 
 * Qualified Name:
 * WEB-INF.classes.org.hospitalespanol.core.obrasSociales.EstadoProductoOSComparator
 * 
 * JD-Core Version: 0.7.0.1
 */