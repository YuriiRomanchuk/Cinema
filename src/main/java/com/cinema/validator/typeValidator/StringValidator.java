package com.cinema.validator.typeValidator;

public class StringValidator implements Validator<String> {

    private String regex;

    public StringValidator(String regex) {
        this.regex = regex;
    }

    @Override
    public boolean validateValue(String value) {
        return value.matches(regex);
    }
}
