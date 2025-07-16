package dataanalyticsreportingpublisher;

import java.io.*;

import java.time.LocalDateTime;
import java.util.*;

import org.osgi.framework.FrameworkUtil;

import org.osgi.framework.BundleContext;

import org.osgi.framework.ServiceReference;
import insuranceverificationpublisher.InsuranceVerification;

public class DataAnalyticsReportingImpl implements DataAnalyticsReporting {
    private Scanner scanner = new Scanner(System.in);
    private boolean isAuthenticated = false;
    private String userName;
    
    private List<String> reports = new ArrayList<>();
    private List<String> feedbacks = new ArrayList<>();
    private List<String> logs = new ArrayList<>();

    @Override
    public void displayMenu() {
        if (!isAuthenticated) {
            login();
        }

        while (true) {
            System.out.println("\n==== Welcome " + userName + " To The Data Analytics Reporting System ====");
            System.out.println(" ");
            System.out.println("1. Report Section");
            System.out.println("2. Generate Bill");
            System.out.println("3. Insurance Verification"); // New option
            System.out.println("4. Provide Feedback");
            System.out.println("5. View Summary");
            System.out.println("6. Logout");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    reportSection();  // Redirect to report section
                    break;
                case 2:
                    generateBill();
                    break;
                case 3:
                	validateInsuranceClaim();
                    
                    break;
                case 4:
                	provideFeedback();
                    break;
                case 5:
                	viewSummary();; // New method for insurance verification
                    break;
                case 6:
                    System.out.println("Logging out...");
                    logs.add("User logged out at " + LocalDateTime.now());
                    isAuthenticated = false;
                    login();
                    return;
                   
               
                default:
                    System.out.println("Invalid choice. Try again.");
                    continue;
            }

         // Ask if the user wants to exit
            System.out.print("\nDo you want to exit? (yes/no): ");
            String exitChoice = scanner.nextLine().toLowerCase();

            // Gracefully handle exit
            if (exitChoice.equals("yes")) {
                System.out.println("Exiting the system. Goodbye!");
                break; // Exit the menu and end the program
            } else if (exitChoice.equals("no")) {
                continue; // Return to the menu without throwing an exception
            } else {
                System.out.println("Invalid input. Returning to the menu...");
                continue; // Invalid input, stay in the loop
            }
       
        
        }
    }
    private void validateInsuranceClaim() {
        System.out.println("\n===== Insurance Verification =====");
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();
        System.out.print("Enter Treatment Code: ");
        String treatmentCode = scanner.nextLine();

        // Get the InsuranceVerification service
        BundleContext context = FrameworkUtil.getBundle(DataAnalyticsReportingImpl.class).getBundleContext();
        ServiceReference<InsuranceVerification> reference = context.getServiceReference(InsuranceVerification.class);
        if (reference != null) {
            InsuranceVerification insuranceVerification = context.getService(reference);
            if (insuranceVerification != null) {
                insuranceVerification.validateInsuranceClaim(patientId, treatmentCode);
            } else {
                System.out.println("Insurance Verification service is not available.");
            }
        } else {
            System.out.println("Insurance Verification service is not registered.");
        }
    }


    private void login() {
    	System.out.println("\n==================================== Welcome To The Smart Health System ==============================================");
        System.out.println("\n==Please Login To The System ==");
        System.out.println(" ");
        while (!isAuthenticated) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (username.equals("admin") && password.equals("admin12")) {
                isAuthenticated = true;
                System.out.println("Login successful .... ");
                System.out.println(" ");
                System.out.println("Login Date and Time: " + LocalDateTime.now());
                System.out.println(" ");
                System.out.print("Enter Your Name: ");
                userName = scanner.nextLine();
                logs.add(userName + " logged in at " + LocalDateTime.now());
            } else {
                System.out.println("Invalid username or password. Try again.");
            }
        }
    }

    private void reportSection() {
        while (true) {
            System.out.println("\n================ Report Section =================");
            System.out.println("1. Generate Report");
            System.out.println("2. View Reports");
            System.out.println("3. Export Report");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    generateReport();
                    break;
                case 2:
                    viewReports();
                    break;
                case 3:
                    exportReport();
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    continue;
            }

            System.out.print("\nDo you want to choose another option in the Report Section? (yes/no): ");
            String anotherOption = scanner.nextLine().toLowerCase();
            if (!anotherOption.equals("yes")) {
                break;  // Exit the report section if the user doesn't want to continue
            }
        }
    }

    @Override
    public void generateReport() {
        System.out.println("\n===== Generate Report Section ======");
        System.out.println("Select the type of report to generate:");
        System.out.println("1. Patient Admission Report");
        System.out.println("2. Medication Inventory Report");
        System.out.print("Enter your choice: ");
        
        int reportType = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (reportType) {
            case 1:
                generatePatientAdmissionReport();
                break;
            case 2:
                generateMedicationInventoryReport();
                break;
            default:
                System.out.println("Invalid choice. No report generated.");
                return;
        }
    }

    private void generatePatientAdmissionReport() {
        System.out.println("\n==== Patient Admission Report ====");
        System.out.print("Enter Patient Name: ");
        String patientName = scanner.nextLine();
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();
        System.out.print("Enter Admission Date (YYYY-MM-DD): ");
        String admissionDate = scanner.nextLine();
        System.out.print("Enter Ward/Department: ");
        String ward = scanner.nextLine();
        System.out.print("Enter Diagnosis: ");
        String diagnosis = scanner.nextLine();
        System.out.print("Enter Doctor in Charge: ");
        String doctorInCharge = scanner.nextLine();

        String reportDetails = String.format(
            "Patient Admission Report | Patient: %s (ID: %s) | Admission Date: %s | Ward: %s | Diagnosis: %s | Doctor: %s",
            patientName, patientId, admissionDate, ward, diagnosis, doctorInCharge
        );

        reports.add(reportDetails);
        logs.add("Generated Patient Admission Report: " + reportDetails);

        System.out.println("\n==== Report Generated Successfully ====");
        System.out.println("Report Details:");
        System.out.println("----------------------------------------");
        System.out.printf("%-20s : %s%n", "Patient Name", patientName);
        System.out.printf("%-20s : %s%n", "Patient ID", patientId);
        System.out.printf("%-20s : %s%n", "Admission Date", admissionDate);
        System.out.printf("%-20s : %s%n", "Ward/Department", ward);
        System.out.printf("%-20s : %s%n", "Diagnosis", diagnosis);
        System.out.printf("%-20s : %s%n", "Doctor in Charge", doctorInCharge);
        System.out.println("----------------------------------------");
    }

    private void generateMedicationInventoryReport() {
        System.out.println("\n==== Medication Inventory Report ====");
        System.out.print("Enter Medication Name: ");
        String medicationName = scanner.nextLine();
        System.out.print("Enter Current Stock Level: ");
        int currentStock = scanner.nextInt();
        System.out.print("Enter Minimum Required Stock Level: ");
        int minRequiredStock = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Last Restock Date (YYYY-MM-DD): ");
        String lastRestockDate = scanner.nextLine();

        String reportDetails = String.format(
            "Medication Inventory Report | Medication: %s | Current Stock: %d | Min Required Stock: %d | Last Restock Date: %s",
            medicationName, currentStock, minRequiredStock, lastRestockDate
        );

        reports.add(reportDetails);
        logs.add("Generated Medication Inventory Report: " + reportDetails);

        System.out.println("\n==== Report Generated Successfully ====");
        System.out.println("Report Details:");
        System.out.println("----------------------------------------");
        System.out.printf("%-20s : %s%n", "Medication Name", medicationName);
        System.out.printf("%-20s : %d%n", "Current Stock", currentStock);
        System.out.printf("%-20s : %d%n", "Min Required Stock", minRequiredStock);
        System.out.printf("%-20s : %s%n", "Last Restock Date", lastRestockDate);
        System.out.println("----------------------------------------");
    }

    @Override
    public void generateBill() {
    	System.out.println("\n============ Bill Section ==============");
        String patientName, wardName, phoneNumber, email;
        double totalBill, discount = 0.0;

        System.out.print("Enter Patient Name: ");
        patientName = scanner.nextLine();
        System.out.print("Enter Ward Name: ");
        wardName = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        phoneNumber = scanner.nextLine();
        System.out.print("Enter Email: ");
        email = scanner.nextLine();
        System.out.print("Enter Total Bill Amount: ");
        totalBill = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        // Discount Calculation
        if (totalBill > 100000) {
            discount = totalBill * 0.10;
        } else if (totalBill > 75000) {
            discount = totalBill * 0.08;
        } else if (totalBill > 25000) {
            discount = totalBill * 0.05;
        } else if (totalBill > 10000) {
            discount = totalBill * 0.02;
        }

        double finalBill = totalBill - discount;

        // Professional Bill Format
        System.out.println("\n==================================================");
        System.out.println("               HOSPITAL BILL RECEIPT              ");
        System.out.println("==================================================");
        System.out.printf("%-20s : %s%n", "Patient Name", patientName);
        System.out.printf("%-20s : %s%n", "Ward Name", wardName);
        System.out.printf("%-20s : %s%n", "Phone Number", phoneNumber);
        System.out.printf("%-20s : %s%n", "Email", email);
        System.out.println("--------------------------------------------------");
        System.out.printf("%-20s : Rs. %.2f%n", "Total Bill Amount", totalBill);
        System.out.printf("%-20s : Rs. %.2f%n", "Discount Applied", discount);
        System.out.printf("%-20s : Rs. %.2f%n", "Final Bill Amount", finalBill);

        // Save bill details
        String billDetails = String.format("Patient: %s, Ward: %s, Phone: %s, Email: %s, Total Bill: %.2f, Discount: %.2f, Final Bill: %.2f",
                patientName, wardName, phoneNumber, email, totalBill, discount, finalBill);
        reports.add("Bill Generated: " + billDetails);
        logs.add("Generated Bill: " + billDetails);

        // Ask if the user wants to generate another bill
        System.out.print("\nDo you want to generate another bill? (yes/no): ");
        String generateAnother = scanner.nextLine().toLowerCase();
        if (generateAnother.equals("yes")) {
            generateBill(); // Recursively call generateBill for another bill
        }
    }

    @Override
    public void provideFeedback() {
    	System.out.println("\n============= Feedback Section ============");
        System.out.print("Enter your feedback: ");
        String feedback = scanner.nextLine();
        feedbacks.add(feedback);
        logs.add("Feedback provided: " + feedback);
        System.out.println("Thank you for your feedback!");
    }

    @Override
    public void viewSummary() {
        System.out.println("\n====== Report Summary ======");
        System.out.println("Total Reports: " + reports.size());
        System.out.println("Total Feedbacks: " + feedbacks.size());
        
        // Simple Calculations
        double feedbackPercentage = (feedbacks.size() / (double)(reports.size() + feedbacks.size())) * 100;
        System.out.println("Feedback Contribution: " + String.format("%.2f", feedbackPercentage) + "%");

        System.out.println("\n===== Activity Log =====");
        for (String log : logs) {
            System.out.println(log);
        }
    }

    @Override
    public void viewReports() {
        if (reports.isEmpty()) {
            System.out.println("No reports available.");
            return;
        }
        System.out.println("\n===== Stored Reports =====");
        for (int i = 0; i < reports.size(); i++) {
            System.out.println((i + 1) + ". " + reports.get(i));
        }
    }

   
    @Override
    public void exportReport() {
        

        System.out.print("Enter file path to export the report: ");
        String filePath = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String report : reports) {
                writer.write(report);
                writer.newLine();
            }
            System.out.println("Report exported successfully");
            
            
        } catch (IOException e) {
            System.out.println("Error exporting the report: " + e.getMessage());
        }
    }
    
   
    
    
    
}
