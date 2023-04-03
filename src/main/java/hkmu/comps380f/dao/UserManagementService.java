package hkmu.comps380f.dao;

import hkmu.comps380f.exception.AttachmentNotFound;
import hkmu.comps380f.exception.CommentNotFound;
import hkmu.comps380f.exception.PhotoNotFound;
import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.model.AppUser;

import hkmu.comps380f.model.Attachment;
import hkmu.comps380f.model.Comment;
import hkmu.comps380f.model.Photo;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class UserManagementService {
    @Resource
    private PhotoPosterRepository tuRepo;

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