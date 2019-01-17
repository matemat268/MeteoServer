package com.example.meteo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "DAILY_MEASUREMENT")
public class DailyMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_pom;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gr_pom")
    private Measurements grupaPomiarow;

    @NotNull
    private Date dzien;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_stacji")
    private Station stacja;

    private Double max_t;

    private Double min_t;

    private Double sr_t;

    private Double min_t_gr;

    private Double sum_opad;

    private String rodzaj_opad;

    private Double wys_po_sn;

    private Double sr_wilg;

    private Double sr_pr_wiatr;

    private Double sr_zachm;

    private Double uslonecz;

    public DailyMeasurement() {
    }

    public DailyMeasurement(@NotNull Measurements grupaPomiarow, @NotNull Date dzien, @NotNull Station stacja, Double max_t, Double min_t, Double sr_t, Double min_t_gr, Double sum_opad, String rodzaj_opad, Double wys_po_sn, Double sr_wilg, Double sr_pr_wiatr, Double sr_zachm, Double uslonecz) {
        this.grupaPomiarow = grupaPomiarow;
        this.dzien = dzien;
        this.stacja = stacja;
        this.max_t = max_t;
        this.min_t = min_t;
        this.sr_t = sr_t;
        this.min_t_gr = min_t_gr;
        this.sum_opad = sum_opad;
        this.rodzaj_opad = rodzaj_opad;
        this.wys_po_sn = wys_po_sn;
        this.sr_wilg = sr_wilg;
        this.sr_pr_wiatr = sr_pr_wiatr;
        this.sr_zachm = sr_zachm;
        this.uslonecz = uslonecz;
    }

    public Long getId_pom() {
        return id_pom;
    }

    public void setId_pom(Long id_pom) {
        this.id_pom = id_pom;
    }

    public Measurements getGrupaPomiarow() {
        return grupaPomiarow;
    }

    public void setGrupaPomiarow(Measurements grupaPomiarow) {
        this.grupaPomiarow = grupaPomiarow;
    }

    public Date getDzien() {
        return dzien;
    }

    public void setDzien(Date dzien) {
        this.dzien = dzien;
    }

    public void setDzen(String s) {
        if(s != null)
            this.dzien = new Date(Integer.parseInt(s.substring(0,4)) - 1900, Integer.parseInt(s.substring(5,7))-1, Integer.parseInt(s.substring(8,10))+2);
    }

    public Station getStacja() {
        return stacja;
    }

    public void setStacja(Station stacja) {
        this.stacja = stacja;
    }

    public Double getMax_t() {
        return max_t;
    }

    public void setMax_t(Double max_t) {
        this.max_t = max_t;
    }

    public Double getMin_t() {
        return min_t;
    }

    public void setMin_t(Double min_t) {
        this.min_t = min_t;
    }

    public Double getSr_t() {
        return sr_t;
    }

    public void setSr_t(Double sr_t) {
        this.sr_t = sr_t;
    }

    public Double getMin_t_gr() {
        return min_t_gr;
    }

    public void setMin_t_gr(Double min_t_gr) {
        this.min_t_gr = min_t_gr;
    }

    public Double getSum_opad() {
        return sum_opad;
    }

    public void setSum_opad(Double sum_opad) {
        this.sum_opad = sum_opad;
    }

    public String getRodzaj_opad() {
        return rodzaj_opad;
    }

    public void setRodzaj_opad(String rodzaj_opad) {
        this.rodzaj_opad = rodzaj_opad;
    }

    public Double getWys_po_sn() {
        return wys_po_sn;
    }

    public void setWys_po_sn(Double wys_po_sn) {
        this.wys_po_sn = wys_po_sn;
    }

    public Double getSr_wilg() {
        return sr_wilg;
    }

    public void setSr_wilg(Double sr_wilg) {
        this.sr_wilg = sr_wilg;
    }

    public Double getSr_pr_wiatr() {
        return sr_pr_wiatr;
    }

    public void setSr_pr_wiatr(Double sr_pr_wiatr) {
        this.sr_pr_wiatr = sr_pr_wiatr;
    }

    public Double getSr_zachm() {
        return sr_zachm;
    }

    public void setSr_zachm(Double sr_zachm) {
        this.sr_zachm = sr_zachm;
    }

    public Double getUslonecz() {
        return uslonecz;
    }

    public void setUslonecz(Double uslonecz) {
        this.uslonecz = uslonecz;
    }
}
