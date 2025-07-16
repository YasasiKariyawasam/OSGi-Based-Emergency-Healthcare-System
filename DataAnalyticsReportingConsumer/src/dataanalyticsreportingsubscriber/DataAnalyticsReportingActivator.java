package dataanalyticsreportingsubscriber;

import dataanalyticsreportingpublisher.DataAnalyticsReporting;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class DataAnalyticsReportingActivator implements BundleActivator {
    private ServiceReference<?> serviceReference;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Data Analytics Reporting Subscriber Started");

        serviceReference = context.getServiceReference(DataAnalyticsReporting.class.getName());
        DataAnalyticsReporting service = (DataAnalyticsReporting) context.getService(serviceReference);
        
        if (service != null) {
            service.displayMenu();
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Data Analytics Reporting Subscriber Stopped");
        context.ungetService(serviceReference);
    }
}
