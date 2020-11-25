package is.hi.g.hikersicelands.hikersicelands.Repositories;

import is.hi.g.hikersicelands.hikersicelands.Entities.Achievement;
import is.hi.g.hikersicelands.hikersicelands.Entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.activation.ActivationID;
import java.util.List;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    Achievement save(Achievement achievement); // Aðferð til að búa til Achievement
    @Transactional
    void deleteAchievementById(Long id); // aðferð til að eyða
    List<Achievement> findAchievementsByHikeId(Long hikeId); // aðferð til að fá lista af Achievements út frá HikeID
    Achievement findAchievementById(Long id); // aðferð til að sækja ákveðið Achievement
}
