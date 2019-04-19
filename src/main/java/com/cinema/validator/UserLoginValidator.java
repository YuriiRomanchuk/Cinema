package com.cinema.validator;

import com.cinema.model.dto.UserDto;
import com.cinema.validator.typeValidator.StringValidator;
import com.cinema.validator.verifier.StringVerifier;
import com.cinema.validator.verifier.Verifier;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserLoginValidator implements ModelValidator<UserDto, String>{

    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("regexpValidator");

    @Override
    public String validate(UserDto userDto) {

        StringBuilder stringBuilder = new StringBuilder();
        List<Verifier<StringBuilder>> verifiers = receiveValidateRule(userDto);

        for (Verifier<StringBuilder> verifier : verifiers) {
            verifier.check(stringBuilder);
        }

        stringBuilder.setLength(stringBuilder.length() - 1);
        if (stringBuilder.length() > 0) {
            stringBuilder.append(" is't valid!");
        }

        return stringBuilder.toString().trim();
    }

    private List<Verifier<StringBuilder>> receiveValidateRule(UserDto userDto) {

        List<Verifier<StringBuilder>> verifiers = new ArrayList<>();
        StringValidator stringNumberValidator = new StringValidator(resourceBundle.getString("regexStringNumber"));
        StringValidator emailValidator = new StringValidator(resourceBundle.getString("regexEmail"));

        verifiers.add(new StringVerifier(stringNumberValidator, userDto.getLogin(), "Nickname"));
        verifiers.add(new StringVerifier(stringNumberValidator, userDto.getPassword(), "Password"));
        verifiers.add(new StringVerifier(emailValidator, userDto.getEmail(), "Email"));

        return verifiers;
    }




}
