package dataanalyticsreportingpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class DataAnalyticsReportingActivator implements BundleActivator {
    private ServiceRegistration<?> serviceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Data Analytics Reporting Publisher Started");
        
        DataAnalyticsReporting service = new DataAnalyticsReportingImpl();
        serviceRegistration = context.registerService(DataAnalyticsReporting.class.getName(), service, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Data Analytics Reporting Publisher Stopped");
        serviceRegistration.unregister();
    }
}
