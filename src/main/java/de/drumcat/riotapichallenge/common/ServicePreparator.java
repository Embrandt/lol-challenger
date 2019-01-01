package de.drumcat.riotapichallenge.common;

import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Resource;

import javax.inject.Singleton;
import java.util.logging.Logger;

@Singleton
public class ServicePreparator {

	private static Logger logger = Logger.getLogger(ServicePreparator.class
			.getName());


	private void initServices() {
		logger.info("Prepare for the resources for the services...");

		Defaults.setDateFormat(null);

		initDomainService();

	}

	private void initDomainService() {
		// Domain Service
		Resource resource = new Resource("");
		//((RestServiceProxy) arbeitslisteClient).setResource(resource);

	}
	public void prepare() {
		initServices();
	}
}
