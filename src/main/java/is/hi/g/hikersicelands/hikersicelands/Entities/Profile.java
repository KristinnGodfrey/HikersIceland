package is.hi.g.hikersicelands.hikersicelands.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String password;

    private String username;
    private String name;
    private int age;
    private boolean admin;

    @ManyToMany
    private List<Achievement> completedAchievements = new ArrayList<>();


    // Tómur smiður
    public Profile() {

    }

    // Smiður
    public Profile(String username, String password, String name, int age, boolean admin, List<Achievement> completedAchievements) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.admin = admin;
        this.completedAchievements = completedAchievements;
    }

    // Getters og setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean priv) {
        this.admin = priv;
    }

    public boolean isAdmin() {
        return admin;
    }

    public List<Achievement> getCompletedAchievements() {
        return completedAchievements;
    }

    public void setCompletedAchievements(List<Achievement> completedAchievements) {
        this.completedAchievements = completedAchievements;
    }


}
