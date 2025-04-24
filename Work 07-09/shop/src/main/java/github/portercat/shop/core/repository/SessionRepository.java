package github.portercat.shop.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import github.portercat.shop.core.model.entity.Session;
import github.portercat.shop.core.model.entity.user.User;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long>
{
    Optional<Session> findByToken(String token);
    Optional<Session> findByUser(User user);
}
