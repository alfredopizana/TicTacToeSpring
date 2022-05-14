package dev.apizana.tictactoe.models;

import javax.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Integer id;
    private String email;
    private String password;
    private String username;
    private Boolean active;

    @CreatedDate
    private Instant createdDate;

    @LastModifiedDate
    private Instant modifiedDate;

    //setters and getters
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    //constructor
    public User(){

    }
}
