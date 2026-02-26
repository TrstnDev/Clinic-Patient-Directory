package com.example.first_proj.model.validation;

import com.example.first_proj.model.Patient;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.format.DateTimeFormatter;

public class RsaIdDobValidator implements ConstraintValidator<ValidRsaIdMatchingDob, Patient> {

    @Override
    public boolean isValid(Patient patient, ConstraintValidatorContext context) {
        // 1. If either field is missing, skip this validation
        // @NotNull annotations handle empty fields
        if (patient.getPatientRsaId() == null || patient.getDateOfBirth() == null) {
            return true;
        }

        String rsaId = patient.getPatientRsaId();

        // 2. Prevents string index errors if ID is incomplete
        if (rsaId.length() < 6) {
            return false;
        }

        // 3. Format the LocalDate to match SA ID prefix format (YYMMDD)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        String expectedPrefix = patient.getDateOfBirth().format(formatter);
        String actualPrefix = rsaId.substring(0, 6);

        // 4. Perform comparison
        boolean isValid = expectedPrefix.equals(actualPrefix);

        // 5. Advanced formatting: if invalid attach the error directly to the specific field
        // Thymeleaf highlights the input box in red
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()).addPropertyNode("patientRsaId").addConstraintViolation();
        }

        return isValid;
    }

}
