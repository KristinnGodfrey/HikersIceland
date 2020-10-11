package is.hi.g.hikersicelands.hikersicelands;

import is.hi.g.hikersicelands.hikersicelands.Entities.Movie;
import is.hi.g.hikersicelands.hikersicelands.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    private MovieService movieService;
    @Autowired
    public HomeController(MovieService movieService) {
        this.movieService = movieService;
    }
    @RequestMapping("/")
    public String Home(Model model) {
        model.addAttribute("Movies",movieService.findAll()); // senda gögn í html skjalið okkar
        return "Welcome";
    }

//    @RequestMapping("/addmovie", method = RequestMethod.POST)
//    public String addMovie(Movie movie, BindingResult result, Model model) {
//        if(result.hasErrors()){
//            return "add-movie";
//        }
//        movieService.save(movie); // vista biomynd
//        model.addAttribute("movies", movieService.findAll()); // sækja allar biomyndirnar aftur
//        return "Welcome";
//    }
    @RequestMapping(value = "/addmovie", method = RequestMethod.GET)
    public String addMovieForm(Model model) {
        return "add-movie";
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteMovie(@PathVariable("id") long id, Model model) {
        Movie movie = movieService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid Movie ID"));
        movieService.delete(movie);
        model.addAttribute("movies", movieService.findAll());
        return "Welcome";
    }

    @RequestMapping("/login")
    public static String Login() {
        return "Login";
    }

    @RequestMapping("/signup")
    public static String Signup() {
        return "Signup";
    }
}
