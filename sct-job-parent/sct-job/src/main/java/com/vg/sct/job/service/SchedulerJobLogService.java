package com.vg.sct.job.service;

import com.vg.sct.common.support.http.PageInfo;
import com.vg.sct.job.domain.model.SchedulerJobLogsModel;

import java.util.List;

/**
 * @author: xieweij
 * @time: 2021/8/4 14:10
 */
public interface SchedulerJobLogService {

    /**
     * 分页查询
     * @param pageInfo
     * @return
     */
    List<SchedulerJobLogsModel> findListPages(PageInfo pageInfo);

    /**
     * 添加日志
     * @param log
     */
    void addLog(SchedulerJobLogsModel log);


}
