package org.example.validation;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class MyValidator {
    private static final ValidatorFactory FACTORY =
            Validation.buildDefaultValidatorFactory();

    public static final Validator VALIDATOR =
            FACTORY.getValidator();
}

