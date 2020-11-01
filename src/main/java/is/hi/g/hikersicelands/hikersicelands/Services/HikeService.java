package is.hi.g.hikersicelands.hikersicelands.Services;

import is.hi.g.hikersicelands.hikersicelands.Entities.Hike;

import java.util.List;
import java.util.Optional;

public interface HikeService {
    Hike save(Hike hike);
    List<Hike> findAll();
    Optional<Hike> findById(Long id);
}
