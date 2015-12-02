package ar.com.builderadmin.security.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Si la peticion viene de IE, le pedimos que no use el cache 
 * 
 * @author segarcia
 *
 */
public class IECacheInterceptor extends HandlerInterceptorAdapter  {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {	
		
		String browserName = request.getHeader("user-agent");
		
		if (browserName!=null && browserName.indexOf("Trident/7")>0){
			//Es IE
			response.setHeader("Cache-Control", "no-cache");
		}
		
		return true;
		
	}
	
}