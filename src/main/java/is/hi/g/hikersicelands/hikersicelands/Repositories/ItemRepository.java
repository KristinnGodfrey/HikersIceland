package is.hi.g.hikersicelands.hikersicelands.Repositories;

import is.hi.g.hikersicelands.hikersicelands.Entities.Achievement;
import is.hi.g.hikersicelands.hikersicelands.Entities.Hike;
import is.hi.g.hikersicelands.hikersicelands.Entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item save(Item item);
    List<Item> findAll();
    Optional<Item> findById(Long id);
    List<Item> findByHikeId(Long hikeId);
    Optional<Item> findByMountains(String mountain);
}
