package com.dn.shoptech.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "role_name",length = 155)
    String role_name;

    public Role(String role_name) {
        this.role_name = role_name;
    }

    @Override
    public String toString() {
        return  role_name  ;
    }
}
