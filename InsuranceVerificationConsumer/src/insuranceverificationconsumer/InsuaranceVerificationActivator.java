package insuranceverificationconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import insuranceverificationpublisher.InsuranceVerification;

public class InsuaranceVerificationActivator implements BundleActivator {
    private ServiceReference<InsuranceVerification> insuranceVerificationReference;

    @Override
    public void start(BundleContext context) throws Exception {
        // Get the InsuranceVerification service
        insuranceVerificationReference = context.getServiceReference(InsuranceVerification.class);
        if (insuranceVerificationReference != null) {
            InsuranceVerification insuranceVerification = context.getService(insuranceVerificationReference);
            if (insuranceVerification != null) {
                // Simulate an insurance claim validation
                insuranceVerification.validateInsuranceClaim("12345", "TREAT123");
            }
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // Release the service
        if (insuranceVerificationReference != null) {
        	System.out.println("InsuranceVerificationConsumer Stopped");
            context.ungetService(insuranceVerificationReference);
            
        }
    }
}