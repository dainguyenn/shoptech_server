package com.dn.shoptech.dto;

import com.dn.shoptech.model.ENUM.Gender;
import com.dn.shoptech.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    Long id;
    String username;
    String password;
    Boolean active;
    @NotNull
    String first_name;
    @NotNull
    String last_name;
    @NotNull
    @Email
    String email;
    String phone_number;
    String address;
    Gender gender;
    Date dob;
    Date create_at;

    Set<Role> roles;
}
