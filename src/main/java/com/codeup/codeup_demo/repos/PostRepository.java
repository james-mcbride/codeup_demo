package com.codeup.codeup_demo.repos;

import com.codeup.codeup_demo.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostsByTitleContainingOrBodyContaining(String title, String body);
}
