package com.codeup.codeup_demo.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Image> images;


    @Column(length = 50, nullable = true)
    private String time;


//    @OneToOne
//    private User owner;

    @ManyToOne
    @JoinColumn (name = "owner_id")
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category){
        categories.add(category);
    }


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="post_categories",
            joinColumns={@JoinColumn(name="post_id")},
            inverseJoinColumns={@JoinColumn(name="category_id")}
    )
    private List<Category> categories;
}
