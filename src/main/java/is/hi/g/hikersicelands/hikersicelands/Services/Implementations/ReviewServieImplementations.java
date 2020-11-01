package is.hi.g.hikersicelands.hikersicelands.Services.Implementations;

import is.hi.g.hikersicelands.hikersicelands.Entities.Review;
import is.hi.g.hikersicelands.hikersicelands.Repositories.ReviewRepository;
import is.hi.g.hikersicelands.hikersicelands.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServieImplementations implements ReviewService {

    ReviewRepository repository;

    @Autowired
    public ReviewServieImplementations(ReviewRepository reviewRepository){this.repository = reviewRepository;}

    @Override
    public Review save(Review review) {

        return repository.save(review);
    }

    @Override
    public void delete(Review review) {
        repository.delete(review);
    }

    @Override
    public List<Review> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Review> findByhikeId(String hikeId) {
        return repository.findByhikeId(hikeId);
    }


    @Override
    public Optional<Review> findById(Long id) {
        return repository.findById(id);
    }


}
