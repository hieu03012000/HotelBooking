/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieunt.tbluser;

/**
 *
 * @author HIEUNGUYEN
 */
public class UserCreateError {
    private String emailExistedError;
    private String emailFormatError;
    private String passwordLengthError;
    private String confirmNotMatch;
    private String nameLengthError;
    private String phoneError;
    private String addLengthError;

    public UserCreateError() {
    }

    public UserCreateError(String emailExistedError, String emailFormatError, String passwordLengthError, String confirmNotMatch, String nameLengthError, String phoneError, String addLengthError) {
        this.emailExistedError = emailExistedError;
        this.emailFormatError = emailFormatError;
        this.passwordLengthError = passwordLengthError;
        this.confirmNotMatch = confirmNotMatch;
        this.nameLengthError = nameLengthError;
        this.phoneError = phoneError;
        this.addLengthError = addLengthError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public String getAddLengthError() {
        return addLengthError;
    }

    public void setAddLengthError(String addLengthError) {
        this.addLengthError = addLengthError;
    }

    public String getConfirmNotMatch() {
        return confirmNotMatch;
    }

    public String getEmailExistedError() {
        return emailExistedError;
    }

    public String getEmailFormatError() {
        return emailFormatError;
    }

    public String getNameLengthError() {
        return nameLengthError;
    }

    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    public void setConfirmNotMatch(String confirmNotMatch) {
        this.confirmNotMatch = confirmNotMatch;
    }

    public void setEmailExistedError(String emailExistedError) {
        this.emailExistedError = emailExistedError;
    }

    public void setEmailFormatError(String emailFormatError) {
        this.emailFormatError = emailFormatError;
    }

    public void setNameLengthError(String nameLengthError) {
        this.nameLengthError = nameLengthError;
    }

    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }
    
    
}
