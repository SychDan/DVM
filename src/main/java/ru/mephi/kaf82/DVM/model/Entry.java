package ru.mephi.kaf82.DVM.model;


import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vakobo
 */

@Entity
public class Entry extends AbstractPersistable<Long> {

    @ManyToOne
    private Person person;

    @ManyToOne
    private Camera camera;

    @ManyToOne
    private Marshrut marshrut;

    @ManyToOne
    private File media;

    private Instant dateOfEntry;


    private int life;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Marshrut getMarshrut() {
        return marshrut;
    }

    public void setMarshrut(Marshrut marshrut) {
        this.marshrut = marshrut;
    }

    public Instant getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(Instant dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public File getMedia() {
        return media;
    }

    public void setMedia(File media) {
        this.media = media;
    }
}
