package com.lyudovskikh.management.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Schema(description = "User info creation request")
public class UserInfoCreationRequest {

    @NotBlank
    @Size(min = 1, max = 50)
    @Schema(description = "Login", example = "lyudovskikh-ds")
    private String login;

    @NotBlank
    @Email
    @Size(min = 1, max = 50)
    @Schema(description = "Email", example = "lyudovskikh.d.s@gmail.com")
    private String email;

    @NotBlank
    @Size(min = 1, max = 100)
    @Schema(description = "First name", example = "Dmitry")
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 100)
    @Schema(description = "Last name", example = "Lyudovskikh")
    private String lastName;

    @Size(min = 1, max = 100)
    @Schema(description = "Middle name", example = "Sergeevich")
    private String middleName;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
}
