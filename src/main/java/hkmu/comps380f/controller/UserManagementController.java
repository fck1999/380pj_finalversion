package hkmu.comps380f.controller;

import hkmu.comps380f.dao.CommentService;
import hkmu.comps380f.dao.UserManagementService;
import hkmu.comps380f.exception.AttachmentNotFound;
import hkmu.comps380f.exception.PhotoNotFound;
import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.model.AppUser;
import hkmu.comps380f.model.Photo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/user")
public class UserManagementController {
    @Resource
    UserManagementService umService;

    @GetMapping({"", "/", "/list"})
    public String list(ModelMap model) {
        model.addAttribute("appUser", umService.getAppUsers());
        return "listUser";
    }

    public static class Form {
        private String username;
        private String password;
        private String email;

        private String description;
        private String[] roles;

        // getters and setters for all properties
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String[] getRoles() {
            return roles;
        }

        public void setRoles(String[] roles) {
            this.roles = roles;
        }

    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("adduser", "appUser", new Form());
    }

    @PostMapping("/create")
    public String create(Form form) throws IOException {
        umService.createAppUser(form.getUsername(),
                form.getPassword(), form.getEmail(), form.getDescription(), form.getRoles());
        return "redirect:/user/list";
    }

    @GetMapping("/delete/{username}")
    public String deletePhoto(@PathVariable("username") String username) {
        umService.delete(username);
        return "redirect:/user/list";
    }

    @GetMapping("/profile/{username}")
    public String view(@PathVariable("username") String username,
                       ModelMap model)
            throws UserNotFound{
        AppUser appUser = umService.getAppUser(username);
        model.addAttribute("username", username);
        model.addAttribute("appUser", appUser);
        return "profile";
    }

}