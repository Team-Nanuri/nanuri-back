package team.hackerping.nanuri.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Table(name = "MEMBER")
@Getter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @NotNull
    private String username;
    @NotNull
    private String encodedPassword;
    @NotNull
    @Enumerated(EnumType.STRING)
    private UserType userType;
}
