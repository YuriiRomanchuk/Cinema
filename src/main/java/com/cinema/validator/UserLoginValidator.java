package com.cinema.validator;

import com.cinema.model.dto.UserDto;
import com.cinema.validator.typeValidator.StringValidator;

import java.util.ResourceBundle;

public class UserLoginValidator extends ModelValidator<UserDto> {

    public UserLoginValidator() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("regexpValidator");
        StringValidator emailValidator = new StringValidator(resourceBundle.getString("regexEmail"), "Wrong email ");
        StringValidator passwordValidator = new StringValidator(resourceBundle.getString("regexStringNumber"), "Wrong Password");
        validators.put(passwordValidator, UserDto::getPassword);
        validators.put(emailValidator, UserDto::getEmail);
    }
}
