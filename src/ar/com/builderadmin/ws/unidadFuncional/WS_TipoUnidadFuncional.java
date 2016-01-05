package ar.com.builderadmin.ws.unidadFuncional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.builderadmin.ws.WS_Abstracto;

@RestController
@RequestMapping(value = "/tipoUnidadFuncional", produces = "application/json;charset=utf-8")
public class WS_TipoUnidadFuncional extends WS_Abstracto{
	
	
	/**
	 * Ping de disponibilidad
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ping", produces = "text/plain;charset=utf-8", method = RequestMethod.GET)
	public String ping() {
		return "WS_TipoUnidadFuncional -> ping";
	}

}
