package ru.mephi.kaf82.DVM.model;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 * Created by Vakobo
 */

@Entity
public class Person {

    @Id
    @GeneratedValue
    @Nullable
    private long id;

    private String family;

    private String firstName;

    private String secondName;

    private int employeeId;

    @Column(unique = true)
    private String cardNumber;

    private byte access;

    private byte admin;

    private byte status;

    private String otdel;

    private String doljn;

    @Column(length = 11)
    private String pass;

    @OneToOne
    private File photo;

    @Column(length = 2)
    private int life;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return firstName + " " + secondName;
    }
}
