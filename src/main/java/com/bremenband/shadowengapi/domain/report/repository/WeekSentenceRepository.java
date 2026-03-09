package com.bremenband.shadowengapi.domain.report.repository;

import com.bremenband.shadowengapi.domain.report.entity.WeekSentence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeekSentenceRepository extends JpaRepository<WeekSentence, Long> {

    void deleteByReport_Id(Long reportId);
}
