package com.example.meteo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "RIGHTS")
public class Right {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_praw;

    @NotNull
    private String nazwa;

    String opis;

    public Right() {
    }

    public Right(@NotNull String nazwa, String opis) {
        this.nazwa = nazwa;
        this.opis = opis;
    }

    public Long getId_praw() {
        return id_praw;
    }

    public void setId_praw(Long id_praw) {
        this.id_praw = id_praw;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
