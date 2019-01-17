package com.example.meteo.entity;

import javax.persistence.*;

@Entity
@Table(name = "COLLECTED_DATA")
public class CollectedData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_pob;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_login", nullable = false)
    private LogIn logIn;

    private String typ;

    private String dane;

    private String zakres;

    private String powod;

    public CollectedData() {
    }

    public Long getId_pob() {
        return id_pob;
    }

    public void setId_pob(Long id_pob) {
        this.id_pob = id_pob;
    }

    public LogIn getLogIn() {
        return logIn;
    }

    public void setLogIn(LogIn logIn) {
        this.logIn = logIn;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getDane() {
        return dane;
    }

    public void setDane(String dane) {
        this.dane = dane;
    }

    public String getZakres() {
        return zakres;
    }

    public void setZakres(String zakres) {
        this.zakres = zakres;
    }

    public String getPowod() {
        return powod;
    }

    public void setPowod(String powod) {
        this.powod = powod;
    }
}
