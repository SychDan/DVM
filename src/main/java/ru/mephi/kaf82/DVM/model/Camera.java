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
public class Camera {

    @Id
    @GeneratedValue
    @Nullable
    private long id;

    private String camCode;

    private String serNum;

    private String invNum;

    @Column(length = 2)
    private int life;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCamCode() {
        return camCode;
    }

    public void setCamCode(String camCode) {
        this.camCode = camCode;
    }

    public String getSerNum() {
        return serNum;
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    public String getInvNum() {
        return invNum;
    }

    public void setInvNum(String invNum) {
        this.invNum = invNum;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
