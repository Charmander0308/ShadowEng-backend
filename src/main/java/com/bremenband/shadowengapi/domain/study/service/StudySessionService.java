package com.bremenband.shadowengapi.domain.study.service;

import com.bremenband.shadowengapi.domain.study.dto.res.LatestActiveSessionResponse;
import com.bremenband.shadowengapi.domain.study.dto.res.RecentStudySessionResponse;
import com.bremenband.shadowengapi.domain.study.dto.res.ThumbnailsResponse;
import com.bremenband.shadowengapi.domain.study.entity.SessionStatus;
import com.bremenband.shadowengapi.domain.study.entity.StudySession;
import com.bremenband.shadowengapi.domain.study.repository.StudySessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudySessionService {

    private final StudySessionRepository studySessionRepository;

    public RecentStudySessionResponse getRecentSession(Long userId) {
        Optional<StudySession> sessionOpt = studySessionRepository
                .findTopByUser_IdAndStatusOrderByCreatedAtDesc(userId, SessionStatus.ACTIVE);

        if (sessionOpt.isEmpty()) {
            return new RecentStudySessionResponse(null);
        }

        StudySession session = sessionOpt.get();
        String videoId = session.getVideo().getVideoId();
        ThumbnailsResponse thumbnails = ThumbnailsResponse.from(videoId);

        LatestActiveSessionResponse latestSession = new LatestActiveSessionResponse(
                session.getId(),
                thumbnails,
                session.getProgressRate()
        );

        return new RecentStudySessionResponse(latestSession);
    }
}
