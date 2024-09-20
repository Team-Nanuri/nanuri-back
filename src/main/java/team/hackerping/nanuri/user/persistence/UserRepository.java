package team.hackerping.nanuri.user.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.hackerping.nanuri.user.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
