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



    // Búa til profile
   // @Override
   // public Profile createProfile(String username, String password){
    //    return repository.createProfile(username, password);
    //}

  @Override
  public Profile save(Profile profile){
        return repository.save(profile);
  }

    // leita að profile út frá nafni
    @Override
    public List<Profile> searchProfileByName(String name){
        return repository.searchProfileByName(name);
    }


}

