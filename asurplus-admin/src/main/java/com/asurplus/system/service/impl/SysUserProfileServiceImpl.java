package com.asurplus.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.asurplus.common.minio.MinioUtils;
import com.asurplus.common.satoken.SaTokenSessionConst;
import com.asurplus.common.utils.PasswordUtils;
import com.asurplus.common.utils.RES;
import com.asurplus.system.entity.SysUserInfo;
import com.asurplus.system.mapper.SysUserInfoMapper;
import com.asurplus.system.mapper.SysUserRoleMapper;
import com.asurplus.system.service.SysUserProfileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class SysUserProfileServiceImpl implements SysUserProfileService {

    @Autowired
    private SysUserInfoMapper sysUserInfoMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private MinioUtils minioUtils;

    @Override
    public RES getProfile() {
        RES res = RES.ok();
        res.put("data", sysUserInfoMapper.getSysUserInfoVO(StpUtil.getLoginIdAsInt()));
        List<String> list = sysUserRoleMapper.listRoleNameByUserId(StpUtil.getLoginIdAsInt());
        if (CollectionUtil.isEmpty(list)) {
            res.put("roleGroup", null);
        } else {
            res.put("roleGroup", StringUtils.join(list));
        }
        return res;
    }

    @Override
    public RES updateProfile(SysUserInfo sysUserInfo) {
        if (null == sysUserInfo) {
            return RES.no("数据错误");
        }
        SysUserInfo dbObj = sysUserInfoMapper.selectById(StpUtil.getLoginIdAsInt());
        dbObj.setName(sysUserInfo.getName());
        dbObj.setPhone(sysUserInfo.getPhone());
        dbObj.setEmail(sysUserInfo.getEmail());
        dbObj.setSex(sysUserInfo.getSex());
        sysUserInfoMapper.updateById(sysUserInfo);
        // 删除缓存
        StpUtil.getTokenSession().delete(SaTokenSessionConst.USER_INFO);
        return RES.ok();
    }

    @Override
    public RES updateAvatar(MultipartFile file) {
        RES res = minioUtils.uploadFile(file, "system");
        if (200 != res.getCode()) {
            return res;
        }
        SysUserInfo sysUserInfo = sysUserInfoMapper.selectById(StpUtil.getLoginIdAsInt());
        sysUserInfo.setAvatar(String.valueOf(res.getData()));
        sysUserInfoMapper.updateById(sysUserInfo);
        // 删除缓存
        StpUtil.getTokenSession().delete(SaTokenSessionConst.USER_INFO);
        return RES.ok(res.getData());
    }

    @Override
    public RES updatePwd(String oldPassword, String newPassword) {
        if (StringUtils.isBlank(oldPassword)) {
            return RES.no("旧密码不能为空");
        }
        if (StringUtils.isBlank(newPassword)) {
            return RES.no("新密码不能为空");
        }
        if (oldPassword.equals(newPassword)) {
            return RES.no("新密码不能与旧密码相同");
        }
        SysUserInfo sysUserInfo = sysUserInfoMapper.selectById(StpUtil.getLoginIdAsInt());
        if (!PasswordUtils.getPassword(sysUserInfo.getAccount(), oldPassword).equals(sysUserInfo.getPassword())) {
            return RES.no("旧密码不正确");
        }
        sysUserInfo.setPassword(PasswordUtils.getPassword(sysUserInfo.getAccount(), newPassword));
        sysUserInfoMapper.updateById(sysUserInfo);
        return RES.ok();
    }
}
