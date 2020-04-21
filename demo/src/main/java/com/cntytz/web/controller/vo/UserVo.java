package com.cntytz.web.controller.vo;


import com.cntytz.yunti.dao.BindQuery;
import com.cntytz.yunti.dao.SqlKeyWordExpand;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: luban
 * @description: 返给前台的pojo
 * @author: ling
 * @create: 2020-04-11 16:58
 **/
@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = -2375386553549887420L;
    private String id;
    @BindQuery(keyWord = SqlKeyWordExpand.LIKE)
    private String userName;
    private String passWord;
    private String email;
}
