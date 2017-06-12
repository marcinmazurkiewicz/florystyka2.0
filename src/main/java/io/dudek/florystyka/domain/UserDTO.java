package io.dudek.florystyka.domain;

import io.dudek.florystyka.validation.UniqueMail;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class UserDTO {

    @NotNull
    @UniqueMail
    private String mail;

    @NotNull
    @Size(min=8, max=32)
    private String password;

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
