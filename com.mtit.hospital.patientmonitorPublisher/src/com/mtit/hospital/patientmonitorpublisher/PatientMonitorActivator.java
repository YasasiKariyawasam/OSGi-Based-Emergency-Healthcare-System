package com.mtit.hospital.patientmonitorpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class PatientMonitorActivator implements BundleActivator {

	private ServiceRegistration<PatientMonitorService> registration;

	
	@Override

	public void start(BundleContext context) throws Exception {
		 System.out.println("Hospital Patient Monitor Service Started!");
	        PatientMonitorService service = new PatientMonitorImpl();
	        registration = context.registerService(PatientMonitorService.class, service, null);
	}

	@Override

	public void stop(BundleContext context) throws Exception {
	
		  System.out.println("Hospital Patient Monitor Service Stopped!");
	        registration.unregister();
	}

}
