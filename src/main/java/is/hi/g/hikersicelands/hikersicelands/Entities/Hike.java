package is.hi.g.hikersicelands.hikersicelands.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Hike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "hike") // one Hike has multiple achievements
    private List<Achievement> achievements = new ArrayList<>();


    private String name;
    private String description;
    private String location;
    private String image;

    public Hike() {

    }

    public Hike(long id, String name, String description, String location, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.image = image;
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
}
