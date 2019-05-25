package ru.mephi.kaf82.DVM.model;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vakobo
 */

@Entity
public class Otchet {

    @Id
    @GeneratedValue
    @Nullable
    private long id;

    private String name;

    @ManyToOne
    private Person person;

    private Instant date;

    @OneToMany
    private List<Media> media = new ArrayList<>();

    @OneToMany
    private List<Photo> photo = new ArrayList<>();

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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }

    public List<Photo> getPhoto() {
        return photo;
    }

    public void setPhoto(List<Photo> photo) {
        this.photo = photo;
    }
}
