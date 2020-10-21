package com.skeet.consul.provider.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.*;

import static com.skeet.consul.provider.util.JsonUtils.DATE_FORMAT;

/**
 * json工具类型
 *
 * @author wugx
 * @date 2017/7/25 16:24
 */
public class JsonUtils {

    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ";

    /**
     * 对象转为json字符串
     */
    public static String beanToJson(Object object) {
        if (Objects.isNull(object)) {
            return "";
        }
        Gson gson = GsonUtil.create();
        return gson.toJson(object);
    }

    /**
     * 将json字符串转为对象
     */
    public static <T> T jsonToBean(String json, Class<T> clazz) {
        T obj;
        try {
            Gson gson = GsonUtil.create();
            obj = gson.fromJson(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException(MessageFormat.format("Convert error: {0} to {1}", json, clazz), e);
        }
        return obj;
    }

    /**
     * json转为list 只适用于List简单对象[List<String>]，如果是List复杂对象[List<xxDTO>]请使用fromJsonArray
     */
    @Deprecated
    public static <T> List<T> jsonToList(String json, Class<T> clazz) {
        List<T> list;
        try {
            Gson gson = GsonUtil.create();
            list = gson.fromJson(json, new TypeToken<List<T>>() {
            }.getType());
        } catch (Exception e) {
            throw new RuntimeException(MessageFormat.format("Convert error: {0} to {1}", json, clazz), e);
        }
        return list;
    }

    /**
     * Json 转List
     * (此方法避免了泛型在编译期类型被擦除导致)
     */
    public static <T> List<T> fromJsonArray(String json, Class<T> clazz) {
        List<T> lst = new ArrayList<T>();
        try {
            Gson gson = GsonUtil.create();
            JsonArray array = new JsonParser().parse(json).getAsJsonArray();
            for (final JsonElement elem : array) {
                lst.add(gson.fromJson(elem, clazz));
            }
        } catch (Exception e) {
            throw new RuntimeException(MessageFormat.format("Convert error: {0} to {1}", json, clazz), e);
        }
        return lst;
    }

    /**
     * 将一个json流转化为对象
     *
     * @param in
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T jsonToBean(InputStream in, Class<T> clazz) {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
        byte[] bytes = new byte[1024];
        StringBuilder builder = new StringBuilder();
        try {
            while (bufferedInputStream.read(bytes) > 0) {
                builder.append(new String(bytes));
            }
            return jsonToBean(builder.toString().trim(), clazz);
        } catch (IOException e) {
            throw new RuntimeException(MessageFormat.format("Convert error: {0} to {1}", in, clazz), e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * json转为Set
     */
    public static <T> Set<T> jsonToSet(String json, Class<T> clazz) {
        Set<T> set;
        try {
            Gson gson = GsonUtil.create();
            set = gson.fromJson(json, new TypeToken<Set<T>>() {
            }.getType());
        } catch (Exception e) {
            throw new RuntimeException(MessageFormat.format("Convert error: {0} to {1}", json, clazz), e);
        }
        return set;
    }

    /**
     * GSON对于泛型的支持不足，为了使GSON对于泛型进行解析，需要对type做如下调整
     */
    public static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            @Override
            public Type getRawType() {
                return raw;
            }

            @Override
            public Type[] getActualTypeArguments() {
                return args;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
    }

    /**
     * 将json字符串转为对象
     */
    public static <T> T jsonToBean(String json, final Class<T> raw, final Type... args) {
        T obj;
        try {
            Gson gson = GsonUtil.create();
            obj = gson.fromJson(json, type(raw, args));
        } catch (Exception e) {
            throw new RuntimeException(MessageFormat.format("Convert error: {0} to {1}", json, raw), e);
        }
        return obj;
    }
}

class GsonUtil {
    private GsonUtil() {
    }

    static Gson create() {
        GsonBuilder gb = new GsonBuilder();
        gb.setDateFormat(DATE_FORMAT);
        gb.registerTypeAdapter(Date.class, new DateAdapter());
//        gb.serializeNulls();
        return gb.create();
    }
}

class DateAdapter implements JsonDeserializer<Date> {
    private final DateTimeFormatter fmt1 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZZ");
    private final DateTimeFormatter fmt2 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");
    private final DateTimeFormatter fmt3 = DateTimeFormat.forPattern("EEE MMM dd HH:mm:ss Z yyyy").withLocale(Locale.ENGLISH);
    private final DateTimeFormatter fmt4 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.S");

    @Override
    public Date deserialize(JsonElement json, Type arg1,
                            JsonDeserializationContext arg2) throws JsonParseException {
        String dataJson = json.getAsString();
        if (StringUtils.isBlank(dataJson)) {
            return null;
        }
        if (StringUtils.isNumeric(dataJson)) {
            return new Date(json.getAsJsonPrimitive().getAsLong());
        }
        if (!dataJson.contains("-")) {
            DateTime dateTime = fmt3.parseDateTime(dataJson);
            return dateTime.toDate();
        }
        if (dataJson.contains(".")) {
            try {
                return fmt2.parseDateTime(dataJson).toDate();
            } catch (Exception e) {
                return fmt4.parseDateTime(dataJson).toDate();
            }
        }
        return fmt1.parseDateTime(dataJson).toDate();
    }

}
