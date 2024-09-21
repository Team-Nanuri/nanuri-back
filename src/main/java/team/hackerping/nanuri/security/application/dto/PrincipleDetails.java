package team.hackerping.nanuri.security.application.dto;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import team.hackerping.nanuri.user.domain.User;
import team.hackerping.nanuri.user.domain.UserStatus;

@ToString
public class PrincipleDetails implements UserDetails {
    private final Long id;
    private final String username;
    private final String password;
    private final UserStatus status;

    private PrincipleDetails(Long id, String username, String password, UserStatus status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public static PrincipleDetails from(User user) {
        return new PrincipleDetails(
                user.getId(),
                user.getUsername(),
                user.getEncodedPassword(),
                user.getUserStatus()
        );
    }

    public Map<String, Object> getClaims() {
        return Map.of("id", id, "username", username, "status", status);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(status);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
