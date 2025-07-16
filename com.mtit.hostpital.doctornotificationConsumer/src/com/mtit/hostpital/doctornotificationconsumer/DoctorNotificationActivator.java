package com.mtit.hostpital.doctornotificationconsumer;

import com.mtit.hostpital.bedmanagementpublisher.BedManagementService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

public class DoctorNotificationActivator implements BundleActivator {

	 private BundleContext context;
	 private ServiceReference<BedManagementService> serviceReference;

	 
	 @Override
	public void start(BundleContext context) throws Exception {
		
		  this.context = context;
	        System.out.println("Doctor Notification System Started!");

	        // Add a ServiceListener to wait for the service to be registered
	        String filter = "(objectclass=" + BedManagementService.class.getName() + ")";
	        context.addServiceListener(listener, filter);

	        // Try to get the service immediately
	        serviceReference = context.getServiceReference(BedManagementService.class);
	        if (serviceReference != null) {
	            useService();
	        }
	}

	 
	 @Override
	public void stop(BundleContext context) throws Exception {
	     System.out.println("Doctor Notification System Stopped!");
	        if (serviceReference != null) {
	            context.ungetService(serviceReference);
	        }
	    }

	    private void useService() {
	    	 if (serviceReference != null) {
	    	        BedManagementService service = context.getService(serviceReference);
	    	        if (service != null) {
	    	            System.out.println("Successfully retrieved BedManagementService.");
	    	            
	    	            int beds = service.getAvailableBeds();
	    	            int icuUnits = service.getAvailableIcuUnits();
	    	            
	    	            System.out.println("Current Bed Status: Beds = " + beds + ", ICU Units = " + icuUnits);
	    	            
	    	            if (beds < 10) {
	    	                System.out.println("🚨 Alert: Only " + beds + " beds available!");
	    	            }
	    	            if (icuUnits < 5) {
	    	                System.out.println("🚨 Alert: Only " + icuUnits + " ICU units available!");
	    	            }
	    	        } else {
	    	            System.out.println("⚠ Service reference exists, but service is null.");
	    	        }
	    	    } else {
	    	        System.out.println("⚠ No service reference available.");
	    	    }
	    
	        }
	


     private final ServiceListener listener = new ServiceListener() {
    @Override
    public void serviceChanged(ServiceEvent event) {
        if (event.getType() == ServiceEvent.REGISTERED) {
        
            serviceReference = context.getServiceReference(BedManagementService.class);
            
            useService();
            
            }else {
            System.out.println("Failed to fetch BedManagementService.");
        }
        }
      
  };
}
