package com.example.meteo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "MEASUREMENTS")
public class Measurements {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_gr_pom;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_uzyt", nullable = false)
    private User uzyt;

    @NotNull
    private Date data_dod;

    @OneToMany
    @JoinColumn(name = "id_gr_pom")
    private Set<DailyMeasurement> pomiary;

    @JoinColumn(name = "typ")
    private String typ;

    public Measurements() {
    }

    public Measurements(@NotNull User uzyt, @NotNull Date data_dod, Set<DailyMeasurement> pomiary, String typ) {
        this.uzyt = uzyt;
        this.data_dod = data_dod;
        this.pomiary = pomiary;
        this.typ = typ;
    }

    public Long getId_gr_pom() {
        return id_gr_pom;
    }

    public void setId_gr_pom(Long id_gr_pom) {
        this.id_gr_pom = id_gr_pom;
    }

    public User getUzyt() {
        return uzyt;
    }

    public void setUzyt(User uzyt) {
        this.uzyt = uzyt;
    }

    public Date getData_dod() {
        return data_dod;
    }

    public void setData_dod(Date data_dod) {
        this.data_dod = data_dod;
    }

    public Set<DailyMeasurement> getPomiary() {
        return pomiary;
    }

    public void setPomiary(Set<DailyMeasurement> pomiary) {
        this.pomiary = pomiary;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }
}
