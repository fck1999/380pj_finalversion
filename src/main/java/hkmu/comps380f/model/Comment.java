package hkmu.comps380f.model;

import hkmu.comps380f.model.AppUser;
import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int id;

    @Column(name = "username")
    private String username;
    @Column(name = "writer")
    private String writer;
    @Column(name = "content")
    private String comment;

    public Comment() {
    }

    public Comment(String writer, String comment, String username) {
        this.writer = writer;
        this.comment = comment;
        this.username = username;
    }

    // getters and setters of all properties


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}