package com.dn.shoptech.model;

import com.dn.shoptech.model.ENUM.Gender;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(name = "username", length = 55,nullable = false, unique = true) @NotNull
    String username;
    @Column(name = "password", length = 255,nullable = false) @NotNull
    String password;
    @Column(name = "active",nullable = false)
    Boolean active = true;
    @Column(name = "first_name",nullable = false,length = 55) @NotNull
    String first_name;
    @Column(name = "last_name",nullable = false,length = 55) @NotNull
    String last_name;
    @Column(name = "email",nullable = false,length = 255) @NotNull @Email
    String email;
    @Column(name = "phone_number",length = 12)
    String phone_number;
    @Column(name = "address",length = 255)
    String address;

    @Column(name = "gender",length=23)
    @Enumerated(EnumType.STRING)
    Gender gender;
    @Column(name = "dob")
    Date dob;
    @Column(name = "create_at")
    Instant create_at;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles = new HashSet<>();



    public User(String username, String password , String first_name, String last_name, String email, String phone_number, String address, Gender gender, Date dob ) {
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
        this.gender = gender;
        this.dob = dob;
        this.create_at = Instant.now();
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role: roles){
            authorities.add(new SimpleGrantedAuthority(role.getRole_name()));
        }

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
