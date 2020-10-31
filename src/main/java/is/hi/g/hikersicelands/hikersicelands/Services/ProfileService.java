package is.hi.g.hikersicelands.hikersicelands.Services;

import is.hi.g.hikersicelands.hikersicelands.Entities.Profile;

import java.util.List;


public interface ProfileService {

    Profile loginProfile(String username, String password);
    Profile createProfile(String username, String password);
    Profile privateMode(boolean priv);

    List<Profile> searchProfile(String name);

}
