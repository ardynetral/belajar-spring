package com.belajar.movies.belajarspring.datasource.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @Column(name = "`movie_id`")
    private Long movieId;

    @Column(name = "`studio_id`")
    private Long studioId;

    @Column(name = "`start_time`")
    private String startTime;

    @Column(name = "`end_time`")
    private String endTime;

    @Column(name = "`price`")
    private Double price;

    @Column(name = "`date`")
    private Date date;

    @Column(name = "`created_at`")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "`updated_at`")
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name = "`deleted_at`")
    private Date deletedAt;

}
