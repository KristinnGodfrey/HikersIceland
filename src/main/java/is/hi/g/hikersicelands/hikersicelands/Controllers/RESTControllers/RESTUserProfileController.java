package is.hi.g.hikersicelands.hikersicelands.Controllers.RESTControllers;

import is.hi.g.hikersicelands.hikersicelands.Entities.Profile;
import is.hi.g.hikersicelands.hikersicelands.Services.HikeService;
import is.hi.g.hikersicelands.hikersicelands.Services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

//ber ábyrgð á login, signup og http session
@RestController
public class RESTUserProfileController {

    private ProfileService profileService;
    private HikeService hikeService;

    @Autowired
    public RESTUserProfileController(ProfileService profileService, HikeService hikeService){
        this.profileService = profileService;
        this.hikeService = hikeService;
    }

    //birtir viðeigandi myprofile view fyrir mismunandi profile/user
    @RequestMapping(value = "/rest/profile", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Object myProfile(Model model, HttpSession httpSession) {
        // Loggar inn í username frá session
        String sessionUsername = (String) httpSession.getAttribute("username");
        if(sessionUsername == null) {
            return new ResponseEntity<String>("User is not logged in", HttpStatus.UNAUTHORIZED);
        }

        Profile profile = profileService.searchProfileByUsername(sessionUsername);
        if(profile == null) {
            return new ResponseEntity<String>("Username not found", HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(profile);
    }

    //uppfærir user/profile upplýsingar ef þeim er breytt
    @RequestMapping(value = "/rest/profile", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Object myProfileUpdate(@Valid Profile profile, BindingResult result, Model model, HttpSession httpSession){
        if(result.hasErrors()){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        String sessionUsername = (String) httpSession.getAttribute("username");
        if(sessionUsername == null) {
            return new ResponseEntity<String>("User is not logged in", HttpStatus.UNAUTHORIZED);
        }

        // eyða gamla
        String oldPass = profileService.searchProfileByUsername(profile.getUsername()).getPassword();
        profileService.deleteProfileByUsername(profile.getUsername());

        if(!profile.getPassword().equals("")){
            // uppfæra password ef breytt
            oldPass = profile.getPassword();
        }
        // gera nýtt
        Profile newProfile = profileService.saveProfile(new Profile(profile.getUsername(), oldPass, profile.getName(), profile.getAge(), profile.getAdmin(), profile.getCompletedAchievements()));

        return ResponseEntity.ok(newProfile);
    }

    //handler fyrir signup view
    @PostMapping(
            value="/rest/signup",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = "application/json;charset=UTF-8")
    public Object signup(@RequestBody Profile profile, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        if(profile.getUsername() == null || profile.getUsername().isEmpty()) {
            return new ResponseEntity<String>("Username missing", HttpStatus.BAD_REQUEST);
        }
        if(profile.getPassword() == null || profile.getPassword().isEmpty()) {
            return new ResponseEntity<String>("Password missing", HttpStatus.BAD_REQUEST);
        }
        if (profileService.searchProfileByUsername(profile.getUsername()) == null){
            // Successfulprofile
            Profile newProfile = profileService.saveProfile(profile);
            return ResponseEntity.ok(newProfile);
        }
        return new ResponseEntity<String>("Username is not avalible", HttpStatus.BAD_REQUEST);
    }

    //handler fyrir login
    @PostMapping(
            value="/rest/login",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = "application/json;charset=UTF-8")
    public Object login(@RequestBody Profile profile, BindingResult result, Model model, HttpSession httpSession){
        if(result.hasErrors()){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        if(profile.getUsername() == null || profile.getUsername().isEmpty()) {
            return new ResponseEntity<String>("Username missing", HttpStatus.BAD_REQUEST);
        }
        if(profile.getPassword() == null || profile.getPassword().isEmpty()) {
            return new ResponseEntity<String>("Password missing", HttpStatus.BAD_REQUEST);
        }
        if (profileService.loginProfile(profile.getUsername(), profile.getPassword())){
            httpSession.setAttribute("username", profile.getUsername());
            Profile sessionProfile = profileService.searchProfileByUsername(profile.getUsername());
            return ResponseEntity.ok(sessionProfile);
        }
        else return new ResponseEntity<String>("Login failed", HttpStatus.UNAUTHORIZED);
    }
}

