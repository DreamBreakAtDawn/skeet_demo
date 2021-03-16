package com.skeet.h2;

import com.skeet.h2.entity.UmsUser;
import com.skeet.h2.util.DateUtil;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Desc:用于生成数据库表数据的xml格式
 *
 * @author chengsj
 * @date 2020/10/28 15:17
 */
public class DataSetXmlGenerator {


    private static Pattern linePattern = Pattern.compile("_([a-z])");

    private static Pattern humpPattern = Pattern.compile("\\B(\\p{Upper})(\\p{Lower}*)");

//    private static Pattern humpPattern2 = Pattern.compile("\\B([A-Z])([a-z]*)");

    public static void main(String[] args) {

        UmsUser user = UmsUser.builder()
                .tid(20)
                .username("talon")
                .password("live")
                .build();

        System.out.println(generatorData(user));
    }

    private static String generatorData(Object... objs) {
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder
                .append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n")
                .append("<dataset>\n");

        for (Object o : objs) {
            Class<?> clazz = o.getClass();
            Field[] fields = clazz.getDeclaredFields();
            String clazzSimpleName = clazz.getSimpleName();
            resultBuilder.append("    <" + humpToLine(clazzSimpleName) + "\n");

            for (int i = 0; i < fields.length; i++) {
                try {
                    Field field = fields[i];
                    String fieldName = field.getName();
                    PropertyDescriptor descriptor = new PropertyDescriptor(fieldName, clazz);
                    Object invoke = descriptor.getReadMethod().invoke(o);
                    if (Objects.nonNull(invoke)) {
                        resultBuilder
                                .append("            ")
                                .append(humpToLine(fieldName))
                                .append("=\"")
                                .append(toString(invoke))
                                .append("\"\n");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            resultBuilder.replace(resultBuilder.length() - 1, resultBuilder.length(), "/>\n\n");
        }

        resultBuilder.append("</dataset>");
        return resultBuilder.toString();
    }

    private static String toString(Object o) {
        if (o instanceof Date) {
            return DateUtil.convertDate2String((Date) o, DateUtil.FORMAT_Y_M_D_H_M_S);
        }
        return o.toString();
    }

    public static String humpToLine(String str) {
        StringBuffer sb = new StringBuffer();
        Matcher matcher = humpPattern.matcher(str);
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.replace(0, 1, String.valueOf(str.charAt(0)).toLowerCase()).toString();
    }
}
