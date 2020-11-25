package is.hi.g.hikersicelands.hikersicelands.Controllers;

import antlr.ASTNULLType;
import is.hi.g.hikersicelands.hikersicelands.Entities.Achievement;
import is.hi.g.hikersicelands.hikersicelands.Entities.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import is.hi.g.hikersicelands.hikersicelands.Entities.Review;
import is.hi.g.hikersicelands.hikersicelands.Services.ReviewService;
import is.hi.g.hikersicelands.hikersicelands.Entities.Hike;
import is.hi.g.hikersicelands.hikersicelands.Services.HikeService;

import javax.validation.Valid;




@Controller
public class ReviewController {

    private ReviewService reviewService;
    private HikeService hikeService;

    @Autowired
    public ReviewController(ReviewService reviewService, HikeService hikeService){
        this.reviewService = reviewService;
        this.hikeService = hikeService;
    }
    @RequestMapping(value ="/hike/{hikeid}/review/{reviewid}", method = RequestMethod.POST)
    public String deleteReview(@PathVariable("hikeid") long hikeid, @PathVariable("reviewid") long id, @Valid Review review, BindingResult result, Model model){
        if(result.hasErrors()){
            return "welcome";
        }

        reviewService.deleteReviewById(id);
        Hike hike = hikeService.findById(hikeid).orElseThrow(()->new IllegalArgumentException("Invalid Hike Id"));
        model.addAttribute("hike", hike);
        model.addAttribute("achievement", new Achievement());
        return "Reviews";
    }
    @RequestMapping("/hike/{id}/reviews")
    public String getReviews(@PathVariable("id") long id, Model model){
        Hike hike = hikeService.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Hike Id"));
        model.addAttribute("hike", hike);
        Review review = new Review();
        model.addAttribute("review", review);
        return "Reviews";
    }
    @RequestMapping(value = "/hike/{id}/reviews", method = RequestMethod.POST)
    public String postReviews(@PathVariable("id") long id, @Valid Review review, BindingResult result, Model model){
        Hike hike = hikeService.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Hike Id"));
        Review saveReview = new Review(review.getReviewText(), review.getRating(), hike);
        reviewService.save(saveReview);
        Hike updatedHike = hikeService.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Hike Id"));
        model.addAttribute("hike", updatedHike);
        model.addAttribute("review", new Review());
        return "Reviews";
    }

}
