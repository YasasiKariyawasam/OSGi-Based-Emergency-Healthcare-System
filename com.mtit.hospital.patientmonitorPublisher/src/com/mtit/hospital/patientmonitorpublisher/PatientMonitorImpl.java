package com.mtit.hospital.patientmonitorpublisher;

public class PatientMonitorImpl implements PatientMonitorService{

    @Override
    public String getPatientVitals(String patientId) {
        // Simulate fetching patient vitals (e.g., heart rate, oxygen level, blood pressure)
        return "Patient ID: " + patientId + ", Vitals: Heart Rate=72, Oxygen=98%, BP=120/80";
    }
	
	
	
	
	
	
}
