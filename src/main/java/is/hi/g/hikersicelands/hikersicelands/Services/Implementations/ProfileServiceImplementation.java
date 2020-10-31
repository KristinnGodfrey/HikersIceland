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

    @Override
    public Profile loginProfile(String username, String password){
        return repository.loginProfile(username, password);
    }

    @Override
    public Profile createProfile(String username, String password){
        return repository.createProfile(username, password);
    }

    @Override
    public Profile privateMode(boolean priv){
        return repository.privateMode(priv);
    }

    @Override
    public List<Profile> searchProfile(String name){
        return repository.searchProfile(name);
    }
}
