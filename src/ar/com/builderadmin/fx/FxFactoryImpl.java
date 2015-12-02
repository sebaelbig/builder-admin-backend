package ar.com.builderadmin.fx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * FX Factory implementation of the I_FxFactory interface.
 * 
 * @author fgonzalez
 * 
 */
public class FxFactoryImpl {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(FxFactoryImpl.class);

	/**
	 * Spring Application Context
	 */
	@Autowired
	private ApplicationContext applicationContext;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.universe.core.fx.I_FxFactory#getNewFxInstance(java.lang.Class)
	 */
	public <F extends I_FX> F getNewFxInstance(Class<F> clazz) {
		
		this.logger.debug("creando FX: " + clazz.getName());

		return this.getApplicationContext().getBean(clazz);
	}

	/**
	 * @return the applicationContext
	 */
	public ApplicationContext getApplicationContext() {
		return this.applicationContext;
	}

	/**
	 * @param applicationContext
	 *            the applicationContext to set
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

}
