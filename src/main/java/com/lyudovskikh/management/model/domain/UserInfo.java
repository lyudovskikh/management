package com.lyudovskikh.management.model.domain;

import com.querydsl.core.annotations.QueryEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * User information
 */
@QueryEntity
@Entity
@Table(name = "USER_INFO")
public class UserInfo {

    /**
     * Identifier
     */
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    /**
     * Login
     */
    @Column(name = "LOGIN", length = 50, nullable = false, unique = true)
    private String login;

    /**
     * Email
     */
    @Column(name = "EMAIL", length = 50, nullable = false)
    private String email;

    /**
     * First name
     */
    @Column(name = "FIRST_NAME", length = 100, nullable = false)
    private String firstName;

    /**
     * Last name
     */
    @Column(name = "LAST_NAME", length = 100, nullable = false)
    private String lastName;

    /**
     * Middle name
     */
    @Column(name = "MIDDLE_NAME", length = 100)
    private String middleName;

    /**
     * Version
     */
    @Version
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
