package is.hi.g.hikersicelands.hikersicelands.Services.Implementations;

import is.hi.g.hikersicelands.hikersicelands.Entities.Profile;
import is.hi.g.hikersicelands.hikersicelands.Repositories.ProfileRepository;
import is.hi.g.hikersicelands.hikersicelands.Services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImplementation implements ProfileService {

    ProfileRepository repository;

    @Autowired
    public ProfileServiceImplementation(ProfileRepository profileRepository){
        this.repository = profileRepository;
    }

    // Logga inn profile
    @Override
    public Boolean loginProfile(String username, String password) {
        Profile profile = searchProfileByUsername(username);
        if (profile == null) return false;
        if (profile.getPassword().equals(password)) {
            return true;
        } else return false;
    }

    // Búa til profile
    @Override
    public Profile saveProfile(Profile profile){
            return repository.save(profile);
    }

    // leita að profile út frá nafni
    @Override
    public List<Profile> searchProfileByName(String name){
        return repository.findByName(name);
    }

    // leita að profile út frá unique nafni
    @Override
    public Profile searchProfileByUsername(String name){
        return repository.findByUsername(name);
    }

    @Override
    public void deleteProfileByUsername(String username) {
        repository.deleteByUsername(username);
    }

}
