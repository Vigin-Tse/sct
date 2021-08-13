package com.vg.sct.job.service.impl;

import com.vg.sct.common.support.http.PageInfo;
import com.vg.sct.job.domain.model.SchedulerJobLogsModel;
import com.vg.sct.job.repository.SchedulerJobLogRepository;
import com.vg.sct.job.service.SchedulerJobLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: xieweij
 * @time: 2021/8/4 14:10
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class SchedulerJobLogServiceImpl implements SchedulerJobLogService {

    @Autowired
    private SchedulerJobLogRepository logRepository;

    @Override
    public List<SchedulerJobLogsModel> findListPages(PageInfo pageInfo) {
        Page<SchedulerJobLogsModel> pageList = this.logRepository.findAll(PageRequest.of(pageInfo.getPageNum(), pageInfo.getPageSize()));
        pageInfo.setPageTotal(pageList.getTotalPages());
        pageInfo.setTotal(Integer.valueOf(String.valueOf(pageList.getTotalElements())));
        return pageList.toList();
    }

    @Override
    public void addLog(SchedulerJobLogsModel log) {
        logRepository.save(log);
    }

}
