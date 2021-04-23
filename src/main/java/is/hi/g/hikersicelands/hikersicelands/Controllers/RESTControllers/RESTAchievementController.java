package is.hi.g.hikersicelands.hikersicelands.Controllers.RESTControllers;

import is.hi.g.hikersicelands.hikersicelands.Entities.Achievement;
import is.hi.g.hikersicelands.hikersicelands.Entities.Hike;
import is.hi.g.hikersicelands.hikersicelands.Entities.Profile;
import is.hi.g.hikersicelands.hikersicelands.Services.AchievementService;
import is.hi.g.hikersicelands.hikersicelands.Services.HikeService;
import is.hi.g.hikersicelands.hikersicelands.Services.ProfileService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class RESTAchievementController {

    private HikeService hikeService;
    private AchievementService achievementService;
    private ProfileService profileService;

    @Autowired
    public RESTAchievementController(HikeService hikeService, AchievementService achievementService, ProfileService profileService) {
        this.hikeService = hikeService;
        this.achievementService = achievementService;
        this.profileService = profileService;
    }

    @RequestMapping(value = "/rest/hikes/{hikeid}/achievements/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")

    public ResponseEntity<Object> getAchievement(@PathVariable("id") long id, Model model, HttpSession httpSession) {
        Achievement achievement;
        try {
            achievement = achievementService.findAchievementById(id);
        } finally {
        }

        if (achievement == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(achievement);
        }
    }

    @PostMapping(value = "rest/hikes/{hikeid}/achievements/{achievementid}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})

    public Object completeAchievement(@PathVariable("hikeid") long hikeid, @PathVariable("achievementid") long id, @RequestBody JSONObject obj, HttpSession httpSession) {

        String sessionUsername = obj.getAsString("username");


        Achievement achievement;
        Hike hike;
        try {
            hike = hikeService.findById(hikeid).orElse(null);
            achievement = achievementService.findAchievementById(id);
        } finally {
        }
        if (hike == null || achievement == null) {
            return ResponseEntity.notFound().build();
        }

        // Find profile
        Profile profile = profileService.searchProfileByUsername(sessionUsername);
        List<Achievement> completedAchievements = profile.getCompletedAchievements();

        // Add or remove achievement
        if (completedAchievements.contains(achievement)) {
            completedAchievements.remove(achievement);
        } else {
            completedAchievements.add(achievement);
        }

        profile = profileService.saveProfile(profile);
        return ResponseEntity.ok(profile);
    }
}
