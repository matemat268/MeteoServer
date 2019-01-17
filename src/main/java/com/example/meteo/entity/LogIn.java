package com.example.meteo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "LOGIN")
public class LogIn {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_login;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_uzyt", nullable = false)
    private User uzyt;

    @NotNull
    private Date czas;

    private Integer prob;

    public LogIn() {
    }

    public LogIn(@NotNull User uzyt, @NotNull Date czas, Integer prob) {
        this.uzyt = uzyt;
        this.czas = czas;
        this.prob = prob;
    }

    public Long getId_login() {
        return id_login;
    }

    public void setId_login(Long id_login) {
        this.id_login = id_login;
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

    public Integer getProb() {
        return prob;
    }

    public void setProb(Integer prob) {
        this.prob = prob;
    }
}
