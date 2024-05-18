package com.odanielfilho.securepassword.service;

import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class PasswordService {
    public List<String> validatePass(String pass){
        List<String> failures = new ArrayList<>();
        validateLength(pass, failures);
        validateUpperCase(pass, failures);
        validateLowerCase(pass, failures);
        validateNumber(pass, failures);
        validateSpecialCharacters(pass, failures);
        return failures;
    }

    private void validateSpecialCharacters(String pass, List<String> failures) {
        if (!Pattern.compile("[^\\p{L}\\d\\s_]").matcher(pass).find()) {
            failures.add("Password does not contain special character");
        }
    }

    private void validateNumber(String pass, List<String> failures) {
        if (!Pattern.matches(".*[0-9].*", pass)) {
            failures.add("A senha deve possuir pelo menos um número.");
        }
    }

    private void validateLowerCase(String pass, List<String> failures) {
        if (!Pattern.matches(".*[a-z].*", pass)) {
            failures.add("A senha deve possuir pelo menos uma letra minúscula.");
        }
    }

    private void validateUpperCase(String pass, List<String> failures) {
        if (!Pattern.matches(".*[A-Z].*", pass)) {
            failures.add("A senha deve possuir pelo menos uma letra maiúscula.");
        }
    }

    private void validateLength(String pass, List<String> failures) {
        if(StringUtils.isBlank(pass) || pass.length() < 8){
            failures.add("A senha deve possuir pelo menos 8 caracteres");
        }
    }


}
