package com.asurplus.system.controller;

import com.asurplus.common.utils.RES;
import com.asurplus.system.entity.SysUserInfo;
import com.asurplus.system.service.SysUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/system/sys-user-profile")
public class SysUserProfileController {

    @Autowired
    private SysUserProfileService sysUserProfileService;

    @GetMapping
    public RES getProfile() {
        return sysUserProfileService.getProfile();
    }

    @PostMapping("updateAvatar")
    public RES updateAvatar(@RequestParam("file") MultipartFile file) {
        return sysUserProfileService.updateAvatar(file);
    }

    @PutMapping
    public RES updateProfile(@RequestBody SysUserInfo sysUserInfo) {
        return sysUserProfileService.updateProfile(sysUserInfo);
    }

    @PutMapping("updatePwd")
    public RES updatePwd(String oldPassword, String newPassword) {
        return sysUserProfileService.updatePwd(oldPassword, newPassword);
    }

}
