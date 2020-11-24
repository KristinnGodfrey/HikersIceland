package is.hi.g.hikersicelands.hikersicelands.Repositories;

import is.hi.g.hikersicelands.hikersicelands.Entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long>{
    Profile save(Profile profile);
    List<Profile> searchProfileByName(String name);
}
