package is.hi.g.hikersicelands.hikersicelands.Repositories;

import is.hi.g.hikersicelands.hikersicelands.Entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review save(Review review);
    void delete(Review review);
    List<Review> findAll();
    List<Review> findByhikeId(String hikeId);
    Optional<Review> findById(Long id);
}
