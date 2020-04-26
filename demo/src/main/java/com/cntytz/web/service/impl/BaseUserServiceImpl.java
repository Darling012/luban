package com.cntytz.web.service.impl;


import com.cntytz.web.dao.rdbms.entity.User;
import com.cntytz.web.dao.rdbms.repo.mapper.UserMapper;
import com.cntytz.web.service.BaseUserService;
import com.cntytz.yunti.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: luban
 * @description:
 * @author: ling
 * @create: 2020-04-14 17:16
 **/
@Service
public class BaseUserServiceImpl extends BaseServiceImpl<UserMapper, User> implements BaseUserService {

    @Override
    public List<User> queryByEmail(String email) {
       return this.baseMapper.findByEmail(email);
    }
}
