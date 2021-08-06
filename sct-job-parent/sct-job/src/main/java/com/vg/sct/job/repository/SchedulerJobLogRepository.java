package com.vg.sct.job.repository;

import com.vg.sct.job.domain.model.SchedulerJobLogsModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: xieweij
 * @time: 2021/8/4 14:12
 */
public interface SchedulerJobLogRepository extends JpaRepository<SchedulerJobLogsModel, Integer> {
}
