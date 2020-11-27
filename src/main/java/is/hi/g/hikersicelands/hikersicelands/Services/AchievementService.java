package is.hi.g.hikersicelands.hikersicelands.Services;

import is.hi.g.hikersicelands.hikersicelands.Entities.Achievement;

import java.util.List;

public interface AchievementService {
    Achievement save(Achievement achievement); // Aðferð til að búa til Achievement
    void deleteAchievementById(Long id); // aðferð til að eyða
    List<Achievement> findAchievementsByHikeId(Long hikeId); // aðferð til að fá lista af Achievements út frá HikeID
    Achievement findAchievementById(Long id); // aðferð til að sækja ákveðið Achievement
}
