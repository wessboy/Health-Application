package com.healthapp.HealthApp.security.service;

import com.healthapp.HealthApp.security.entities.AppRole;
import com.healthapp.HealthApp.security.entities.AppUser;

public interface IServiceAuthentication {

    public AppUser createAppUser(AppUser appUser);
    public AppRole createAppRole(AppRole appRole);
    public void addRoleToUser(String username, String role);
    public void removeRoleToUser(String username, String role);
    public AppUser LoadUserByUserName(String username);
}
