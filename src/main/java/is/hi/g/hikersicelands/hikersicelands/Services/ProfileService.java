package is.hi.g.hikersicelands.hikersicelands.Services;

import is.hi.g.hikersicelands.hikersicelands.Entities.Profile;

import java.util.List;


public interface ProfileService {

    Profile loginProfile(String username, String password);
    Profile saveProfile(Profile profile);
    Profile privateMode(boolean priv);
    List<Profile> searchProfileByName(String name);

}
