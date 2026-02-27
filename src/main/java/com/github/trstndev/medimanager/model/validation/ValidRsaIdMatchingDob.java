package com.github.trstndev.medimanager.model.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

// Target TYPE means this annotation goes on top of the entire class, not a specific field
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
// This links the annotation to the logic class
@Constraint(validatedBy = RsaIdDobValidator.class)
@Documented
public @interface ValidRsaIdMatchingDob {

    // The default error message if validation fails
    String message() default "The first 6 digits of the RSA ID must match the Date of Birth.";

    // Boilerplate required by Jakarta Validation API
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
