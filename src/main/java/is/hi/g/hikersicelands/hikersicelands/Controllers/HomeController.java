package is.hi.g.hikersicelands.hikersicelands.Controllers;

import is.hi.g.hikersicelands.hikersicelands.Entities.Hike;
import is.hi.g.hikersicelands.hikersicelands.Services.HikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private HikeService hikeService;
    @Autowired
    public HomeController(HikeService hikeService){
        this.hikeService = hikeService;
    }

    @RequestMapping("/")
    public String Home(Model model) {
        model.addAttribute("hikes",hikeService.findAll());
        return "Welcome";
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

    @RequestMapping("/login")
    public String Login() {
        return "Login";
    }

    @RequestMapping("/signup")
    public String Signup() {
        return "Signup";
    }
}
