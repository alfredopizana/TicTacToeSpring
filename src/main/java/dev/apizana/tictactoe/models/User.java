package dev.apizana.tictactoe.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "user")
@Data
@Builder
public class User implements Serializable {

    @Id
    @NotNull
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Long id;

    @Email(message = "Invalid email")
    @NotNull(message = "Email is required")
    @Column(unique = true)
    private String email;

    @NotNull(message = "password is required")
    private String password;

    @NotNull(message = "username is required")
    @Column(unique = true)
    private String username;

    @JsonIgnore
    private Boolean active = true;

    @CreatedDate
    private Instant createdDate = Instant.now();


    @LastModifiedDate
    private Instant modifiedDate = Instant.now();



}
