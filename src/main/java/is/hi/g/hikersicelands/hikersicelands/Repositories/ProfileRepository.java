package is.hi.g.hikersicelands.hikersicelands.Repositories;

import is.hi.g.hikersicelands.hikersicelands.Entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long>{
    Profile loginProfile(String username, String password);
    Profile createProfile(String username, String password);
    Profile privateMode(boolean priv);

    List<Profile> searchProfile(String name);
}
