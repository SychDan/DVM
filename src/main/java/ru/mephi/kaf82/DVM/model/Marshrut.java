package ru.mephi.kaf82.DVM.model;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Vakobo
 */

@Entity
public class Marshrut {

    @Id
    @GeneratedValue
    @Nullable
    private long id;

    @Column(length = 100)
    private String name;

    @Column(length = 500)
    private String description;

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
}
