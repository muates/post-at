package com.muates.postservice.repository;

import com.muates.postservice.model.entity.PostComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

    Page<PostComment> findByPostId(Long postId, Pageable pageable);

    @Query("SELECT COUNT(pc) FROM PostComment pc WHERE pc.post.id = :postId")
    int countByPostId(@Param("postId") Long postId);
}
