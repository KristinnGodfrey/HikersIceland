package is.hi.g.hikersicelands.hikersicelands;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
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
}
