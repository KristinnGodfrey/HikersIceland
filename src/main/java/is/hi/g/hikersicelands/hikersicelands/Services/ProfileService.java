package is.hi.g.hikersicelands.hikersicelands.Services;

import is.hi.g.hikersicelands.hikersicelands.Entities.Profile;

import java.util.List;


public interface ProfileService {

    Boolean loginProfile(String username, String password);

    Profile saveProfile(Profile profile);

    //    Profile updateProfile(Profile profile);
    List<Profile> searchProfileByName(String name);

    Profile searchProfileByUsername(String name);

    void deleteProfileByUsername(String username);
}
