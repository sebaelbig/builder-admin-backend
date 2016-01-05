package ar.com.builderadmin.ws.planificacion;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/planificacion", produces = "application/json;charset=utf-8")
public class WS_Planificacion {
	
	@RequestMapping(value = "/ping", produces = "text/plain;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody
	String ping() {
		return "WS_planificacion -> ping";
	}

}
