package com.example.meteo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "GIVEN_RIGHTS")
public class GivenRights {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_nad_praw;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_uzyt_nad", nullable = false)
    private User uzytNadajacy;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_uzyt_doc", nullable = false)
    private User uzytDocelowy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_gr_pom")
    private Measurements grupaPomiarow;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_praw", nullable = false)
    private Right prawo;

    private Date data;

    public GivenRights() {
    }

    public GivenRights(@NotNull User uzytNadajacy, @NotNull User uzytDocelowy, Measurements grupaPomiarow, @NotNull Right prawo, Date data) {
        this.uzytNadajacy = uzytNadajacy;
        this.uzytDocelowy = uzytDocelowy;
        this.grupaPomiarow = grupaPomiarow;
        this.prawo = prawo;
        this.data = data;
    }

    public Long getId_nad_praw() {
        return id_nad_praw;
    }

    public void setId_nad_praw(Long id_nad_praw) {
        this.id_nad_praw = id_nad_praw;
    }

    public User getUzytNadajacy() {
        return uzytNadajacy;
    }

    public void setUzytNadajacy(User uzytNadajacy) {
        this.uzytNadajacy = uzytNadajacy;
    }

    public User getUzytDocelowy() {
        return uzytDocelowy;
    }

    public void setUzytDocelowy(User uzytDocelowy) {
        this.uzytDocelowy = uzytDocelowy;
    }

    public Measurements getGrupaPomiarow() {
        return grupaPomiarow;
    }

    public void setGrupaPomiarow(Measurements grupaPomiarow) {
        this.grupaPomiarow = grupaPomiarow;
    }

    public Right getPrawo() {
        return prawo;
    }

    public void setPrawo(Right prawo) {
        this.prawo = prawo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
