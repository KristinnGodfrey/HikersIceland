package is.hi.g.hikersicelands.hikersicelands.Controllers.RESTControllers;

import is.hi.g.hikersicelands.hikersicelands.Entities.Hike;
import is.hi.g.hikersicelands.hikersicelands.Services.HikeService;
import is.hi.g.hikersicelands.hikersicelands.Services.ItemService;
import is.hi.g.hikersicelands.hikersicelands.Services.ProfileService;
import is.hi.g.hikersicelands.hikersicelands.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
public class RESTHomeController {

    private HikeService hikeService;
    private ItemService itemService;
    private ReviewService reviewService;
    private ProfileService profileService;
    @Autowired
    public RESTHomeController(ReviewService reviewService, HikeService hikeService, ProfileService profileService){
        this.reviewService = reviewService;
        this.hikeService = hikeService;
        this.profileService = profileService;
    }

    @RequestMapping("/rest/hikes")
    public ResponseEntity<List<Hike>> Home(Model model, HttpSession httpSession) {

        String sessionUsername = (String) httpSession.getAttribute("username");
        if( sessionUsername == null){
            model.addAttribute("profile", null);
        } else {
            model.addAttribute("profile",profileService.searchProfileByUsername(sessionUsername));
        }
        List<Hike> hikes = hikeService.findAll();
        return ResponseEntity.ok(hikes);
    }

    @RequestMapping("/rest/hikes/{id}")
    public ResponseEntity<Object> getHike(@PathVariable("id") long id, Model model, HttpSession httpSession){
        Hike hike;
        try {
            hike = hikeService.findById(id).orElse(null);
        } finally { }

        if (hike == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(hike);
        }
    }

}