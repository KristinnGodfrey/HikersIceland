package is.hi.g.hikersicelands.hikersicelands.Entities;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // Achievement Id
    private String name; // titill
    private String description; // skýring
    private int difficulty; // erfiðleiki

    @ManyToOne
    private Hike hike;

    // Tómur smiður
    public Achievement() {

    }

    // Smiður
    public Achievement(long hikeId, String name, String description, int difficulty) {
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
    }

    // Getters og setters
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

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
