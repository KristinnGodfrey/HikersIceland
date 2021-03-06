package is.hi.g.hikersicelands.hikersicelands.Controllers;

import is.hi.g.hikersicelands.hikersicelands.Entities.*;
import is.hi.g.hikersicelands.hikersicelands.Services.HikeService;
import is.hi.g.hikersicelands.hikersicelands.Services.ItemService;
import is.hi.g.hikersicelands.hikersicelands.Entities.Review;
import is.hi.g.hikersicelands.hikersicelands.Services.ProfileService;
import is.hi.g.hikersicelands.hikersicelands.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Controller
public class HomeController {

    private HikeService hikeService;
    private ItemService itemService;
    private ReviewService reviewService;
    private ProfileService profileService;
    @Autowired
    public HomeController(ReviewService reviewService, HikeService hikeService, ProfileService profileService){
        this.reviewService = reviewService;
        this.hikeService = hikeService;
        this.profileService = profileService;
    }

    @RequestMapping("/")
    public String Home(Model model, HttpSession httpSession) {

        String sessionUsername = (String) httpSession.getAttribute("username");
        if( sessionUsername == null){
            model.addAttribute("profile", null);
        } else {
            model.addAttribute("profile",profileService.searchProfileByUsername(sessionUsername));
        }
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
        model.addAttribute("hike", new Hike());
        return "add-hike";
    }

    @RequestMapping(value="/addhike", method = RequestMethod.GET)
    public String addHikeForm(Hike hike, Model model){
        model.addAttribute("hikes", hikeService.findAll());
        return "add-hike";
    }

    @RequestMapping("/hike/{id}")
    public String getHike(@PathVariable("id") long id, Model model, HttpSession httpSession){
        Hike hike = hikeService.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Hike Id"));
        model.addAttribute("hike", hike);
        Achievement achievement = new Achievement();
        model.addAttribute("achievement", achievement);
        Item item = new Item();
        model.addAttribute("item", item);
        String sessionUsername = (String) httpSession.getAttribute("username");
        if(sessionUsername != null) {
            Profile sessionProfile = profileService.searchProfileByUsername(sessionUsername);
            model.addAttribute("profile", sessionProfile);
        }
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

//    @PostConstruct
//    public void init() {
//        List<Achievement> achievements = new ArrayList<Achievement>();
//        Hike hikeObject = new Hike(1, "coolhike", "fun","loccc","imgg", achievements);
//
//
//
//        Item itemObject = new Item("nasdasdame", "descasdasdaription", ItemType.MOUNTAIN, "imadsasdage", hikeObject);
//        itemService.save(itemObject);
//        hikeService.save(hikeObject);
//    }
}