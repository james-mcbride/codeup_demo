package com.codeup.codeup_demo.models;

import javax.persistence.*;

@Entity
@Table(name="images")
public class Image {

    Image(){};
    public Image(String path){
        this.path=path;
    }
    public Image(String path, Post post){
        this.path=path;
        this.post = post;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, columnDefinition = "text")
    private String path;

    @ManyToOne
    @JoinColumn (name = "post_id")
    private Post post;
}
