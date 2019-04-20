package com.cinema.validator;

import com.cinema.model.dto.UserDto;
import com.cinema.validator.typeValidator.StringValidator;

import java.util.ResourceBundle;

public class UserRegistrationDataValidator extends ModelValidator<UserDto> {

    public UserRegistrationDataValidator() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("regexpValidator");
        StringValidator emailValidator = new StringValidator(resourceBundle.getString("regexEmail"), "Wrong email");
        StringValidator firstNameValidator = new StringValidator(resourceBundle.getString("regexString"), "Wrong first name");
        StringValidator lastNameValidator = new StringValidator(resourceBundle.getString("regexString"), "Wrong last name ");
        StringValidator middleNameValidator = new StringValidator(resourceBundle.getString("regexString"), "Wrong middle name");
        StringValidator nicknameValidator = new StringValidator(resourceBundle.getString("regexStringNumber"), "Wrong nickname ");
        StringValidator passwordValidator = new StringValidator(resourceBundle.getString("regexStringNumber"), "Wrong Password");
        StringValidator phoneValidator = new StringValidator(resourceBundle.getString("regexPhoneNumber"), "Wrong phone");

        validators.put(passwordValidator, UserDto::getPassword);
        validators.put(emailValidator, UserDto::getEmail);
        validators.put(firstNameValidator, UserDto::getFirstName);
        validators.put(lastNameValidator, UserDto::getLastName);
        validators.put(middleNameValidator, UserDto::getMiddleName);
        validators.put(nicknameValidator, UserDto::getLogin);
        validators.put(phoneValidator, UserDto::getPhone);
    }
}
