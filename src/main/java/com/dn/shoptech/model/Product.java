package com.dn.shoptech.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(
            name = "name",
            unique = true,
            columnDefinition = "nvarchar(255)"
    )
    @NotNull
    private String name;

    @Column(columnDefinition = "nvarchar(255)")
    private String title;

    @Column(columnDefinition = "Text")
    private String description;

    private String img;

    private Boolean active = true;

    @NotNull
    private String category_name;


    @Column(name = "create_at")
    private LocalDate create_at = LocalDate.now();
    @Column(name = "create_by")
    private String create_by;
    @Column(name = "modified_at")
    private LocalDate modified_at;
    @Column(name = "modified_by")
    private String modified_by;

    public Product(String name, String title, String description, String img,  String category, String create_by ) {
        this.name = name;
        this.title = title;
        this.description = description;
        this.img = img;
        this.category_name = category;
        this.create_by = create_by;
    }
}
