package com.application.dto;

/**
 * Data transfer object for all validation errors
 */
public class ValidationErrorDTO {

    private String field;
    private String message;

    /**
     * Constructor
     *
     * @param field   JSONPath to field
     * @param message validation error message
     */
    public ValidationErrorDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
