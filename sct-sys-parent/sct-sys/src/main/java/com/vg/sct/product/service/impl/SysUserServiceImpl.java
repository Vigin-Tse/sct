package com.vg.sct.product.service.impl;

import com.vg.sct.common.support.constants.ClientSecretEnum;
import com.vg.sct.common.domain.model.sys.SysUserModel;
import com.vg.sct.common.support.exception.BusinessException;
import com.vg.sct.common.support.http.HttpResponse;
import com.vg.sct.common.utils.MD5Utils;
import com.vg.sct.feign.auth.api.OauthFeignApi;
import com.vg.sct.product.constants.UserCommonConstants;
import com.vg.sct.product.domain.dto.UserUpdateDto;
import com.vg.sct.product.domain.vo.UserInfoVo;
import com.vg.sct.product.repository.SysUserRepository;
import com.vg.sct.product.service.SysUserService;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private SysUserRepository userRepository;

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

    @Override
    public UserInfoVo getUserInfo(Integer userId) {
        UserInfoVo userInfoVo = new UserInfoVo();

        SysUserModel userModel = userRepository.getOne(userId);
        BeanUtils.copyProperties(userModel, userInfoVo);
        return userInfoVo;
    }

    @Override
    public UserUpdateDto createUser(UserUpdateDto userUpdateDto) {
        if (userUpdateDto != null && userUpdateDto.getId() == null){
            SysUserModel saveModel = new SysUserModel();
            BeanUtils.copyProperties(userUpdateDto, saveModel);

            //初始化默认密码
            saveModel.setPassword(MD5Utils.encodeMd5(MD5Utils.DEFAULT_PASSWORD));
            saveModel.setIsActive(UserCommonConstants.IsActive.Y);
            saveModel =  this.userRepository.save(saveModel);
            BeanUtils.copyProperties(saveModel, userUpdateDto);
        }else{
            throw new BusinessException("新建用户失败，请重新录入用户资料！");
        }
        return userUpdateDto;
    }

    @Override
    public UserUpdateDto updateUser(UserUpdateDto userUpdateDto) {
        if (userUpdateDto != null && userUpdateDto.getId() != null){
            SysUserModel model = this.userRepository.findById(userUpdateDto.getId()).orElse(null);
            if (model == null){
                throw new BusinessException("更新用户资料失败，用户不存在");
            }

            BeanUtils.copyProperties(userUpdateDto, model);
            model = this.userRepository.save(model);
            BeanUtils.copyProperties(model, userUpdateDto);
        }else{
            throw new BusinessException("更新用户资料失败，请重新填入修改资料！");
        }
        return userUpdateDto;
    }
}
