package ar.org.hospitalespanol.utils.comparators.core.usuarios;

import java.util.Comparator;

import ar.org.hospitalespanol.vo.core.usuarios.Usuario_VO;

public class UsuarioComparator implements Comparator<Usuario_VO> {

	@Override
	public int compare(Usuario_VO o1, Usuario_VO o2) {
		return o1.toString().compareToIgnoreCase(o2.toString());
	}

}
