package is.hi.g.hikersicelands.hikersicelands.Controller;

import is.hi.g.hikersicelands.hikersicelands.Entities.Review;
import is.hi.g.hikersicelands.hikersicelands.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {

    private ReviewService reviewService;
    @Autowired
    public HomeController(ReviewService reviewService){this.reviewService = reviewService;}
    @RequestMapping("/")
    public String Home() {
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

    @RequestMapping("/Review")
    public String Reviews(Model model){
        model.addAttribute("reviews", reviewService.findAll());
        return "Reviews"
    }
    @RequestMapping(value ="/addreview", method = RequestMethod.POST)
    public String addReview(@Valid Review review, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("error") //á eftir að útfæra "error"
        }
        reviewService.save(review);
        model.addAttribute("review", reviewService.findAll());
        return "Reviews"
    }
    @RequestMapping(value="/addreview",method = RequestMethod.GET)
    public String addReviewForm(Model model){
        return "add-review"
    }
}
