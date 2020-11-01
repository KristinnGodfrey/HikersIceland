package is.hi.g.hikersicelands.hikersicelands.Services;

import is.hi.g.hikersicelands.hikersicelands.Entities.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Review save(Review review);
    void delete(Review review);
    List<Review> findAll();
    List<Review> findByhikeId(String hikeId);
    Optional<Review> findById(Long id);
}
