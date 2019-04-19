package com.cinema.validator.verifier;

import com.cinema.validator.typeValidator.StringValidator;

public class StringVerifier implements Verifier<StringBuilder> {

    private final StringValidator stringValidator;
    private final String value;
    private final String fieldName;

    public StringVerifier(StringValidator stringValidator, String value, String fieldName) {
        this.stringValidator = stringValidator;
        this.value = value;
        this.fieldName = fieldName;
    }

    @Override
    public void check(StringBuilder stringBuilder) {
        if (!stringValidator.validateValue(value)) {
            stringBuilder.append(fieldName + ", ");
        }
    }
}
