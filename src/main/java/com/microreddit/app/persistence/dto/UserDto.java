package com.microreddit.app.persistence.dto;

import com.microreddit.app.persistence.validators.PasswordMatches;
import com.microreddit.app.persistence.validators.ValidEmail;
import com.microreddit.app.persistence.validators.ValidPassword;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * User transfer object for moving data from GUI to db securely. Validates input data.
 *
 * @author Josh Harkema
 */
@PasswordMatches
public class UserDto {
    @NotNull
    @NotEmpty
    private String username;

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

    @ValidPassword
    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", matchingPassword='" + matchingPassword + '\'' +
                '}';
    }

}
