package is.hi.g.hikersicelands.hikersicelands.Repositories;

import is.hi.g.hikersicelands.hikersicelands.Entities.Hike;
import is.hi.g.hikersicelands.hikersicelands.Entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface HikeRepository extends JpaRepository<Hike, Long> {
    Hike save(Hike hike);
    List<Hike> findAll();
    Optional<Hike> findById(Long id);
    @Transactional
    void deleteHikeById(Long id); // aðferð til að eyða hike
}
