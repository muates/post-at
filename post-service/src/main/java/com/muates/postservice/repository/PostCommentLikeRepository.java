package com.muates.postservice.repository;

import com.muates.postservice.model.entity.PostCommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCommentLikeRepository extends JpaRepository<PostCommentLike, Long> {
}
