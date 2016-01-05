package ar.com.builderadmin.ws.unidadFuncional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/unidad_funcional", produces = "application/json;charset=utf-8")
public class WS_UnidadFuncional {
	
	@RequestMapping(value = "/ping", produces = "text/plain;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody
	String ping() {
		return "WS_UnidadFuncional -> ping";
	}

}
