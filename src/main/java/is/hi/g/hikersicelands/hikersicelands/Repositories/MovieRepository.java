package is.hi.g.hikersicelands.hikersicelands.Repositories;


import is.hi.g.hikersicelands.hikersicelands.Entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

// jparepository kemur fra dependencies sem við sóttum
// jparepostiry hefur ákveðin föll sem hann skilur og gerir magic.
public interface MovieRepository extends JpaRepository<Movie, Long> {
    // hér búum við til öll föllinn sem gagnagrunnurinn á að styðja

    //skilagildi fallnafn parameter-týpa parameter
    Movie save(Movie movie);
    void delete(Movie movie);
    List<Movie> findAll();
    List<Movie> findByTitle(String title);
    Optional<Movie> findById(long id);


    // þetta virkar líka eitthvað svona: @Query("SELECT * FROM tafla WHERE ) fallið

}
