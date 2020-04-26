package com.cntytz.web.controller.copier;

import com.cntytz.web.controller.vo.UserVo;
import com.cntytz.web.dao.rdbms.entity.User;
import com.cntytz.yunti.controller.copier.BaseCopier;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * @program: luban
 * @description: userCopier
 * @author: ling
 * @create: 2020-04-11 17:04
 **/
@Mapper(componentModel = "spring")
@Component
public interface UserCopier extends BaseCopier<User, UserVo> {
}
