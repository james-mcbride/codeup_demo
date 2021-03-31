package com.codeup.codeup_demo.models;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="users")
public class User {

    User (){};
    User (String username, String password, String email){
        this.username=username;
        this.password=password;
        this.email=email;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String username;

    @Column(nullable = false, length = 250)
    private String password;


    @Column(nullable = false, length = 100)
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Post> posts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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



//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<Post> posts;
}
