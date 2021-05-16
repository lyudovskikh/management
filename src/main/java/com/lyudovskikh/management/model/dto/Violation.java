package com.lyudovskikh.management.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Information about validation error")
public class Violation {

    @Schema(description = "Name of field that failed validation", example = "login")
    private final String fieldName;

    @Schema(description = "Validation failure message", example = "size must be between 1 and 50")
    private final String message;

    public Violation(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }
}
