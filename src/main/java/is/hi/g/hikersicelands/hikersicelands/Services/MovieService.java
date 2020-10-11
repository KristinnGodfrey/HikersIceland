package is.hi.g.hikersicelands.hikersicelands.Services;

import is.hi.g.hikersicelands.hikersicelands.Entities.Movie;

import java.util.List;
import java.util.Optional;

// tilgangurinn á að gera þetta tvisvar: kannski er ekki hægt að gera nákvæmlega það sem viljum gera,
// þá er service millag milli controllers og gagnagrunnsins,
public interface MovieService {
    Movie save(Movie movie);

    void delete(Movie movie);
    List<Movie> findAll();
    List<Movie> findByTitle(String title);
    Optional<Movie> findById(long id);
}
