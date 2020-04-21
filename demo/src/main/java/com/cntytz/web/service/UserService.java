package com.cntytz.web.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cntytz.web.dao.rdbms.entity.User;

import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: ling
 * @create: 2020-03-10 21:07
 **/
public interface UserService {

    User queryUserById(String id);

    List<User> queryByEmail(String email);

    IPage<User> queryByLimit();

    void insert(User user);

    void update(User user);
}
