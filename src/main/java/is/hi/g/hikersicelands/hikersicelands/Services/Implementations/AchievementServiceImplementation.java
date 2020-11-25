package is.hi.g.hikersicelands.hikersicelands.Services.Implementations;

import is.hi.g.hikersicelands.hikersicelands.Entities.Achievement;
import is.hi.g.hikersicelands.hikersicelands.Repositories.AchievementRepository;
import is.hi.g.hikersicelands.hikersicelands.Services.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchievementServiceImplementation implements AchievementService {

    AchievementRepository repository;

    @Autowired
    public AchievementServiceImplementation(AchievementRepository achievementRepository) {
        this.repository = achievementRepository;
    }

    // bæta við Achievement
    @Override
    public Achievement save(Achievement achievement) {
        return repository.save(achievement);
    }

    // eyða Achievement
    @Override
    public void deleteAchievementById(Long id) {
        repository.deleteAchievementById(id);
    }

    // sækja lista af Achievements sem tilheyra hikeId
    @Override
    public List<Achievement> findAchievementsByHikeId(Long hikeId) {
        return repository.findAchievementsByHikeId(hikeId);
    }

    // sækja Achievement út frá id
    @Override
    public Achievement findAchievementById(Long id) {
        return repository.findAchievementById(id);
    }
}
