package is.hi.g.hikersicelands.hikersicelands.Repositories;

import is.hi.g.hikersicelands.hikersicelands.Entities.Achievement;
import is.hi.g.hikersicelands.hikersicelands.Entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.rmi.activation.ActivationID;
import java.util.List;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    Achievement save(Achievement achievement);
    void delete(Achievement achievement);
    List<Achievement> findAchievementsByHikeId(Long hikeId);
    Achievement findAchievementById(Long id);
}
