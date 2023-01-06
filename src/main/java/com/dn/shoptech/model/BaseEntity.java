package com.dn.shoptech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {
    @Column(name = "create_at")
    protected LocalDate create_at = LocalDate.now();

    @ManyToOne
    @JoinColumn(name="create_by",referencedColumnName = "username")
    protected User create_by;
    @Column(name = "modified_at")
    protected LocalDate modified_at;
    @ManyToOne
    @JoinColumn(name="modified_by",referencedColumnName = "username")
    protected User modified_by;
}
