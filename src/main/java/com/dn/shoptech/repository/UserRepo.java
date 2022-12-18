package com.dn.shoptech.repository;

import com.dn.shoptech.dto.UserDto;
import com.dn.shoptech.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    @Modifying
    @Query("UPDATE User u SET " +
            "u.first_name = :#{#user.first_name}, u.last_name = :#{#user.last_name}," +
            "u.email = :#{#user.email}, u.address = :#{#user.address}," +
            "u.gender = :#{#user.gender}, u.dob = :#{#user.dob}," +
            "u.phone_number = :#{#user.phone_number} " +
            "WHERE u.id= :#{#user.id}")
    void updateInfoUser(@Param("user") UserDto userDto);


}
