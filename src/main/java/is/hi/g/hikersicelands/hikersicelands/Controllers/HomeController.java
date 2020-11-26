package is.hi.g.hikersicelands.hikersicelands.Controllers;

import is.hi.g.hikersicelands.hikersicelands.Entities.Achievement;
import is.hi.g.hikersicelands.hikersicelands.Entities.Hike;
import is.hi.g.hikersicelands.hikersicelands.Entities.Profile;
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
import java.util.Collections;
import java.util.List;


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
        model.addAttribute("hikes", hikeService.findAll());
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


    @RequestMapping(value ="/addhike", method = RequestMethod.POST)
    public String addHike(@Valid Hike hike, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add-hike";
        }
        hikeService.save(hike);
        model.addAttribute("hikes", hikeService.findAll());
        return "add-hike";
    }

    @RequestMapping(value="/addhike", method = RequestMethod.GET)
    public String addHikeForm(Hike hike, Model model){
        model.addAttribute("hikes", hikeService.findAll());
        return "add-hike";
    }

    @RequestMapping("/hike/{id}")
    public String getHike(@PathVariable("id") long id, Model model){
        Hike hike = hikeService.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Hike Id"));
        model.addAttribute("hike", hike);
        Achievement achievement = new Achievement();
        model.addAttribute("achievement", achievement);
        return "Hike";
    }
    @RequestMapping(value ="/addhike/{hikeid}", method = RequestMethod.POST)
    public String deleteHike(@PathVariable("hikeid") long hikeid, @Valid Hike hike, BindingResult result, Model model){
        if(result.hasErrors()){
            return "welcome";
        }
        hikeService.deleteHikeById(hikeid);
        model.addAttribute("hikes", hikeService.findAll());
        return "add-hike";
    }


}