package ru.mephi.kaf82.DVM.model;


import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.Instant;

/**
 * Created by Vakobo
 */

@Entity
public class Entry {

    @Id
    @GeneratedValue
    @Nullable
    private int en_id;

    @ManyToOne
    private Person person;

    @ManyToOne
    private Camera camera;

    @ManyToOne
    private Marshrut marshrut;

    private Instant dateOfEntry;

    private int id;

    private int life;

    public int getEn_id() {
        return en_id;
    }

    public void setEn_id(@Nullable int en_id) {
        this.en_id = en_id;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
