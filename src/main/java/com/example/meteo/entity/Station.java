package com.example.meteo.entity;

import javax.persistence.*;

@Entity
@Table(name = "STATION")
public class Station {

    @Id
    private Long id_stacji;

    private String nazwa;

    private String kod;

    public Station() {
    }

    public Station(String nazwa, String kod) {
        this.nazwa = nazwa;
        this.kod = kod;
    }

    public Long getId_stacji() {
        return id_stacji;
    }

    public void setId_stacji(Long id_stacji) {
        this.id_stacji = id_stacji;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }
}
