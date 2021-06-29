package com.skeet.rabbitmq.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description QT反射工具类
 * @Author chengsj
 * @Date 2021/3/10 15:57
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReflectionUtil {

    @SneakyThrows
    public static <T> List<String> getFieldValues(Class<T> clazz) {
        List<String> resultList = new ArrayList<>();
        Field[] fields = clazz.getFields();
        for(Field field : fields){
            resultList.add(field.get(clazz).toString());
        }
        return resultList;
    }
}
