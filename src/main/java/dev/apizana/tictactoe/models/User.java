package dev.apizana.tictactoe.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity(name = "user")
@Data
@Builder
public class User {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long id;
    @Email
    private String email;
    private String password;
    private String username;

    private Boolean active = true;

    //@JsonIgnore
    @CreatedDate
    private Instant createdDate = Instant.now();

    //@JsonIgnore
    @LastModifiedDate
    private Instant modifiedDate = Instant.now();


}
