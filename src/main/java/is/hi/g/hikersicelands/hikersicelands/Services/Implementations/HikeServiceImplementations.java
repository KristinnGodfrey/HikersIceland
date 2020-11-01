package is.hi.g.hikersicelands.hikersicelands.Services.Implementations;

import is.hi.g.hikersicelands.hikersicelands.Entities.Hike;
import is.hi.g.hikersicelands.hikersicelands.Repositories.HikeRepository;
import is.hi.g.hikersicelands.hikersicelands.Services.HikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HikeServiceImplementations implements HikeService {
    HikeRepository repository;

    @Autowired
    public HikeServiceImplementations(HikeRepository hikeRepository){this.repository = hikeRepository;}

    @Override
    public Hike save(Hike hike) {
        return repository.save(hike);
    }

    @Override
    public List<Hike> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Hike> findById(Long id) {
        return repository.findById(id);
    }
}
