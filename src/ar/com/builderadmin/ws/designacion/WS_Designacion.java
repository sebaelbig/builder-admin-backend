package ar.com.builderadmin.ws.designacion;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.builderadmin.ws.WS_Abstracto;

@RestController
@RequestMapping(value = "/designacion", produces = "application/json;charset=utf-8")
public class WS_Designacion extends WS_Abstracto {
	
	@RequestMapping(value = "/ping", produces = "text/plain;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody
	String ping() {
		return "WS_Designacion -> ping";
	}
}
