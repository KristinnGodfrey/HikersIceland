package is.hi.g.hikersicelands.hikersicelands.Controllers;

import is.hi.g.hikersicelands.hikersicelands.Entities.Profile;
import is.hi.g.hikersicelands.hikersicelands.Services.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sun.rmi.runtime.NewThreadAction;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserProfileController {

    private ProfileService profileService;

    @Autowired
    public UserProfileController(ProfileService profileService){
        this.profileService = profileService;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@Valid Profile profile, BindingResult result, Model model){
        if(result.hasErrors()){
            return "signup";
        }
        profileService.saveProfile(profile);
        return "Welcome";
    }

    @RequestMapping(value="/signup", method = RequestMethod.GET)
    public String addSignupForm(Profile profile, Model model){
        model.addAttribute("Profile", new Profile());
        return "signup";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(@Valid Profile profile, BindingResult result, Model model){
        if(result.hasErrors()){
            return "login";
        }
        profileService.loginProfile(profile.getUsername(), profile.getPassword());
        return "Welcome";
    }
}

