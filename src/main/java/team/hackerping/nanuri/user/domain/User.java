package team.hackerping.nanuri.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MEMBER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String encodedPassword;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    private String profileImageUrl;

    private User(String username, String encodedPassword, UserType userType) {
        this.username = username;
        this.encodedPassword = encodedPassword;
        this.userType = userType;
        this.userStatus = UserStatus.BLOCKED;
    }

    public static User of(String username, String encodedPassword, UserType userType) {
        return new User(username, encodedPassword, userType);
    }

    public void uploadProfileImage(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void changeUserType(UserType userType) {
        this.userType = userType;
    }

    public void activate() {
        this.userStatus = UserStatus.ACTIVE;
    }
}
