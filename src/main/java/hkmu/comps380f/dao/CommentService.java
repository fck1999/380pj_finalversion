package hkmu.comps380f.dao;

import hkmu.comps380f.exception.AttachmentNotFound;
import hkmu.comps380f.exception.PhotoNotFound;
import hkmu.comps380f.model.AppUser;
import hkmu.comps380f.model.Attachment;
import hkmu.comps380f.model.Comment;
import hkmu.comps380f.model.Photo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {
    private CommentRepository cRepo;
    public List<Comment> getComments() {
        return cRepo.findAll();
    }
    @Transactional
    public void createComment(String writer, String content,
                            String username)
            throws IOException {
        Comment comment = new Comment(writer, content, username);
        cRepo.save(comment);
    }
}
