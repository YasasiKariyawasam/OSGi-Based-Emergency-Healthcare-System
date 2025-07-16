package com.mtit.hospital.emergencypredictorconsumer;

import com.mtit.hospital.patientmonitorpublisher.PatientMonitorService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceEvent;

public class EmergencyPredictorActivator implements BundleActivator {

    private BundleContext context;
    private ServiceReference<PatientMonitorService> serviceReference;

    @Override
    public void start(BundleContext context) throws Exception {
        this.context = context;
        System.out.println("Patient Emergency Predictor Started!");

        // Add a ServiceListener to wait for the service to be registered
        String filter = "(objectclass=" + PatientMonitorService.class.getName() + ")";
        context.addServiceListener(listener, filter);

        // Try to get the service immediately
        serviceReference = context.getServiceReference(PatientMonitorService.class);
        if (serviceReference != null) {
            useService();
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Patient Emergency Predictor Stopped!");
        if (serviceReference != null) {
            context.ungetService(serviceReference);
        }
    }

    private void useService() {
        PatientMonitorService service = context.getService(serviceReference);
        if (service != null) {
            String vitals = service.getPatientVitals("12345");
            System.out.println("Received Patient Vitals: " + vitals);
            // Add logic to predict emergencies based on vitals
            if (vitals.contains("Heart Rate=120")) {
                System.out.println("Emergency Alert: High Heart Rate!");
            }
        }
    }

    private final ServiceListener listener = new ServiceListener() {
        @Override
        public void serviceChanged(ServiceEvent event) {
            if (event.getType() == ServiceEvent.REGISTERED) {
                serviceReference = context.getServiceReference(PatientMonitorService.class);
                useService();
            }
        }
    };
}
