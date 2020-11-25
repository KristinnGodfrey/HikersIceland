package is.hi.g.hikersicelands.hikersicelands.Controllers;

import is.hi.g.hikersicelands.hikersicelands.Entities.Hike;
import is.hi.g.hikersicelands.hikersicelands.Entities.Profile;
import is.hi.g.hikersicelands.hikersicelands.Services.ProfileService;
import is.hi.g.hikersicelands.hikersicelands.Services.HikeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserProfileController {

    private ProfileService profileService;
    private HikeService hikeService;

    @Autowired
    public UserProfileController(ProfileService profileService, HikeService hikeService){
        this.profileService = profileService;
        this.hikeService = hikeService;
    }

    @RequestMapping("/myprofile")
    public String myProfile(Model model, HttpSession httpSession) {
        // Get logged in username from session
        String sessionUsername = (String) httpSession.getAttribute("username");
        if(sessionUsername == null) {
            model.addAttribute("profile", new Profile());
            return "login";
        }

        Profile profile = profileService.searchProfileByUsername(sessionUsername);
        if(profile == null) {
            model.addAttribute("profile", new Profile());
            return "login";
        }

        model.addAttribute("profile", profile);
        return "myprofile";
    }

    @RequestMapping(value = "/myprofile", method = RequestMethod.POST)
    public String myProfileUpdate(@Valid Profile profile, BindingResult result, Model model, HttpSession httpSession){
        if(result.hasErrors()){
            return "login";
        }
        // delete old
        profileService.deleteProfileByUsername(profile.getUsername());

        // create new
        Profile newProfile = profileService.saveProfile(new Profile(profile.getUsername(), profile.getPassword(), profile.getName(), profile.getAge(), profile.getAdmin()));

        model.addAttribute("profile", newProfile);
        model.addAttribute("successMessage", "Updated successfully!");
        return "myProfile";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@Valid Profile profile, BindingResult result, Model model){
        if(result.hasErrors()){
            return "signup";
        }
        profileService.saveProfile(profile);
        model.addAttribute("hikes", hikeService.findAll());
        return "Welcome";
    }

    @RequestMapping(value="/signup", method = RequestMethod.GET)
    public String addSignupForm(Profile profile, Model model){
        model.addAttribute("Profile", new Profile());
        return "signup";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String login(@Valid Profile profile, BindingResult result, Model model){
        if(result.hasErrors() | profile.getUsername().equals(null) | profile.getPassword().equals(null)){
            return "login";
        }
        if (profileService.loginProfile(profile.getUsername(), profile.getPassword())){
            model.addAttribute("hikes", hikeService.findAll());
            return "Welcome";
        }
        else return "login";



    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String addLoginForm(Profile profile, Model model){
        model.addAttribute("Profile", new Profile());
        return "login";
    }
}

