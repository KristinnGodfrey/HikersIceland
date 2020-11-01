package is.hi.g.hikersicelands.hikersicelands.Services.Implementations;

import is.hi.g.hikersicelands.hikersicelands.Entities.Achievement;
import is.hi.g.hikersicelands.hikersicelands.Repositories.AchievementRepository;
import is.hi.g.hikersicelands.hikersicelands.Services.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AchievementServiceImplementation implements AchievementService {

    AchievementRepository repository;

    @Autowired
    public AchievementServiceImplementation(AchievementRepository achievementRepository) {
        this.repository = achievementRepository;
    }

    // búa til Achievement
    @Override
    public Achievement save(Achievement achievement) {
        return repository.save(achievement);
    }

    // eyða Achievement
    @Override
    public void delete(Achievement achievement) {
        repository.delete(achievement);
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
