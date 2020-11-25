package is.hi.g.hikersicelands.hikersicelands.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Hike {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy = "hike") // one Hike has multiple achievements
    private List<Achievement> achievements = new ArrayList<>();

    @OneToMany(mappedBy = "hike") // one Hike has multiple reviews
    private List<Review> reviews = new ArrayList<>();

    private String name;
    private String description;
    private String location;
    private String image;

    public Hike() {

    }

    public Hike(long id, String name, String description, String location, String image, List<Achievement> achievements) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.image = image;
        this.achievements = achievements;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
