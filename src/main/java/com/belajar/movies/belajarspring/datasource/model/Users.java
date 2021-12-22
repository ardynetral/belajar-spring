package com.belajar.movies.belajarspring.datasource.model;

import com.belajar.movies.belajarspring.datasource.AppUserRole;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "`users`", schema = "`movies`")
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;

    @Column(name = "`name`")
    private String username;

    @Column(name = "`email`")
    @NotEmpty(message="Email cannot be null")
    private String email;

    @Size(min = 8, message = "Minimum password length: 10 characters")
    @Column(name = "`password`")
    private String password;

    @Column(name = "`avatar`")
    private String avatar;

    @Column(name = "`is_admin`")
    private Boolean isAdmin;

    @Column(name = "`created_at`")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "`updated_at`")
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name = "`deleted_at`")
    private Date deletedAt;

    @Transient
    private List<AppUserRole> appUserRoles;

    @Transient
    private String token;

}
