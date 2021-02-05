package com.skeet.consul.provider.util;

import com.google.common.base.Splitter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

/**
 * @Description
 * @Author chengsj
 * @Date 2021/2/2 20:03
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SkeetStringUtil {

    public static List<String> split(String lst, String delimiter) {
        return Splitter.on(delimiter).splitToList(lst);
    }

    public static String convertUnderlineToCamel(String columnName) {
        StringBuilder builder = new StringBuilder();
        List<String> ss = Splitter.on("_").splitToList(columnName);

        ss.forEach(i -> {
            if (builder.length() == 0) {
                builder.append(i);
            } else {
                builder.append(firstToUpperCase(i));
            }
        });
        return builder.toString();
    }

    public static String convertCamelToUnderline(String str) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                builder.append("_");
            }

            builder.append(Character.toLowerCase(c));
        }

        return builder.toString();
    }

    public static String firstToUpperCase(String i) {
        return i.substring(0, 1).toUpperCase() + i.substring(1);
    }

    /**
     * @param str
     * @param obj
     * @return
     */
    public static String replace(String str, Object obj) {
        String tarStr = str;
        try {
            Class clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                String name = f.getName();
                String strOld = "_" + name;
                PropertyDescriptor pd = new PropertyDescriptor(name, clazz);
                Method readMethod = pd.getReadMethod();
                Object invoke = readMethod.invoke(obj);
                if (Objects.isNull(invoke)) {
                    continue;
                }
                String strNew = invoke.toString();
                tarStr = tarStr.replace(strOld, strNew);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tarStr;
    }
}
