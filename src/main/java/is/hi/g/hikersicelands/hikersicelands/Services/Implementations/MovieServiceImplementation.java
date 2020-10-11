package is.hi.g.hikersicelands.hikersicelands.Services.Implementations;

import is.hi.g.hikersicelands.hikersicelands.Entities.Movie;
import is.hi.g.hikersicelands.hikersicelands.Repositories.MovieRepository;
import is.hi.g.hikersicelands.hikersicelands.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// hér er hægt að bæta við okkar eigin virkni ofan á það sem við gerðum auto í repositories.
@Service
public class MovieServiceImplementation implements MovieService {

    // aðgengi að MovieRepository klasanum okkar
    MovieRepository repository;

    // stilla repositoryið sem við ætlum að nota sem repositoryið sem við ætlum að taka inn
    // tilvikið af þessu er buið til þegar við kveikjum á servernum
    @Autowired
    public MovieServiceImplementation(MovieRepository movieRepository){
        this.repository = movieRepository;
    }

    @Override
    public Movie save(Movie movie) {
        return repository.save(movie);
    }

    @Override
    public void delete(Movie movie) {
        repository.delete(movie);
    }

    @Override
    public List<Movie> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Movie> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    @Override
    public Optional<Movie> findById(long id) {
        return repository.findById(id);
    }
}
