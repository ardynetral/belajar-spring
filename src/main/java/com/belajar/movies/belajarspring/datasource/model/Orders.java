package com.belajar.movies.belajarspring.datasource.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "`oders`", schema = "`movies`")
@Getter
@Setter
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "`user_id`" )
    @JsonIgnore
    private Users userId;

    @Column(name = "`payment_method`")
    private String paymentMethod;

    @Column(name = "`total_item_price`")
    private Double totalItemPrice;

    @Column(name = "`created_at`")
    private Date createdAt;

    @Column(name = "`updated_at`")
    private Date updatedAt;

    @Column(name = "`deleted_at`")
    private Date deletedAt;
}
