package com.huxton.microservice.sample.userservice.user.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(
        name = "t_user",
        indexes = {
                @Index(name = "idx_email_name", columnList = "email,name")
        }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "email", unique = true, updatable = false)
    private String email;
    private String password;
}
