package is.hi.g.hikersicelands.hikersicelands.Repositories;

import is.hi.g.hikersicelands.hikersicelands.Entities.Hike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HikeRepository extends JpaRepository<Hike, Long> {
    Hike save(Hike hike);
    List<Hike> findAll();
}
