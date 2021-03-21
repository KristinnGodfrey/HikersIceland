package is.hi.g.hikersicelands.hikersicelands.Controllers.RESTControllers;

import is.hi.g.hikersicelands.hikersicelands.Entities.Hike;
import is.hi.g.hikersicelands.hikersicelands.Entities.Profile;
import is.hi.g.hikersicelands.hikersicelands.Entities.Review;
import is.hi.g.hikersicelands.hikersicelands.Services.HikeService;
import is.hi.g.hikersicelands.hikersicelands.Services.ProfileService;
import is.hi.g.hikersicelands.hikersicelands.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
public class RESTReviewController {

    private ReviewService reviewService;
    private HikeService hikeService;
    private ProfileService profileService;

    @Autowired
    public RESTReviewController(ReviewService reviewService, HikeService hikeService, ProfileService profileService){
        this.reviewService = reviewService;
        this.hikeService = hikeService;
        this.profileService = profileService;
    }

    //Eyðir ákveðnu review
    @DeleteMapping(value ="rest/hikes/{hikeid}/reviews/{reviewid}")
    public ResponseEntity<Object> deleteReview(@PathVariable("hikeid") long hikeid, @PathVariable("reviewid") long id, HttpSession httpSession){
        Hike hike;
        Review review;
        try {
            hike = hikeService.findById(hikeid).orElse(null);
            review = reviewService.findById(id).orElse(null);
        } finally { }
        if (hike == null || review == null) {
            return ResponseEntity.notFound().build();
        }

        reviewService.deleteReviewById(id);
        hike = hikeService.findById(hikeid).orElse(null);
        return ResponseEntity.ok(hike);
    }

    //birtir öll review
    @RequestMapping(value = "rest/hikes/{id}/reviews", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<Object> getReviews(@PathVariable("id") long id, Model model, HttpSession httpSession){
        Hike hike;
        try {
            hike = hikeService.findById(id).orElse(null);
        } finally { }
        if (hike == null) {
            return ResponseEntity.notFound().build();
        }
        List<Review> reviews = hike.getReviews();
        return ResponseEntity.ok(reviews);
    }

    //Fall sem býr til review á ákveðið hike
    @PostMapping(
            value="rest/hikes/{id}/reviews",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = "application/json;charset=UTF-8")
    public ResponseEntity<Object> postReviews(@PathVariable("id") long id, @RequestBody Review review, HttpSession httpSession){
        Hike hike;
        try {
            hike = hikeService.findById(id).orElse(null);
        } finally { }
        if (hike == null) {
            return ResponseEntity.notFound().build();
        }
        Review saveReview = new Review(review.getReviewText(), review.getRating(), hike);
        reviewService.save(saveReview);
        Hike updatedHike = hikeService.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Hike Id"));
        return ResponseEntity.ok(updatedHike);
    }

}
