package is.hi.g.hikersicelands.hikersicelands.Services;

import is.hi.g.hikersicelands.hikersicelands.Entities.Item;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Item save(Item item);
    List<Item> findAll();
    Optional<Item> findById(Long id);
    @Transactional
    void deleteItemById(Long id);
}
