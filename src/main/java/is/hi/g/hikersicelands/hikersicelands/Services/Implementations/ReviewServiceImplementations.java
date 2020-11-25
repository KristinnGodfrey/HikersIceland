package is.hi.g.hikersicelands.hikersicelands.Services.Implementations;

import is.hi.g.hikersicelands.hikersicelands.Entities.Review;
import is.hi.g.hikersicelands.hikersicelands.Repositories.ReviewRepository;
import is.hi.g.hikersicelands.hikersicelands.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImplementations implements ReviewService {

    ReviewRepository repository;

    @Autowired
    public ReviewServiceImplementations(ReviewRepository reviewRepository){this.repository = reviewRepository;}

    //Fall til þess að bæta við review í repository
    @Override
    public Review save(Review review) {

        return repository.save(review);
    }
    //Fall til þess að eyða review, vantar að bæta við að admin geta bara notað þetta fall
    @Override
    public void deleteReviewById(Long id) {
        repository.deleteReviewById(id);
    }

    //Fall til að birta öll review í repository
    @Override
    public List<Review> findAll() {
        return repository.findAll();
    }

    //Fall til að birta öll Review fyrir ákveðið hike.
    @Override
    public List<Review> findByhikeId(String hikeId) {
        return repository.findByhikeId(hikeId);
    }

    //Fall til að birta ákveðið review
    @Override
    public Optional<Review> findById(Long id) {
        return repository.findById(id);
    }


}
