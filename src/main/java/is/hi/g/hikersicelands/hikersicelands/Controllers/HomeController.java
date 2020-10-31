package is.hi.g.hikersicelands.hikersicelands.Controllers;

import is.hi.g.hikersicelands.hikersicelands.Entities.Hike;
import is.hi.g.hikersicelands.hikersicelands.Services.HikeService;
import is.hi.g.hikersicelands.hikersicelands.Entities.Review;
import is.hi.g.hikersicelands.hikersicelands.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller
public class HomeController {

    private HikeService hikeService;
    private ReviewService reviewService;
    @Autowired
    public HomeController(ReviewService reviewService, HikeService hikeService){
        this.reviewService = reviewService;
        this.hikeService = hikeService;
    }

    @RequestMapping("/")
    public String Home(Model model) {
        return "Welcome";
    }

    @RequestMapping("/login")
    public String Login() {
        return "Login";
    }

    @RequestMapping("/signup")
    public String Signup() {
        return "Signup";
    }

//    @RequestMapping("/")
//    public String Reviews(Model model) {
//        model.addAttribute("reviews", reviewService.findAll());
//        return "Reviews";
//    }
    @RequestMapping(value = "/addreview", method = RequestMethod.POST)
    public String addReview(@Valid Review review, BindingResult result, Model model){
        if(result.hasErrors()){
            return "Reviews";
        }
        reviewService.save(review);
        model.addAttribute("reviews", reviewService.findAll());
        return "add-review";
    }

    @RequestMapping(value ="/addhike", method = RequestMethod.POST)
    public String addHike(@Valid Hike hike, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add-hike";
        }
        hikeService.save(hike);
        model.addAttribute("hikes", hikeService.findAll());
        return "Welcome";
    }
    @RequestMapping(value="/addhike", method = RequestMethod.GET)
    public String addHikeForm(Hike hike){
        return "add-hike";
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteReview(@PathVariable("id") long id, Model model){
        Review review = reviewService.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Review Id"));
        reviewService.delete(review);
        model.addAttribute("reviews", reviewService.findAll());
        return "Reviews";
    }
}