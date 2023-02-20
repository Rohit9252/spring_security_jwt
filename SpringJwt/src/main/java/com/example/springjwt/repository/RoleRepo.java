package com.example.springjwt.repository;

import com.example.springjwt.model.MyRoles;
import com.example.springjwt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

    public Role findByRole(MyRoles roles);


}
