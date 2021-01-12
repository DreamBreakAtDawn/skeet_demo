package com.skeet.consul.provider.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.skeet.consul.provider.modules.project.dispatch.model.domain.InboundManifestMsgDTO;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.reflection.MetaObject;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/2/6 21:08
 */
public class ReflectUtil {

    public static void main(String[] args) throws Exception {
        Class<?> metaClass = Class.forName("org.apache.ibatis.reflection.SystemMetaObject");
        Method method = metaClass.getDeclaredMethod("forObject", Object.class);
        InboundManifestMsgDTO build = InboundManifestMsgDTO.builder().build();
        MetaObject invoke = (MetaObject) method.invoke(null, build);
        System.out.println(invoke);
    }

    public static String getPropertyDataSourceNameForNonNull(Object obj, String prefix) {
        String dot = StringUtils.isNotBlank(prefix) ? "." : "";

        if (obj == null) {
            return prefix + dot + "*";
        }

        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder builder = new StringBuilder();
        try {
            for (Field f : fields) {
                String fieldName = f.getName();

                PropertyDescriptor pd = null;
                pd = new PropertyDescriptor(fieldName, clazz);
                Method readMethod = pd.getReadMethod();
                Object invoke = readMethod.invoke(obj);
                if (invoke == null) {
                    continue;
                }

                builder.append(prefix).append(dot).append(MyStringUtil.convertCamelToUnderline(fieldName)).append(",");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return builder.delete(builder.length() - 1, builder.length()).toString();
    }

    public static <T> T setDefaultValue(T obj) {
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field f : fields) {
                if (Modifier.isStatic(f.getModifiers())) {
                    continue;
                }

                String fieldName = f.getName();

                PropertyDescriptor pd = null;
                pd = new PropertyDescriptor(fieldName, clazz);

                Class<?> typeClass = f.getType();
                String type = typeClass.getName();

                Object val = null;

                if (type.contains("String")) {
                    val = "";
//                } else if (type.contains("Date")) {
//                    val = new Date();
                } else if (type.contains("BigDecimal")) {
                    val = BigDecimal.ZERO;
                } else if (type.contains("Integer")) {
                    val = 0;
                } else if (type.contains("Long")) {
                    val = 0;
                } else if (type.contains("List")) {
                    val = Lists.newArrayList();
                } else if (type.contains("Map")) {
                    val = Maps.newHashMap();
                } else {
                    val = typeClass.newInstance();
                }


                Method writeMethod = pd.getWriteMethod();
                writeMethod.invoke(obj, val);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }
}
