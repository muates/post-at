package com.muates.postservice.repository;

import com.muates.postservice.model.entity.PostComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

    Page<PostComment> findByPostId(Long postId, Pageable pageable);

    @Query("SELECT COUNT(pc) FROM PostComment pc WHERE pc.post.id = :postId")
    Integer countByPostId(@Param("postId") Long postId);

    @Query("SELECT pc.post.id, COUNT(pc) " +
            "FROM PostComment pc " +
            "WHERE pc.post.id IN :postIds " +
            "GROUP BY pc.post.id")
    List<Object[]> countByPostIds(@Param("postIds") List<Long> postIds);
}
