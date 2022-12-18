package com.dn.shoptech.api.adminApi;

import com.dn.shoptech.model.Role;
import com.dn.shoptech.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/roles")
public class RoleApi {
    private final RoleService roleService;

    @GetMapping("")
    public ResponseEntity<Object> findAllRoles(){
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByRoleId(@PathVariable int id){
        Optional<Role> ORole = roleService.findById(id);

        if(ORole.isPresent()){
            Role role = ORole.get();
            return ResponseEntity.ok(role);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found role with id:"+id);
    }

}
