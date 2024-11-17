package uk.ac.sheffield.foodpassport.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "meals")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "is_public")
    private boolean is_public;

    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;

    @Column(name = "time_created")
    private Instant time_created;

    public Meal() {

    }

    public Meal(String title, String city, String country, double latitude, double longitude, boolean is_public,
            Instant time_created) {
        this.title = title;
        this.city = city;
        this.country = country;
        this.is_public = is_public;
        this.time_created = time_created;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isIs_public() {
        return this.is_public;
    }

    public boolean getIs_public() {
        return this.is_public;
    }

    public void setIs_public(boolean is_public) {
        this.is_public = is_public;
    }

    public User getOwner() {
        return this.owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Instant getTime_created() {
        return this.time_created;
    }

    public void setTime_created(Instant time_created) {
        this.time_created = time_created;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
