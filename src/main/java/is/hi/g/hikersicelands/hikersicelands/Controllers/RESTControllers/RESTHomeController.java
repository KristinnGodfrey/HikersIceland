package is.hi.g.hikersicelands.hikersicelands.Controllers.RESTControllers;

import is.hi.g.hikersicelands.hikersicelands.Entities.Hike;
import is.hi.g.hikersicelands.hikersicelands.Services.HikeService;
import is.hi.g.hikersicelands.hikersicelands.Services.ItemService;
import is.hi.g.hikersicelands.hikersicelands.Services.ProfileService;
import is.hi.g.hikersicelands.hikersicelands.Services.ReviewService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
    @Autowired
    private ServletContext servletContext;

    @RequestMapping( value="/rest/hikes", produces = "application/json;charset=UTF-8")
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

    @RequestMapping(value = "/rest/hikes/{id}", produces = "application/json;charset=UTF-8")
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


    @RequestMapping(value = "/rest/hikes/{id}/image", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public void getHikeImage(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
        String path = "image/" + id + ".jpg";
        ClassPathResource imgFile = new ClassPathResource(path);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
    }

}