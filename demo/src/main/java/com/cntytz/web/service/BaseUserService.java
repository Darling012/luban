package com.cntytz.web.service;


import com.cntytz.web.dao.rdbms.entity.User;
import com.cntytz.yunti.service.BaseService;

import java.util.List;

/**
 * @author Darling
 */
public interface BaseUserService extends BaseService<User> {
    List<User> queryByEmail(String email);
}
