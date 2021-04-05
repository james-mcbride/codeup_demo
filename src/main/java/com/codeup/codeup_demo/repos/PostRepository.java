package com.codeup.codeup_demo.repos;

import com.codeup.codeup_demo.models.Category;
import com.codeup.codeup_demo.models.Post;
import com.codeup.codeup_demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostsByTitleContainingOrBodyContaining(String title, String body);
    List<Post> findPostsByCategoriesIsContaining(Category category);
    List<Post> findPostsByCategoriesIsContainingAndOwner(Category category, User owner);

    List<Post> findPostsByOwnerId(Long ownerId);

    Post findPostByTitleContaining(String ad_to_be_deleted);

}
