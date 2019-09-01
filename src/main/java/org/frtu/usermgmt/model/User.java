package org.frtu.usermgmt.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The username
     */
    @NotNull
    @Size(max = 64)
    @Column(name = "username", nullable = false, updatable = false)
    private String username;

    /**
     * The password as an MD5 value
     */
    @NotNull
    @Size(max = 64)
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * The email
     */
    @NotNull
    @Size(max = 64)
    @Column(name = "email", nullable = false, updatable = true)
    private String email;

    private PaymentType paymentType = PaymentType.FREE;

    /**
     * If the user is deactivated?
     */
    @Column(name = "deactivated", nullable = false)
    private boolean deactivated = false;

    /**
     * Access level of the user.
     * 1 = Admin user
     * 2 = Regular user
     */
    @NotNull
    @Column(name = "accessType", nullable = false)
    private Integer accessType = 2;

    User() {
    }

    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", paymentType=" + paymentType +
                ", accessType=" + accessType +
                '}';
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getAccessType() {
        return accessType;
    }

    public void setAccessType(Integer accessType) {
        this.accessType = accessType;
    }

    public boolean isDeactivated() {
        return deactivated;
    }

    public void setDeactivated(boolean deactivated) {
        this.deactivated = deactivated;
    }
}
