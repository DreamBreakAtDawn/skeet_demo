package com.skeet.consul.provider.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: json转换工具
 * @Author: echo
 * @Date: 2020/10/29 18:51
 * @Version: 1.0
 */
@Slf4j
public class FastJsonUtil {

    /**
     * 对象序列化为string
     *
     * @param object 对象
     * @return string
     */
    public static String toJSONString(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * 将字符串反序列化为对象, 该方法只支持JDK已有的类型(Map, List, set...),需要自定义返回类型, 需要通过TypeReference指定
     *
     * @param jsonString 字符串
     * @param <T>        结果类型
     * @return
     */
    public static <T> T parse(String jsonString) {
        return parse(jsonString, new TypeReference<T>() {
        });

    }

    /**
     * 将json转换成对象
     *
     * @param jsonString    json字符串
     * @param typeReference 转化后的对象类型，兼容泛型
     * @return 对象
     */
    public static <T> T parse(String jsonString, TypeReference<T> typeReference) {
        return JSON.parseObject(jsonString, typeReference);
    }

    /**
     * 将object转换成jsonObject
     *
     * @param object 转换对象
     * @return
     * @author lingbiaokong
     */
    public static JSONObject parseObjectToJsonObj(Object object) {
        return JSON.parseObject(JSON.toJSONString(object));
    }

    /**
     * 对象转字节数组
     *
     * @param object object
     * @return 字节数组
     */
    public static byte[] toByte(Object object) {
        return JSON.toJSONBytes(object);
    }

    /**
     * 使用jackson序列化，目前暂时为了支持枚举转换成指定数值
     *
     * @param object 需要序列化的对象
     * @return jsonStr
     */
    public static String jacksonSerialize(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
