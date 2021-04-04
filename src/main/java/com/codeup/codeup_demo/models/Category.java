package com.codeup.codeup_demo.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="categories")
public class Category {

    public Category(){}

    public Category(String name){
        this.name=name;
    }

    public Category(String name, long id){
        this.name=name;
        this.id=id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String name;

    public Category(long id, String category){
        this.id = id;
        this.name = name;
    }


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return this.name;
    }

    public void setCategory(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "categories")
    private List<Post> posts;

}
