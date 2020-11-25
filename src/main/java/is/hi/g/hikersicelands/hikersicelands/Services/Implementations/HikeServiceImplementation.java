package is.hi.g.hikersicelands.hikersicelands.Services.Implementations;

import is.hi.g.hikersicelands.hikersicelands.Entities.Hike;
import is.hi.g.hikersicelands.hikersicelands.Repositories.HikeRepository;
import is.hi.g.hikersicelands.hikersicelands.Services.HikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HikeServiceImplementation implements HikeService {
    HikeRepository repository;

    @Autowired
    public HikeServiceImplementation(HikeRepository hikeRepository){this.repository = hikeRepository;}

    // vista hike
    @Override
    public Hike save(Hike hike) {
        return repository.save(hike);
    }

    // ná í öll hikes
    @Override
    public List<Hike> findAll() {
        return repository.findAll();
    }

    // ná í öll hikes þar sem id er tilgreint
    @Override
    public Optional<Hike> findById(Long id) {
        return repository.findById(id);
    }
}
