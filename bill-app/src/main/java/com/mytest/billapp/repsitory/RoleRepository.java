package com.mytest.billapp.repsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytest.billapp.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByRole(String role);

}