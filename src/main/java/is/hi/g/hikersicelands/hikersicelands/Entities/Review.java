package is.hi.g.hikersicelands.hikersicelands.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String userId;
    private String reviewText;
    private int rating;

    @ManyToOne
    @JsonIgnore
    private Hike hike;

    public Review() {

    }

    // Smiður til þess að gera review object
    public Review(String reviewText, int rating, Hike hike) {
        this.reviewText = reviewText;
        this.rating = rating;
        this.hike = hike;
    }

    // Getters og Setters fyrir allar breyturnar í þessu review entity
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getuserId() {
        return userId;
    }

    public void setuserId(String userId) {
        this.userId = userId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Hike getHike() {
        return hike;
    }

    public void setHike(Hike hike) {
        this.hike = hike;
    }
}
