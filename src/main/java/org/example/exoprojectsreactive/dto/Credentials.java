package org.example.exoprojectsreactive.dto;

public class Credentials {
    private String username;
    private String password;

    public Credentials(){}


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isValid(){
        return username.equals("user") && password.equals("password");
    }
}
