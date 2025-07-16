package insuranceverificationpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
    private ServiceRegistration<InsuranceVerification> insuranceVerificationRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        // Register InsuranceVerification service
        InsuranceVerificationImpl insuranceVerification = new InsuranceVerificationImpl();
        insuranceVerificationRegistration = context.registerService(InsuranceVerification.class, insuranceVerification, null);
        System.out.println("Insurance Verification service registered.");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // Unregister service
        insuranceVerificationRegistration.unregister();
        System.out.println("Insurance Verification service unregistered.");
    }
}