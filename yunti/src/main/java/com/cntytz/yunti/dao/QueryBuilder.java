package com.cntytz.yunti.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.CaseFormat;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @program: luban
 * @description: 查询条件构造器
 * @author: ling
 * @create: 2020-04-15 20:53
 **/
@Slf4j
public class QueryBuilder {

    public static <T, DTO> QueryWrapper<T> toQueryWrapper(DTO dto) {
        return toQueryWrapper(dto, null);
    }

    public static <T, DTO> LambdaQueryWrapper<T> toLambdaQueryWrapper(DTO dto) {
        return (LambdaQueryWrapper<T>) toQueryWrapper(dto).lambda();
    }

    public static <T, DTO> QueryWrapper<T> toQueryWrapper(DTO dto, Collection<String> fields) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        return dtoToWrapper(wrapper, dto, fields);
    }

    /**
     * 转换具体实现
     *
     * @param wrapper
     * @param dto
     * @param <T>
     * @return
     */
    private static <T, DTO> QueryWrapper<T> dtoToWrapper(QueryWrapper<T> wrapper, DTO dto, Collection<String> fields) {
        List<Field> declaredFields = extractAllFields(dto.getClass());
        for (Field field : declaredFields) {
            // 非指定属性，非逻辑删除字段，跳过
            if (fields != null && !fields.contains(field.getName())) {
                continue;
            }
            //忽略static，以及final，transient
            boolean isStatic = Modifier.isStatic(field.getModifiers());
            boolean isFinal = Modifier.isFinal(field.getModifiers());
            boolean isTransient = Modifier.isTransient(field.getModifiers());
            if (isStatic || isFinal || isTransient) {
                continue;
            }
            //忽略注解 @TableField(exist = false) 的字段
            TableField tableField = field.getAnnotation(TableField.class);
            if (tableField != null && !tableField.exist()) {
                continue;
            }
            BindQuery query = field.getAnnotation(BindQuery.class);
            //忽略字段
            if (query != null && query.ignore()) {
                continue;
            }
            //打开私有访问 获取值
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(dto);
            } catch (IllegalAccessException e) {
                log.error("通过反射获取属性值出错：" + e);
            }
            if (value == null) {
                continue;
            }
            // 对比类型
            SqlKeyWordExpand keyword = (query != null) ? query.keyWord() : SqlKeyWordExpand.EQ;
            // 转换条件
            String columnName = getColumnName(field);
            switch (keyword) {
                case EQ:
                    wrapper.eq(columnName, value);
                    break;
                case IN:
                    if (value.getClass().isArray()) {
                        Object[] valueArray = (Object[]) value;
                        if (valueArray.length == 1) {
                            wrapper.in(columnName, valueArray[0]);
                        } else if (valueArray.length >= 2) {
                            wrapper.in(columnName, valueArray);
                        }
                    } else {
                        wrapper.in(columnName, value);
                    }
                    break;
                case CONTAINS:
                case LIKE:
                    wrapper.like(columnName, value);
                    break;
                case STARTWITH:
                    wrapper.likeRight(columnName, value);
                    break;
                case GT:
                    wrapper.gt(columnName, value);
                    break;
                case BETWEEN_BEGIN:
                case GE:
                    wrapper.ge(columnName, value);
                    break;
                case LT:
                    wrapper.lt(columnName, value);
                    break;
                case BETWEEN_END:
                case LE:
                    wrapper.le(columnName, value);
                    break;
                case BETWEEN:
                    if (value.getClass().isArray()) {
                        Object[] valueArray = (Object[]) value;
                        if (valueArray.length == 1) {
                            wrapper.ge(columnName, valueArray[0]);
                        } else if (valueArray.length >= 2) {
                            wrapper.between(columnName, valueArray[0], valueArray[1]);
                        }
                    }
                    // 支持逗号分隔的字符串
                    else if (value instanceof String && ((String) value).contains(",")) {
                        Object[] valueArray = ((String) value).split(",");
                        wrapper.between(columnName, valueArray[0], valueArray[1]);
                    } else {
                        wrapper.ge(columnName, value);
                    }
                    break;
                default:
            }
        }
        return wrapper;
    }

    /**
     * 获取类所有属性（包含父类中属性）
     *
     * @param clazz
     * @return
     */
    public static List<Field> extractAllFields(Class clazz) {
        List<Field> fieldList = new ArrayList<>();
        Set<String> fieldNameSet = new HashSet<>();
        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            //被重写属性，以子类override的为准
            if (fields.length > 0) {
                Arrays.stream(fields).forEach((field) -> {
                    if (!fieldNameSet.contains(field.getName())) {
                        fieldList.add(field);
                        fieldNameSet.add(field.getName());
                    }
                });
            }
            clazz = clazz.getSuperclass();
        }
        return fieldList;
    }

    /**
     * 获取数据表的列名（驼峰转下划线蛇形命名）
     * <br>
     * 列名取值优先级： @BindQuery.field > @TableField.value > field.name
     *
     * @param field
     * @return
     */
    private static String getColumnName(Field field) {
        String columnName = null;
        if (field.isAnnotationPresent(BindQuery.class)) {
            columnName = field.getAnnotation(BindQuery.class).field();
        } else if (field.isAnnotationPresent(TableField.class)) {
            columnName = field.getAnnotation(TableField.class).value();
        }
        return StringUtils.isBlank(columnName) ? CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName()) : columnName;
    }

}
