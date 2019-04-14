package ru.mephi.kaf82.DVM.model;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Terminal {

    @Id
    @GeneratedValue
    @Nullable
    private long id;

    @Column(length = 200)
    private String nameGroup;

    @Column(length = 11)
    private int parentId;

    @Column(length = 10)
    private String termNumber;

    @Column(length = 200)
    private String address;

    @Column(length = 30)
    private String ip;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getTermNumber() {
        return termNumber;
    }

    public void setTermNumber(String termNumber) {
        this.termNumber = termNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
