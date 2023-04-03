package hkmu.comps380f.controller;

import hkmu.comps380f.dao.CommentService;
import hkmu.comps380f.dao.UserManagementService;
import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.model.AppUser;
import jakarta.annotation.Resource;
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


@Controller
@RequestMapping("/comment")
public class UserCommentController {
    @Resource
    CommentService cService;

    public static class Form {
        private String comment;

        public String getComments() {
            return comment;
        }
        public void setComments(String comment) {
            this.comment = comment;
        }

    }
    @GetMapping("/list")
    public String list(ModelMap model, Principal principal) {
        model.addAttribute("commentDB", cService.getComments());
        return "listComment";
    }

    @GetMapping("/{username}")
    public ModelAndView create() {
        return new ModelAndView("comment", "commentForm", new Form());}

    @PostMapping("/{username}")
    public String create(Form form,Principal principal, @PathVariable("username") String name
                         ) throws IOException {
        cService.createComment(principal.getName(),
                form.getComments(), name);
        return "redirect:/user/profile";
    }



}