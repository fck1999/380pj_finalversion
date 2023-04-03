package hkmu.comps380f.dao;

import hkmu.comps380f.exception.AttachmentNotFound;
import hkmu.comps380f.exception.PhotoNotFound;
import hkmu.comps380f.model.Attachment;
import hkmu.comps380f.model.Photo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class PhotoService {
    @Resource
    private PhotoRepository tRepo;

    @Resource
    private AttachmentRepository aRepo;

    @Transactional
    public List<Photo> getPhotos() {
        return tRepo.findAll();
    }

    @Transactional
    public Photo getPhoto(long id)
            throws PhotoNotFound {
        Photo photo = tRepo.findById(id).orElse(null);
        if (photo == null) {
            throw new PhotoNotFound(id);
        }
        return photo;
    }

    @Transactional
    public Attachment getAttachment(long photoId, UUID attachmentId)
            throws PhotoNotFound, AttachmentNotFound {
        Photo photo = tRepo.findById(photoId).orElse(null);
        if (photo == null) {
            throw new PhotoNotFound(photoId);
        }
        Attachment attachment = aRepo.findById(attachmentId).orElse(null);
        if (attachment == null) {
            throw new AttachmentNotFound(attachmentId);
        }
        return attachment;
    }

    @Transactional(rollbackFor = PhotoNotFound.class)
    public void delete(long id) throws PhotoNotFound {
        Photo deletedPhoto = tRepo.findById(id).orElse(null);
        if (deletedPhoto == null) {
            throw new PhotoNotFound(id);
        }
        tRepo.delete(deletedPhoto);
    }

    @Transactional(rollbackFor = AttachmentNotFound.class)
    public void deleteAttachment(long photoId, UUID attachmentId)
            throws PhotoNotFound, AttachmentNotFound {
        Photo photo = tRepo.findById(photoId).orElse(null);
        if (photo == null) {
            throw new PhotoNotFound(photoId);
        }
        for (Attachment attachment : photo.getAttachments()) {
            if (attachment.getId().equals(attachmentId)) {
                photo.deleteAttachment(attachment);
                tRepo.save(photo);
                return;
            }
        }
        throw new AttachmentNotFound(attachmentId);
    }

    @Transactional
    public long createPhoto(String customerName, String subject,
                             String body, List<MultipartFile> attachments)
            throws IOException {
        Photo photo = new Photo();
        photo.setCustomerName(customerName);
        photo.setSubject(subject);
        photo.setBody(body);

        for (MultipartFile filePart : attachments) {
            Attachment attachment = new Attachment();
            attachment.setName(filePart.getOriginalFilename());
            attachment.setMimeContentType(filePart.getContentType());
            attachment.setContents(filePart.getBytes());
            attachment.setPhoto(photo);
            if (attachment.getName() != null && attachment.getName().length() > 0
                    && attachment.getContents() != null
                    && attachment.getContents().length > 0) {
                photo.getAttachments().add(attachment);
            }
        }
        Photo savedPhoto = tRepo.save(photo);
        return savedPhoto.getId();
    }
    @Transactional(rollbackFor = PhotoNotFound.class)
    public void updatePhoto(long id, String subject,
                             String body, List<MultipartFile> attachments)
            throws IOException, PhotoNotFound {
        Photo updatedPhoto = tRepo.findById(id).orElse(null);
        if (updatedPhoto == null) {
            throw new PhotoNotFound(id);
        }
        updatedPhoto.setSubject(subject);
        updatedPhoto.setBody(body);

        for (MultipartFile filePart : attachments) {
            Attachment attachment = new Attachment();
            attachment.setName(filePart.getOriginalFilename());
            attachment.setMimeContentType(filePart.getContentType());
            attachment.setContents(filePart.getBytes());
            attachment.setPhoto(updatedPhoto);
            if (attachment.getName() != null && attachment.getName().length() > 0
                    && attachment.getContents() != null
                    && attachment.getContents().length > 0) {
                updatedPhoto.getAttachments().add(attachment);
            }
        }
        tRepo.save(updatedPhoto);
    }
}
