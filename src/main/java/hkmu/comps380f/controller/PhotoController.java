package hkmu.comps380f.controller;

import hkmu.comps380f.dao.PhotoService;
import hkmu.comps380f.exception.AttachmentNotFound;
import hkmu.comps380f.exception.PhotoNotFound;
import hkmu.comps380f.model.Attachment;
import hkmu.comps380f.model.Photo;
import hkmu.comps380f.view.DownloadingView;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/photo")
public class PhotoController {

    @Resource
    private PhotoService tService;

    // Controller methods, Form-backing object, ...
    @GetMapping(value = {"", "/list"})
    public String list(ModelMap model) {
        model.addAttribute("photoDatabase", tService.getPhotos());
        return "list";
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("add", "photoForm", new Form());
    }

    public static class Form {
        private String subject;
        private String body;
        private List<MultipartFile> attachments;

        // Getters and Setters of customerName, subject, body, attachments

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public List<MultipartFile> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<MultipartFile> attachments) {
            this.attachments = attachments;
        }
    }

    @PostMapping("/create")
    public View create(Form form, Principal principal) throws IOException {
        long photoId = tService.createPhoto(principal.getName(),
                form.getSubject(), form.getBody(), form.getAttachments());
        return new RedirectView("/photo/view/" + photoId, true);
    }

    @GetMapping("/view/{photoId}")
    public String view(@PathVariable("photoId") long photoId,
                       ModelMap model)
            throws PhotoNotFound{
        Photo photo = tService.getPhoto(photoId);
        model.addAttribute("photoId", photoId);
        model.addAttribute("photo", photo);
        return "view";
    }

    @GetMapping("/{photoId}/attachment/{attachment:.+}")
    public View download(@PathVariable("photoId") long photoId,
                         @PathVariable("attachment") UUID attachmentId)
            throws PhotoNotFound, AttachmentNotFound {
        Attachment attachment = tService.getAttachment(photoId, attachmentId);
        return new DownloadingView(attachment.getName(),
                    attachment.getMimeContentType(), attachment.getContents());
    }

    @GetMapping("/delete/{photoId}")
    public String deletePhoto(@PathVariable("photoId") long photoId)
            throws PhotoNotFound {
        tService.delete(photoId);
        return "redirect:/photo/list";
    }

    @GetMapping("/{phototId}/delete/{attachment:.+}")
    public String deleteAttachment(@PathVariable("photoId") long photoId,
                                   @PathVariable("attachment") UUID attachmentId)
            throws PhotoNotFound, AttachmentNotFound {
        tService.deleteAttachment(photoId, attachmentId);
        return "redirect:/photo/view/" + photoId;
    }

    @GetMapping("/edit/{photoId}")
    public ModelAndView showEdit(@PathVariable("photoId") long photoId,
                                 Principal principal, HttpServletRequest request)
            throws PhotoNotFound {
        Photo photo = tService.getPhoto(photoId);
        if (photo == null
                || (!request.isUserInRole("ROLE_ADMIN")
                && !principal.getName().equals(photo.getCustomerName()))) {
            return new ModelAndView(new RedirectView("/photo/list", true));
        }

        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("photo", photo);

        Form photoForm = new Form();
        photoForm.setSubject(photo.getSubject());
        photoForm.setBody(photo.getBody());
        modelAndView.addObject("photoForm", photoForm);

        return modelAndView;
    }

    @PostMapping("/edit/{photoId}")
    public String edit(@PathVariable("photoId") long photoId, Form form,
                       Principal principal, HttpServletRequest request)
            throws IOException, PhotoNotFound {
        Photo photo = tService.getPhoto(photoId);
        if (photo == null
                || (!request.isUserInRole("ROLE_ADMIN")
                && !principal.getName().equals(photo.getCustomerName()))) {
            return "redirect:/photo/list";
        }

        tService.updatePhoto(photoId, form.getSubject(),
                form.getBody(), form.getAttachments());
        return "redirect:/photo/view/" + photoId;
    }

    @ExceptionHandler({PhotoNotFound.class, AttachmentNotFound.class})
    public ModelAndView error(Exception e) {
        return new ModelAndView("error", "message", e.getMessage());
    }
}
