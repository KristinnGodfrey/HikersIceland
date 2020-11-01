package is.hi.g.hikersicelands.hikersicelands.Services;

import is.hi.g.hikersicelands.hikersicelands.Entities.Achievement;

import java.util.List;

public interface AchievementService {
    Achievement save(Achievement achievement);
    void delete(Achievement achievement);
    List<Achievement> findAchievementsByHikeId(Long hikeId);
    Achievement findAchievementById(Long id);
}
