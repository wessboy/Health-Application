package com.healthapp.HealthApp.security.repository;

import com.healthapp.HealthApp.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Integer> {

    AppRole findAppRoleByRole(String role);
}
