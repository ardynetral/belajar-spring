package com.belajar.movies.belajarspring.datasource.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "`movie_schedules`", schema = "`movies`")
@Getter
@Setter
public class MovieSchedules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "`movie_id`")
    @JsonIgnore
    private Movies movieId;

    @ManyToOne
    @JoinColumn(name = "`studio_id`")
    @JsonIgnore
    private Studios studioId;

    @Column(name = "`start_time`")
    private String startTime;

    @Column(name = "`end_time`")
    private String endTime;

    @Column(name = "`price`")
    private Double price;

    @Column(name = "`date`")
    private Date date;

    @Column(name = "`created_at`")
    private Date createdAt;

    @Column(name = "`updated_at`")
    private Date updatedAt;

    @Column(name = "`deleted_at`")
    private Date deletedAt;

}
