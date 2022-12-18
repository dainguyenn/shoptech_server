package com.dn.shoptech.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable=false, unique = true)
    private String name;

    @Column(name = "active",nullable = false)
    private Boolean active = true;

    @Column(name="create_at",nullable = false)
    private LocalDate create_at = LocalDate.now();
    private String create_by;
    private LocalDate modified_at;
    private String modified_by;


    public Category(String name) {
        this.name = name;
    }
}
