package is.hi.g.hikersicelands.hikersicelands.Services.Implementations;

import is.hi.g.hikersicelands.hikersicelands.Entities.Hike;
import is.hi.g.hikersicelands.hikersicelands.Entities.Item;
import is.hi.g.hikersicelands.hikersicelands.Repositories.HikeRepository;
import is.hi.g.hikersicelands.hikersicelands.Repositories.ItemRepository;
import is.hi.g.hikersicelands.hikersicelands.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImplementation implements ItemService {
    ItemRepository repository;

    @Autowired
    public ItemServiceImplementation(ItemRepository itemRepository){
        this.repository = itemRepository;
    }

    @Override
    public Item save(Item item) {
        return repository.save(item);
    }

    @Override
    public List<Item> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Item> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Item> findByMountains(String mountain) {
        return repository.findByMountains(mountain);
    }
}
