package com.example.meteo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "THREAD")
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_watku;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_uzyt", nullable = false)
    private User uzyt;

    @NotNull
    private Date data;

    @NotNull
    private String tytul;

    @NotNull
    private String typ;

    @OneToMany
    @JoinColumn(name = "id_watku")
    private Set<Post> posty;

    public Thread() {
    }

    public Thread(@NotNull User uzyt, @NotNull Date data, @NotNull String tytul, @NotNull String typ, Set<Post> posty) {
        this.uzyt = uzyt;
        this.data = data;
        this.tytul = tytul;
        this.typ = typ;
        this.posty = posty;
    }

    public Long getId_watku() {
        return id_watku;
    }

    public void setId_watku(Long id_watku) {
        this.id_watku = id_watku;
    }

    public User getUzyt() {
        return uzyt;
    }

    public void setUzyt(User uzyt) {
        this.uzyt = uzyt;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public Set<Post> getPosty() {
        return posty;
    }

    public void setPosty(Set<Post> posty) {
        this.posty = posty;
    }
}
