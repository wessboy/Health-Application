package com.healthapp.HealthApp.security.repository;

import com.healthapp.HealthApp.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Integer> {

    AppUser findAppUserByUserName(String userName);
}
