package com.example.accountservice.dto;

import jakarta.validation.constraints.*;

public class UserInputDTO {
    @NotBlank(message = "\"firstName\" ne peut pas être vide")
    @NotNull(message = "\"firstName\" ne peut pas être null")
    private String firstName;
    @NotBlank(message = "\"lastName\" ne peut pas être vide")
    @NotNull(message = "\"password\" ne peut pas être null")
    private String lastName;
    @NotBlank(message = "\"email\" ne peut pas être vide")
    @NotNull(message = "\"password\" ne peut pas être null")
    @Email(message = "Vous devez fournir un mail valide")
    private String mail;
    @NotNull(message = "\"password\" ne peut pas être null")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@$!%*?&-_,.;'€£])[A-Za-z\\\\d@$!%*?&-_,.;'€£]{8,}$",
    message = "Votre mot de passe doit contenir au moins 8 caractères, au moins un caractère minuscule, au moins un caractère majuscule, au moins un caractère spécial, au moins un chiffre")
    private String password;
    @Digits(integer = 10, fraction = 0, message = "Vous devez fournir un numéro de téléphone à 10 chiffres")
    @NotBlank(message = "\"phoneNumber\" ne peut pas être vide")
    @NotNull(message = "\"phoneNumber\" ne peut pas être null")
    private String phoneNumber;


    public UserInputDTO() {
    }


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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
