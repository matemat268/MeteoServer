package com.example.meteo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_uzyt;

    @NotNull
    private String login;

    @NotNull
    private String haslo;

    @NotNull
    private String email;

    private String imie;

    private String nazwisko;

    private Character plec;

    private Date data_ur;

    public User() {
    }

    public User(@NotNull String login, @NotNull String haslo, @NotNull String email, String imie, String nazwisko, Character plec, Date data_ur) {
        this.login = login;
        this.haslo = haslo;
        this.email = email;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.plec = plec;
        this.data_ur = data_ur;
    }

    public Long getId_uzyt() {
        return id_uzyt;
    }

    public void setId_uzyt(Long id_uzyt) {
        this.id_uzyt = id_uzyt;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Character getPlec() {
        return plec;
    }

    public void setPlec(Character plec) {
        this.plec = plec;
    }

    public Date getData_ur() {
        return data_ur;
    }

    public void setData_ur(Date data_ur) {
        this.data_ur = data_ur;
    }

    public void setData_ur(String s) {
        if(s != null)
            this.data_ur = new Date(Integer.parseInt(s.substring(0,4)) - 1900, Integer.parseInt(s.substring(5,7))-1, Integer.parseInt(s.substring(8,10))+2);
    }
}
