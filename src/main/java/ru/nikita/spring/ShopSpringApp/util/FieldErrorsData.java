package ru.nikita.spring.ShopSpringApp.util;

import org.springframework.validation.FieldError;

import java.util.List;

public class FieldErrorsData {
    public static String getErrorsData(List<FieldError> fieldErrors) {
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            sb.append(fieldError.getField())
                    .append(" - ")
                    .append(fieldError.getDefaultMessage())
                    .append("; ");
        }
        return sb.toString();
    }
}
