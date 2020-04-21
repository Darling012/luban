package com.cntytz.web.dao.rdbms.repo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cntytz.web.dao.rdbms.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Darling
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> findByEmail(@Param("email") String email);
}
