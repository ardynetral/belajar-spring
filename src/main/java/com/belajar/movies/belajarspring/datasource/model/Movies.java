package com.belajar.movies.belajarspring.datasource.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "`movies`", schema = "`movies`")
@Getter
@Setter
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    @Column(name = "`overview`")
    private String overview;

    @Column(name = "`title`")
    private String title;

    @Column(name = "`poster`")
    private String poster;

    @Column(name = "`play_until`")
    private Date playUntil;

    @Column(name = "`created_at`")
    private Date createdAt;

    @Column(name = "`updated_at`")
    private Date updatedAt;

    @Column(name = "`deleted_at`")
    private Date deletedAt;
}
