package com.lyudovskikh.management.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Schema(description = "User info")
public class UserInfoDto {

    @NotNull
    @Positive
    @Schema(description = "ID", example = "1")
    private Long id;

    @NotBlank
    @Size(min = 1, max = 50)
    @Schema(description = "Login", example = "lyudovskikh")
    private String login;

    @NotBlank
    @Email
    @Size(min = 1, max = 50)
    @Schema(description = "Email", example = "dmitry.lyudovskikh@gmail.com")
    private String email;

    @NotBlank
    @Size(min = 1, max = 100)
    @Schema(description = "First name", example = "Dmitry")
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 100)
    @Schema(description = "Last name", example = "Lyudovskikh")
    private String lastName;

    @NotBlank
    @Size(min = 1, max = 100)
    @Schema(description = "Middle name", example = "Sergeevich")
    private String middleName;

    @NotNull
    @PositiveOrZero
    @Schema(description = "Entity version", example = "0")
    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
