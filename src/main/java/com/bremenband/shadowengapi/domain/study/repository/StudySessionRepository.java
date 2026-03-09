package com.bremenband.shadowengapi.domain.study.repository;

import com.bremenband.shadowengapi.domain.study.entity.SessionStatus;
import com.bremenband.shadowengapi.domain.study.entity.StudySession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudySessionRepository extends JpaRepository<StudySession, Long> {

    Optional<StudySession> findTopByUser_IdAndStatusOrderByCreatedAtDesc(Long userId, SessionStatus status);

    List<StudySession> findByUser_IdAndStatusOrderByCreatedAtDesc(Long userId, SessionStatus status);
}
