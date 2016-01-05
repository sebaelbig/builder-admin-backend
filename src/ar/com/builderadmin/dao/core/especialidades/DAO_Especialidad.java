package ar.com.builderadmin.dao.core.especialidades;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.vo.core.especialidades.Especialidad_VO;

@Service
public class DAO_Especialidad extends DAO<Especialidad_VO> {
	
	public DAO_Especialidad() {
		setCondiciones(new HashMap<String, Object>());
	}

	@Override
	protected String getIdClass() {
		return "e";
	}

	public List<Especialidad_VO> listarTodas() {
		return listar(Integer.valueOf(1), Integer.valueOf(0));
	}

	@Override
	protected Class getClazz() {
		return Especialidad_VO.class;
	}

	
}