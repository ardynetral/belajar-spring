package com.belajar.movies.belajarspring.datasource.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "`studios`", schema = "`movies`")
@Getter
@Setter
public class Studios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    @Column(name = "`studio_number`")
    private Integer studioNumber;

    @Column(name = "`seat_capacity`")
    private Integer seatCapacity;

    @Column(name = "`created_at`")
    private Date createdAt;

    @Column(name = "`updated_at`")
    private Date updatedAt;

    @Column(name = "`deleted_at`")
    private Date deletedAt;
}
