package com.example.meteo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "POST")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_postu;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_watku", nullable = false)
    private Thread watek;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_uzyt", nullable = false)
    private User uzyt;

    @NotNull
    private Integer nr_postu;

    @NotNull
    private Date data;

    @NotNull
    private String tresc;

    @OneToMany
    @JoinColumn(name = "id_postu")
    private Set<Attachment> zalaczniki;

    public Post() {
    }

    public Post(@NotNull Thread watek, @NotNull User uzyt, @NotNull Integer nr_postu, @NotNull Date data, @NotNull String tresc, Set<Attachment> zalaczniki) {
        this.watek = watek;
        this.uzyt = uzyt;
        this.nr_postu = nr_postu;
        this.data = data;
        this.tresc = tresc;
        this.zalaczniki = zalaczniki;
    }

    public Long getId_postu() {
        return id_postu;
    }

    public void setId_postu(Long id_postu) {
        this.id_postu = id_postu;
    }

    public Thread getWatek() {
        return watek;
    }

    public void setWatek(Thread watek) {
        this.watek = watek;
    }

    public User getUzyt() {
        return uzyt;
    }

    public void setUzyt(User uzyt) {
        this.uzyt = uzyt;
    }

    public Integer getNr_postu() {
        return nr_postu;
    }

    public void setNr_postu(Integer nr_postu) {
        this.nr_postu = nr_postu;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public Set<Attachment> getZalaczniki() {
        return zalaczniki;
    }

    public void setZalaczniki(Set<Attachment> zalaczniki) {
        this.zalaczniki = zalaczniki;
    }
}
