package com.cntytz.web.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cntytz.web.dao.rdbms.entity.User;
import com.cntytz.web.dao.rdbms.repo.mapper.UserMapper;
import com.cntytz.web.exception.UserException;
import com.cntytz.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @program: demo
 * @description:
 * @author: ling
 * @create: 2020-03-10 21:08
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserById(String id) {
        User user = userMapper.selectById(id);
        return Optional.ofNullable(user).orElseThrow(() -> new UserException("ddd"));
    }

    @Override
    public List<User> queryByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    @Override
    public IPage<User> queryByLimit() {
        Page<User> page = new Page<>(1, 10);
        return userMapper.selectPage(page, null);
    }

    @Override
    public void insert(User user) {
        int num = userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        int num = userMapper.update(user, null);
    }


}
