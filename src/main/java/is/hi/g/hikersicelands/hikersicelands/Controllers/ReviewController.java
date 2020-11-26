package is.hi.g.hikersicelands.hikersicelands.Controllers;

import antlr.ASTNULLType;
import is.hi.g.hikersicelands.hikersicelands.Entities.*;
import is.hi.g.hikersicelands.hikersicelands.Services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import is.hi.g.hikersicelands.hikersicelands.Entities.Review;
import is.hi.g.hikersicelands.hikersicelands.Services.ReviewService;
import is.hi.g.hikersicelands.hikersicelands.Services.HikeService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;




@Controller
public class ReviewController {

    private ReviewService reviewService;
    private HikeService hikeService;
    private ProfileService profileService;

    @Autowired
    public ReviewController(ReviewService reviewService, HikeService hikeService, ProfileService profileService){
        this.reviewService = reviewService;
        this.hikeService = hikeService;
        this.profileService = profileService;
    }
    @RequestMapping(value ="/hike/{hikeid}/review/{reviewid}", method = RequestMethod.POST)
    public String deleteReview(@PathVariable("hikeid") long hikeid, @PathVariable("reviewid") long id, @Valid Review review, BindingResult result, Model model, HttpSession httpSession){
        if(result.hasErrors()){
            return "welcome";
        }

        reviewService.deleteReviewById(id);
        Hike hike = hikeService.findById(hikeid).orElseThrow(()->new IllegalArgumentException("Invalid Hike Id"));
        model.addAttribute("hike", hike);
        model.addAttribute("achievement", new Achievement());
        String sessionUsername = (String) httpSession.getAttribute("username");
        if(sessionUsername != null) {
            Profile sessionProfile = profileService.searchProfileByUsername(sessionUsername);
            model.addAttribute("profile", sessionProfile);
        }
        return "Reviews";
    }
    @RequestMapping("/hike/{id}/reviews")
    public String getReviews(@PathVariable("id") long id, Model model, HttpSession httpSession){
        Hike hike = hikeService.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Hike Id"));
        model.addAttribute("hike", hike);
        Review review = new Review();
        model.addAttribute("review", review);
        String sessionUsername = (String) httpSession.getAttribute("username");
        if(sessionUsername != null) {
            Profile sessionProfile = profileService.searchProfileByUsername(sessionUsername);
            model.addAttribute("profile", sessionProfile);
        }
        return "Reviews";
    }
    @RequestMapping(value = "/hike/{id}/reviews", method = RequestMethod.POST)
    public String postReviews(@PathVariable("id") long id, @Valid Review review, BindingResult result, Model model, HttpSession httpSession){
        Hike hike = hikeService.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Hike Id"));
        Review saveReview = new Review(review.getReviewText(), review.getRating(), hike);
        reviewService.save(saveReview);
        Hike updatedHike = hikeService.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Hike Id"));
        model.addAttribute("hike", updatedHike);
        model.addAttribute("review", new Review());
        String sessionUsername = (String) httpSession.getAttribute("username");
        if(sessionUsername != null) {
            Profile sessionProfile = profileService.searchProfileByUsername(sessionUsername);
            model.addAttribute("profile", sessionProfile);
        }
        return "Reviews";
    }

}
