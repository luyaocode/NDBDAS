package com.asurplus.system.controller;

import com.asurplus.common.utils.RES;
import com.asurplus.system.service.LoginService;
import com.asurplus.system.vo.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     *
     * @param loginDTO
     * @return
     */
    @PostMapping("login")
    public RES login(@RequestBody LoginDTO loginDTO) {
        return loginService.login(loginDTO);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("getInfo")
    public RES getInfo() {
        return loginService.getInfo();
    }

    /**
     * 获取路由信息
     *
     * @return
     */
    @GetMapping("getRouters")
    public RES getRouters() {
        return loginService.getRouters();
    }

    /**
     * 登出
     *
     * @return
     */
    @GetMapping("logout")
    public RES logout() {
        return loginService.logout();
    }
}
