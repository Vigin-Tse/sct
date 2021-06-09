package com.vg.sct.common.support.context;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * 配置实现 AuditorAware类 才能使 @CreatedBy、@LastModifiedBy 注解生效
 */
public class ExecuteAuditorAware implements AuditorAware<Integer> {


    @Override
    public Optional<Integer> getCurrentAuditor() {
        if (LoginUserContext.getCurrentUser() == null){
            return Optional.ofNullable(null);
        }

        return Optional.ofNullable(LoginUserContext.getCurrentUser().getId());
    }
}
