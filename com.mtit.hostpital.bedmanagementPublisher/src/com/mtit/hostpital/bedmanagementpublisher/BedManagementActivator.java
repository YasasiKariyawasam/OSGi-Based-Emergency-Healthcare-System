package com.mtit.hostpital.bedmanagementpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class BedManagementActivator implements BundleActivator {

	private ServiceRegistration<BedManagementService> registration;

	
	 @Override
	public void start(BundleContext context) throws Exception {
		
	     System.out.println("Hospital Bed Management System Started!");
	        BedManagementService service = new BedManagementServiceImpl();
	        registration = context.registerService(BedManagementService.class, service, null);
	     // Simulate an update to bed status
	        new Thread(() -> {
	            try {
	                Thread.sleep(5000); // Delay 5 seconds
	                service.updateBedStatus(8, 3); // Set below the threshold
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }).start();
	}

	 
	 @Override
	public void stop(BundleContext context) throws Exception {
	
		 System.out.println("Hospital Bed Management System Stopped!");
	        registration.unregister();
	}

}
