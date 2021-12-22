package com.belajar.movies.belajarspring.datasource.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "`order_items`", schema = "`movies`")
@Getter
@Setter
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`order_id`")
    @JsonIgnore
    private Orders orderId;

    @ManyToOne
    @JoinColumn(name = "`movie_schedule_id`")
    @JsonIgnore
    private MovieSchedules movieScheduleId;

    @Column(name = "`qty`")
    private Integer qty;

    @Column(name = "`price`")
    private Double price;

    @Column(name = "`sub_total_price`")
    private Double subTotalPrice;

    @Column(name = "`snapshots`")
    private String snapshots;

    @Column(name = "`created_at`")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "`updated_at`")
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name = "`deleted_at`")
    private Date deletedAt;
}
