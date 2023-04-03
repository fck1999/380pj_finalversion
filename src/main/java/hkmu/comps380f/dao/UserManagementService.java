package hkmu.comps380f.dao;

import hkmu.comps380f.exception.*;
import hkmu.comps380f.model.*;

import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UserManagementService {
    @Resource
    private AppUserRepository tuRepo;

    @Transactional
    public List<AppUser> getAppUsers() {
        return tuRepo.findAll();
    }

    public AppUser getAppUser(String name)
            throws UserNotFound {
        AppUser user = tuRepo.findById(name).orElse(null);
        if (user == null) {
            throw new UserNotFound(name);
        }
        return user;
    }
    @Transactional
    public void delete(String username) {
        AppUser appUser = tuRepo.findById(username).orElse(null);
        if (appUser == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found.");
        }
        tuRepo.delete(appUser);
    }

    @Transactional
    public void createAppUser(String username, String password, String email, String description, String[] roles) {
        AppUser user = new AppUser(username, password, email, description, roles);
        tuRepo.save(user);
    }

}