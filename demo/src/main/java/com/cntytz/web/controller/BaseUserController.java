package com.cntytz.web.controller;


import com.cntytz.web.controller.copier.UserCopier;
import com.cntytz.web.controller.vo.UserVo;
import com.cntytz.web.dao.rdbms.entity.User;
import com.cntytz.web.service.BaseUserService;
import com.cntytz.yunti.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: luban
 * @description: base user test
 * @author: ling
 * @create: 2020-04-15 14:22
 **/
@RestController
@RequestMapping("user")
public class BaseUserController extends BaseController<User, UserVo, UserVo> {
    public BaseUserController(BaseUserService baseUserService, UserCopier userCopier) {
        super(baseUserService, userCopier);
    }
}
