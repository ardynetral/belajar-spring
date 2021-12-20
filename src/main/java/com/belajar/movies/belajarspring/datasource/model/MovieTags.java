package com.belajar.movies.belajarspring.datasource.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "`movie_tags`", schema = "`movies`")
@Getter
@Setter
public class MovieTags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "`movie_id`")
    @JsonIgnore
    private Movies movieId;

    @ManyToOne
    @JoinColumn(name = "`tag_id`")
    @JsonIgnore
    private Tags tagId;

    @Column(name = "`created_at`")
    private Date createdAt;

    @Column(name = "`updated_at`")
    private Date updatedAt;

    @Column(name = "`deleted_at`")
    private Date deletedAt;

}
