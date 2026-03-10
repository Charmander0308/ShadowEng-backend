package com.bremenband.shadowengapi.domain.bookmark.repository;

import com.bremenband.shadowengapi.domain.bookmark.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    List<Bookmark> findByUser_IdOrderByCreatedAtDesc(Long userId);
}
