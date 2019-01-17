package com.example.meteo.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "LOG")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_log;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_uzyt", nullable = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private User uzyt;

    private Date czas;

    @NotNull
    private String typ;

    private String opis;

    public Log() {
    }

    public Log(User uzyt, Date czas, @NotNull String typ, String opis) {
        this.uzyt = uzyt;
        this.czas = czas;
        this.typ = typ;
        this.opis = opis;
    }

    public Long getId_log() {
        return id_log;
    }

    public void setId_log(Long id_log) {
        this.id_log = id_log;
    }

    public User getUzyt() {
        return uzyt;
    }

    public void setUzyt(User uzyt) {
        this.uzyt = uzyt;
    }

    public Date getCzas() {
        return czas;
    }

    public void setCzas(Date czas) {
        this.czas = czas;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
