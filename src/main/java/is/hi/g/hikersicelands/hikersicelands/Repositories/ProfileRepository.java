package is.hi.g.hikersicelands.hikersicelands.Repositories;

import is.hi.g.hikersicelands.hikersicelands.Entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long>{
    Profile save(Profile profile);
    Profile findByUsername(String username);
    List<Profile> findByName(String name);
    @Transactional
    void deleteByUsername(String username);
}
