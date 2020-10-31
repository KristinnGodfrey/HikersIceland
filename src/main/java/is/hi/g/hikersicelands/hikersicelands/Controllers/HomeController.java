package is.hi.g.hikersicelands.hikersicelands;

import is.hi.g.hikersicelands.hikersicelands.Entities.Hike;
import is.hi.g.hikersicelands.hikersicelands.Services.HikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class HomeController {

    private HikeService hikeService;
    @Autowired
    public HomeController(HikeService hikeService){
        this.hikeService = hikeService;
    }

    @RequestMapping("/")
    public String Home(Model model) {
        model.addAttribute("hikes",hikeService.viewAll());
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
}