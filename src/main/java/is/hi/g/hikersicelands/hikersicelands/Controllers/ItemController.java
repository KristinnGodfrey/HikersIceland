package is.hi.g.hikersicelands.hikersicelands.Controllers;

import is.hi.g.hikersicelands.hikersicelands.Entities.Achievement;
import is.hi.g.hikersicelands.hikersicelands.Entities.Hike;
import is.hi.g.hikersicelands.hikersicelands.Entities.Item;
import is.hi.g.hikersicelands.hikersicelands.Entities.Profile;
import is.hi.g.hikersicelands.hikersicelands.Services.AchievementService;
import is.hi.g.hikersicelands.hikersicelands.Services.HikeService;
import is.hi.g.hikersicelands.hikersicelands.Services.ItemService;
import is.hi.g.hikersicelands.hikersicelands.Services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class ItemController {

    private HikeService hikeService;
    private ItemService itemService;
    private ProfileService profileService;

    @Autowired
    public ItemController(HikeService hikeService, ItemService itemService, ProfileService profileService){
        this.hikeService = hikeService;
        this.itemService = itemService;
        this.profileService = profileService;
    }

    @RequestMapping(value ="/hike/{id}/item", method = RequestMethod.POST)
    public String additem(@PathVariable("id") long id, @Valid Item item, BindingResult result, Model model, HttpSession httpSession){
        if(result.hasErrors()){
            return "welcome";
        }
        // find the hike
        Hike hike = hikeService.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Hike Id"));
        // create a new item
        Item saveItem = new Item(item.getName(), item.getDescription(), item.getItemType(), item.getImage(), hike);
        itemService.save(saveItem);

        Hike updatedHike = hikeService.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Hike Id"));
        model.addAttribute("hike", updatedHike);
        model.addAttribute("achievement", new Achievement());
        model.addAttribute("item", new Item());
        String sessionUsername = (String) httpSession.getAttribute("username");
        if(sessionUsername != null) {
            Profile sessionProfile = profileService.searchProfileByUsername(sessionUsername);
            model.addAttribute("profile", sessionProfile);
        }
        return "hike";
    }

    @RequestMapping(value ="/hike/{hikeid}/item/{itemid}", method = RequestMethod.POST)
    public String deleteitem(@PathVariable("hikeid") long hikeid, @PathVariable("itemid") long id, @Valid Item item, BindingResult result, Model model, HttpSession httpSession){
        if(result.hasErrors()){
            return "welcome";
        }

        itemService.deleteItemById(id);
        Hike hike = hikeService.findById(hikeid).orElseThrow(()->new IllegalArgumentException("Invalid Hike Id"));
        model.addAttribute("hike", hike);
        model.addAttribute("achievement", new Achievement());
        model.addAttribute("item", new Item());
        String sessionUsername = (String) httpSession.getAttribute("username");
        if(sessionUsername != null) {
            Profile sessionProfile = profileService.searchProfileByUsername(sessionUsername);
            model.addAttribute("profile", sessionProfile);
        }
        return "Hike";
    }

    @RequestMapping(value ="/hike/{hikeid}/item/{itemid}/complete", method = RequestMethod.POST)
    public String completeitem(@PathVariable("hikeid") long hikeid, @PathVariable("itemid") long id, @Valid Item item, BindingResult result, Model model, HttpSession httpSession){
        if(result.hasErrors()){
            return "welcome";
        }

        // TODO complete hike for a user

        Hike hike = hikeService.findById(hikeid).orElseThrow(()->new IllegalArgumentException("Invalid Hike Id"));
        model.addAttribute("hike", hike);
        model.addAttribute("achievement", new Achievement());
        model.addAttribute("item", new Item());
        String sessionUsername = (String) httpSession.getAttribute("username");
        if(sessionUsername != null) {
            Profile sessionProfile = profileService.searchProfileByUsername(sessionUsername);
            model.addAttribute("profile", sessionProfile);
        }
        return "Hike";
    }

    @RequestMapping(value="/hike/{id]/item", method = RequestMethod.GET)
    public String additemForm(Hike hike){
        return "welcome";
    }

}
