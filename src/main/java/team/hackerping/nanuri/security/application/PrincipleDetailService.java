package team.hackerping.nanuri.security.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import team.hackerping.nanuri.security.application.dto.PrincipleDetails;
import team.hackerping.nanuri.user.persistence.UserRepository;

@RequiredArgsConstructor
@Slf4j
public class PrincipleDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("UserNotFound"));
        log.info(user.toString());
        return PrincipleDetails.from(user);
    }
}
