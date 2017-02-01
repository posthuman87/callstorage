package com.application.dto;

/**
 * Data Transfer Object for receiving call information
 */
public class CallDTO {

    /**
     * First name of a caller
     */
    private String firstName;

    /**
     * Last name of a caller
     */
    private String lastName;

    /**
     * Phone number of a caller
     */
    private String phoneNumber;

    /**
     * Formatted phone number
     */
    private String formattedPhoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFormattedPhoneNumber() {
        return formattedPhoneNumber;
    }

    public void setFormattedPhoneNumber(String formattedPhoneNumber) {
        this.formattedPhoneNumber = formattedPhoneNumber;
    }
}
