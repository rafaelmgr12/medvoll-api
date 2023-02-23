package com.rafaelmgr12.medvollapi.domain.user;

import jakarta.persistence.*;
import lombok.*;

@Table(name="users")
@Entity(name="User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
}
