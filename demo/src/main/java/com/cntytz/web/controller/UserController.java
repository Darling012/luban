package com.cntytz.web.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cntytz.web.controller.copier.UserCopier;
import com.cntytz.web.controller.vo.UserVo;
import com.cntytz.web.dao.rdbms.entity.User;
import com.cntytz.web.service.BaseUserService;
import com.cntytz.web.service.UserService;
import com.cntytz.yunti.body.pojo.PageCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: demo
 * @description:
 * @author: ling
 * @create: 2020-03-10 21:06
 **/
@Api
@RestController
@RequestMapping("user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserCopier userCopier;
    @Autowired
    private BaseUserService baseUserService;

    @ApiOperation(value = "通过ID获取用户", notes = "查询用户")
    @GetMapping("{id}")
    public UserVo queryUserById(@PathVariable String id) {
        User user = userService.queryUserById(id);
        return userCopier.entityToVo(user);
    }

    @GetMapping("test-string")
    public String test() {
        return "ok";
    }

    @GetMapping("email")
    public List<UserVo> queryByEmail() {
        List<User> list = userService.queryByEmail("1111");
        return list.stream().map(e -> userCopier.entityToVo(e)).collect(Collectors.toList());
    }

    @PostMapping("page")
    public IPage<User> queryTable(PageCondition<UserVo> condition) {
        return userService.queryByLimit();
    }

    @PostMapping
    public void insertValue(@RequestBody UserVo userVo) {
        userService.insert(userCopier.voToEntity(userVo));
    }

    @PutMapping
    public void updateUser(@RequestBody UserVo userVo) {
        userService.update(userCopier.voToEntity(userVo));
    }

    @GetMapping("request-param")
    public void exceptionTest(@RequestParam String a) {
        System.out.println(a);
    }

    @GetMapping("base-test")
    public User baseTest() {
        return baseUserService.getEntityById("a");
    }
}
