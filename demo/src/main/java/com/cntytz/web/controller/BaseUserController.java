package com.cntytz.web.controller;


import com.cntytz.web.controller.copier.UserCopier;
import com.cntytz.web.controller.vo.UserVo;
import com.cntytz.web.dao.rdbms.entity.User;
import com.cntytz.web.service.BaseUserService;
import com.cntytz.yunti.controller.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: luban
 * @description: base user test
 * @author: ling
 * @create: 2020-04-15 14:22
 **/
@RestController
@RequestMapping("user")
public class BaseUserController extends BaseController<User, UserVo, UserVo> {
    private final BaseUserService baseUserService;
    private final UserCopier userCopier;

    public BaseUserController(BaseUserService baseUserService, UserCopier userCopier) {
        super(baseUserService, userCopier);
        this.baseUserService = baseUserService;
        this.userCopier = userCopier;
    }

    @GetMapping("email/{email}")
    public List<UserVo> queryByEmail(@PathVariable String email) {
        return baseUserService.queryByEmail(email).stream().map(userCopier::entityToVo).collect(Collectors.toList());
    }
}
