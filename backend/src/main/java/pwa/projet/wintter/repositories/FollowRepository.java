package pwa.projet.wintter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pwa.projet.wintter.models.Follow;
import pwa.projet.wintter.models.User;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long>
{
    Optional<Follow> findFollowByFollowerIsAndFollowingIs(User follower, User following);
    void deleteByFollowerAndFollowing(User follower, User following);

}
