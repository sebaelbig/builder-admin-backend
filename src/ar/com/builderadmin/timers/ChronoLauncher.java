package ar.com.builderadmin.timers;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
public class ChronoLauncher
{

	/**
	 * Logger.
	 */
	protected final Logger log = LoggerFactory.getLogger(ChronoLauncher.class);

	@Autowired
	private ChronoPedidos chronoPedidos;
	
	@Autowired
	private ChronoMailer chronoMailer;
	
	public Executor taskExecutor() {
		return Executors.newScheduledThreadPool(100);
	}

	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		
		taskRegistrar.setScheduler(taskExecutor());
		
		taskRegistrar.addTriggerTask( chronoPedidos, chronoPedidos);
		taskRegistrar.addTriggerTask( chronoMailer, chronoMailer);
	}
	
	
}