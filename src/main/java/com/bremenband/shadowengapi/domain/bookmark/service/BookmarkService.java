package com.bremenband.shadowengapi.domain.bookmark.service;

import com.bremenband.shadowengapi.domain.bookmark.dto.res.BookmarkListResponse;
import com.bremenband.shadowengapi.domain.bookmark.entity.Bookmark;
import com.bremenband.shadowengapi.domain.bookmark.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    @Transactional(readOnly = true)
    public BookmarkListResponse getBookmarks(Long userId) {
        List<Bookmark> bookmarks = bookmarkRepository.findByUser_IdOrderByCreatedAtDesc(userId);

        List<BookmarkListResponse.BookmarkItem> items = bookmarks.stream()
                .map(BookmarkListResponse.BookmarkItem::from)
                .toList();

        return new BookmarkListResponse(items);
    }
}
