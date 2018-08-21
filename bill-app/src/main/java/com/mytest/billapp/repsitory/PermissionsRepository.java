package com.mytest.billapp.repsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytest.billapp.model.Permissions;

@Repository
public interface PermissionsRepository extends JpaRepository<Permissions, Long> {

}
