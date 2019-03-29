package ru.mephi.kaf82.DVM.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Vakobo
 */

@Entity
public class Person extends AbstractPersistable<Long> {

    private String family;

    private String firstName;

    private String secondName;

    private int employeeId;

    private String cardNumber;

    private byte access;

    private byte admin;

    private byte status;

    private String otdel;

    private String doljn;

    @Column(length = 11)
    private String pass;

    @Column(length = 200)
    private String photo;

    @Column(length = 2)
    private int life;

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public byte getAccess() {
        return access;
    }

    public void setAccess(byte access) {
        this.access = access;
    }

    public byte getAdmin() {
        return admin;
    }

    public void setAdmin(byte admin) {
        this.admin = admin;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getOtdel() {
        return otdel;
    }

    public void setOtdel(String otdel) {
        this.otdel = otdel;
    }

    public String getDoljn() {
        return doljn;
    }

    public void setDoljn(String doljn) {
        this.doljn = doljn;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
