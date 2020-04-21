package com.cntytz.web.dao.rdbms.entity;

import com.cntytz.yunti.dao.BaseEntity;
import lombok.Data;

/**
 * @program: demo
 * @description: user
 * @author: ling
 * @create: 2020-03-10 20:53
 **/
@Data
public class User extends BaseEntity {
    private String id;
    private String userName;
    private String passWord;
    private String email;
    private String nickName;
    private String regTime;
    private Integer flag;
}
