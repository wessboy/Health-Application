package com.healthapp.HealthApp.security.service;

import com.healthapp.HealthApp.security.entities.AppRole;
import com.healthapp.HealthApp.security.entities.AppUser;
import com.healthapp.HealthApp.security.repository.AppRoleRepository;
import com.healthapp.HealthApp.security.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceAuthentication implements  IServiceAuthentication{

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    AppUserRepository _appUserRepository;
    @Autowired
    AppRoleRepository _appRoleRepository;

    @Override
    public AppUser createAppUser(AppUser appUser) {
        AppUser appUserFromDb = _appUserRepository.findAppUserByUserName(appUser.getUserName());
        if(appUserFromDb != null){
            throw new RuntimeException("user already Exists");
        }

        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        _appUserRepository.save(appUser);
        return appUser;



    }

    @Override
    public AppRole createAppRole(AppRole appRole) {
        AppRole roleFromDb = _appRoleRepository.findAppRoleByRole(appRole.getRole());
        if(roleFromDb != null){
            throw  new RuntimeException("Role Already Exists !");
        }
        _appRoleRepository.save(appRole);
        return appRole;
    }

    @Override
    public void addRoleToUser(String username, String role) {

        AppRole approle = _appRoleRepository.findAppRoleByRole(role);
        _appUserRepository.findAppUserByUserName(username).setRole(approle);
    }

    @Override
    public void removeRoleToUser(String username, String role) {
       AppUser user =  _appUserRepository.findAppUserByUserName(username);

       if(user != null)
       _appRoleRepository.findAppRoleByRole(role).getItems().remove(user);
    }

    @Override
    public AppUser LoadUserByUserName(String username) {
        return _appUserRepository.findAppUserByUserName(username);
    }
}
