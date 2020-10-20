package com.skeet.consul.provider.util;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2019/6/25 9:48
 */
public class MyStringUtil {

    public static void main(String[] args) {
//        String s = buildQueryIn(Lists.newArrayList("123", "456"));
//        System.out.println(s);

        String columnDefine = " `tid` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `user_id` varchar(20) NOT NULL DEFAULT '' COMMENT '用户ID',\n" +
                "  `username` varchar(10) NOT NULL DEFAULT '' COMMENT '用户名',\n" +
                "  `password` varchar(20) NOT NULL DEFAULT '' COMMENT '密码',\n" +
                "  `email` varchar(50) NOT NULL DEFAULT '' COMMENT '用户邮箱',\n" +
                "  `department` varchar(20) NOT NULL DEFAULT '' COMMENT '用户所属部门',\n" +
                "  `role` varchar(10) NOT NULL DEFAULT '0' COMMENT '用户角色',\n" +
                "  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
                "  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'";
        System.out.println(convertColumnToEntity(columnDefine));
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

    public static String buildQueryIn(List<String> lst) {
        StringBuilder builder = new StringBuilder();
        lst.forEach(i -> builder.append(String.format("'%s', ", i)));
        builder.replace(builder.length() - 2, builder.length(), "");
        return builder.toString();
    }

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

    private static String firstToUpperCase(String i) {
        return i.substring(0, 1).toUpperCase() + i.substring(1);
    }

    /**
     * 将表定义语句的字段转化成javabean的属性
     *
     * @param columnDefine
     * @return
     */
    public static String convertColumnToEntity(String columnDefine) {
        if (StringUtils.isBlank(columnDefine)) {
            return "";
        }
        List<String> resultList = Lists.newArrayList();
        List<String> list = Splitter.on(",\n").splitToList(columnDefine);
        Pattern compile = Pattern.compile("^\\s*`(.*)` ([a-zA-Z]*)(\\(.*\\))? .*'(.*)'$");
        list.forEach(ele -> {
            Matcher matcher = compile.matcher(ele);
            if (matcher.find()) {
                String result = String.format("    /**\n     * %s\n     */\n    private %s %s;\n",
                        matcher.group(4), obtainPropertyType(matcher.group(2)), convertUnderlineToCamel(matcher.group(1)));
//                String result = new StringBuilder()
//                        .append("    /**")
//                        .append("\n     * ")
//                        .append(matcher.group(4))
//                        .append("\n     */")
//                        .append("\n    private")
//                        .append(" ")
//                        .append(obtainPropertyType(matcher.group(2)))
//                        .append(" ")
//                        .append(convertUnderlineToCamel(matcher.group(1)))
//                        .append(";\n")
//                        .toString();
                resultList.add(result);
            }
        });

        return Joiner.on("\n").join(resultList);
    }

    private static String convertColumnToMybatisResultMap(String columnDefine) {
        if (StringUtils.isBlank(columnDefine)) {
            return "";
        }
        List<String> resultList = Lists.newArrayList();
        List<String> list = Splitter.on(",\n").splitToList(columnDefine);
        Pattern compile = Pattern.compile("^\\s*`(.*)` ([a-zA-Z]*)(\\(.*\\))? .*'(.*)'$");
        list.forEach(ele -> {
            Matcher matcher = compile.matcher(ele);
            if (matcher.find()) {
                String result = String.format("<result column=\"%s\" property=\"%s\"/>", matcher.group(1), convertUnderlineToCamel(matcher.group(1)));
                resultList.add(result);
            }
        });

        return Joiner.on("\n").join(resultList);
    }

    private static String obtainPropertyType(String type) {
        String result = "";

        switch (type.toLowerCase()) {
            case "timestamp":
            case "datetime":
                result = "Date";
                break;
            case "int":
                result = "Integer";
                break;
            case "decimal":
                result = "BigDecimal";
                break;
            default:
                result = "String";
                break;
        }

        return result;
    }
}
