package com.example.meteo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ATTACHMENT")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_zal;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_postu", nullable = false)
    private Post post;

    @NotNull
    private String sciezka;

    @NotNull
    private String typ;

    public Attachment() {
    }

    public Attachment(@NotNull Post post, @NotNull String sciezka, @NotNull String typ) {
        this.post = post;
        this.sciezka = sciezka;
        this.typ = typ;
    }

    public Long getId_zal() {
        return id_zal;
    }

    public void setId_zal(Long id_zal) {
        this.id_zal = id_zal;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getSciezka() {
        return sciezka;
    }

    public void setSciezka(String sciezka) {
        this.sciezka = sciezka;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }
}
