package com.dn.shoptech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
    @Column(name = "create_at")
    protected LocalDate create_at = LocalDate.now();
    @Column(name = "create_by")
    protected String create_by;
    @Column(name = "modified_at")
    protected LocalDate modified_at;
    @Column(name = "modified_by")
    protected String modified_by;
}
