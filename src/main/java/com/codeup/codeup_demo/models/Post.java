package com.codeup.codeup_demo.models;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name="posts")
public class Post {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "text")
    private String body;



    @Column(length = 50, nullable = true)
    private String time;


    @OneToOne
    private User owner;

    public Post(){
        this.time=LocalDateTime.now().toString();
    }
    public Post(String title, String body){
        this.title=title;
        this.body=body;
        this.time=LocalDateTime.now().toString();
    }
    public Post(String title, String body, Long id){
        this.title=title;
        this.body=body;
        this.id=id;
    }
}
