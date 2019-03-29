package ru.mephi.kaf82.DVM.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigInteger;

/**
 * Created by Vakobo
 */

@Entity
public class Media extends AbstractPersistable<Long> {

    private int idJournal;

    @Column(length = 5)
    private int videoFound;

    @Column(length = 5)
    private int videoTransfer;

    @Column(length = 5)
    private int fotoFound;

    @Column(length = 5)
    private int fotoTransfer;

    @Column(length = 5)
    private int logFound;

    @Column(length = 5)
    private int logTransfer;

    @Column(length = 19)
    private BigInteger sizeFound;

    @Column(length = 19)
    private BigInteger sizeTransfer;

    public int getIdJournal() {
        return idJournal;
    }

    public void setIdJournal(int idJournal) {
        this.idJournal = idJournal;
    }

    public int getVideoFound() {
        return videoFound;
    }

    public void setVideoFound(int videoFound) {
        this.videoFound = videoFound;
    }

    public int getVideoTransfer() {
        return videoTransfer;
    }

    public void setVideoTransfer(int videoTransfer) {
        this.videoTransfer = videoTransfer;
    }

    public int getFotoFound() {
        return fotoFound;
    }

    public void setFotoFound(int fotoFound) {
        this.fotoFound = fotoFound;
    }

    public int getFotoTransfer() {
        return fotoTransfer;
    }

    public void setFotoTransfer(int fotoTransfer) {
        this.fotoTransfer = fotoTransfer;
    }

    public int getLogFound() {
        return logFound;
    }

    public void setLogFound(int logFound) {
        this.logFound = logFound;
    }

    public int getLogTransfer() {
        return logTransfer;
    }

    public void setLogTransfer(int logTransfer) {
        this.logTransfer = logTransfer;
    }

    public BigInteger getSizeFound() {
        return sizeFound;
    }

    public void setSizeFound(BigInteger sizeFound) {
        this.sizeFound = sizeFound;
    }

    public BigInteger getSizeTransfer() {
        return sizeTransfer;
    }

    public void setSizeTransfer(BigInteger sizeTransfer) {
        this.sizeTransfer = sizeTransfer;
    }
}
