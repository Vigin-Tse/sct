package com.vg.sct.sys.service.impl;

import com.vg.sct.common.constants.ClientSecretEnum;
import com.vg.sct.common.http.HttpResponse;
import com.vg.sct.feign.auth.api.OauthFeignApi;
import com.vg.sct.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author xieweij
 * @create 2020/7/8 16:49
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private OauthFeignApi oauthFeignApi;

    @Override
    public HttpResponse loginByUserNameAndPsw(String userName, String pwd) {

        //TODO 基础检验 userName、password参数

        //拼装登录请求
        Map<String, String> loginParam = new HashMap<>();
        loginParam.put("grant_type", "password");
        loginParam.put("client_id", ClientSecretEnum.SCT_WEB_CLIENT.getClient());
        loginParam.put("client_secret", ClientSecretEnum.SCT_WEB_CLIENT.getSecret());
        loginParam.put("username", userName);
        loginParam.put("password", pwd);

        //远程调用统一认证中心获取凭证
        return oauthFeignApi.postAccessToken(loginParam);
    }
}
