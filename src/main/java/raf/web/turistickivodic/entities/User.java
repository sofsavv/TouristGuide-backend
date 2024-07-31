package raf.web.turistickivodic.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User {

    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    private String email;
    @NotNull(message = "First name is required")
    @NotEmpty(message = "First name is required")
    private String firstName;
    @NotNull(message = "Last name is required")
    @NotEmpty(message = "Last name is required")
    private String lastName;
    @NotNull(message = "Role is required")
    @NotEmpty(message = "Role is required")
    private String role;
    private boolean active; //status
    private String hashedPassword;

    private User(){}

    public User(String email, String firstName, String lastName, String role, boolean active, String hashedPassword) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.active = active;
        this.hashedPassword = hashedPassword;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}
