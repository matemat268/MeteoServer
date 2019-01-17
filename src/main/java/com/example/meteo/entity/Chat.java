package com.example.meteo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "CHAT")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_wiad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_uzyt", nullable = false)
    private User uzyt;

    @NotNull
    private Date data;

    @NotNull
    private String tresc;

    public Chat() {
    }

    public Chat(User uzyt, @NotNull Date data, @NotNull String tresc) {
        this.uzyt = uzyt;
        this.data = data;
        this.tresc = tresc;
    }

    public Long getId_wiad() {
        return id_wiad;
    }

    public void setId_wiad(Long id_wiad) {
        this.id_wiad = id_wiad;
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

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }
}
