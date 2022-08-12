package com.asurplus.system.service;

import com.asurplus.common.utils.RES;
import com.asurplus.system.entity.SysUserInfo;
import org.springframework.web.multipart.MultipartFile;

public interface SysUserProfileService {

    RES getProfile();

    RES updateProfile(SysUserInfo sysUserInfo);

    RES updateAvatar(MultipartFile file);

    RES updatePwd(String oldPassword, String newPassword);
}
