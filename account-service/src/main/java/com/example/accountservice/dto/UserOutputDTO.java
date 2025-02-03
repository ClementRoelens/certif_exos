package com.example.accountservice.dto;

import com.example.accountservice.entity.User;

public class UserOutputDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String mail;
    private String phoneNumber;


    public UserOutputDTO() {
    }

    public UserOutputDTO(User user){
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        mail = user.getUsername();
        phoneNumber = user.getPhoneNumber();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
