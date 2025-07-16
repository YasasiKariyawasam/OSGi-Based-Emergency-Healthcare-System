package insuranceverificationpublisher;

public interface InsuranceVerification {
    void validateInsuranceClaim(String patientId, String treatmentCode);
}