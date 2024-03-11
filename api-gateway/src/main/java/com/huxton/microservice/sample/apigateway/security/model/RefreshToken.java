package com.huxton.microservice.sample.apigateway.security.model;

import com.huxton.microservice.sample.apigateway.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_refresh_token")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 1000)
    private String token;
    private Date expiryDate;
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            unique = false
    )
    private User user;
}
