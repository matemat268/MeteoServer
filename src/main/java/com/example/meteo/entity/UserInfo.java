package com.example.meteo.entity;

import java.util.Date;

public class UserInfo {

    private Long id_uzyt;
    private String login;
    private String email;
    private String imie;
    private String nazwisko;
    private Character plec;
    private Date data_ur;
    private Date data_rej;
    private Date data_ost;
    private String admin;

    public UserInfo(Long id_uzyt, String login, String email, String imie, String nazwisko, Character plec, Date data_ur, Date data_rej, Date data_ost, String admin) {
        this.id_uzyt = id_uzyt;
        this.login = login;
        this.email = email;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.plec = plec;
        this.data_ur = data_ur;
        this.data_rej = data_rej;
        this.data_ost = data_ost;
        this.admin = admin;
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

    public Date getData_rej() {
        return data_rej;
    }

    public void setData_rej(Date data_rej) {
        this.data_rej = data_rej;
    }

    public Date getData_ost() {
        return data_ost;
    }

    public void setData_ost(Date data_ost) {
        this.data_ost = data_ost;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
}
