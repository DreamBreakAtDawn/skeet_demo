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

    private static final Pattern COLUMN_DEFINE_PATTERN = Pattern.compile("^\\s*`(.*)` ([a-zA-Z]*)(\\(.*\\))? .*'(.*)'$");

    public static void main(String[] args) {
//        String s = buildQueryIn(Lists.newArrayList("123", "456"));
//        System.out.println(s);

        String columnDefine = " `Flimit_multiple` varchar(50) DEFAULT NULL COMMENT '边际倍数',\n" +
                "  `Fall_multiple` varchar(50) DEFAULT NULL COMMENT '全场倍数',\n" +
                "  `Flimit_rate` varchar(64) DEFAULT NULL COMMENT '边际利率（%/年）',\n" +
                "  `Fweighting_rate` varchar(50) DEFAULT NULL COMMENT '加权利率（%/年）',\n" +
                "  `Flimit_price` varchar(50) DEFAULT NULL COMMENT '边际价格（元）',\n" +
                "  `Fweighting_price` varchar(50) DEFAULT NULL COMMENT '加权价格（元）',\n" +
                "  `Fsmall_range` varchar(50) DEFAULT NULL COMMENT '小区间范围（每一轮最新指导区间）',\n" +
                "  `Fsmall_range_lower_Side` varchar(50) DEFAULT NULL COMMENT '指导区间下限',\n" +
                "  `Fsmall_range_upper_Side` varchar(50) DEFAULT NULL COMMENT '指导区间上限',\n" +
                "  `Foperator` varchar(50) DEFAULT NULL COMMENT '操作人'";
//        System.out.println(convertColumnToEntity(columnDefine, "F"));
        System.out.println(getInsertSqlForBondHeaderDictionary(columnDefine, "F"));
    }

    private static String getInsertSqlForBondHeaderDictionary(String columnDefine, String removePrefix) {
        if (StringUtils.isBlank(columnDefine)) {
            return "";
        }

//        String sqlTemplate = "INSERT INTO `db_appletree`.`t_org_ad_bond_header_data_dictionary` (
//        `Fid`, `Fbond_type`, `Fheader_name`, `Fchinese_header_name`, `Fcolumn_name`, `Fdata_status`)
//        VALUES ('1', '1', 'orgRole', '我司角色', 'Forg_role', '1');";
        String sqlTemplate = "INSERT INTO `db_primary_bond_sale`.`t_org_ad_bond_header_data_dictionary` (" +
                "`Fheader_name`, `Fchinese_header_name`, `Fcolumn_name`, `Fcolumn_source`, `column_limit`, `Fdata_status`) " +
                "VALUES ('%s', '%s', '%s', '%s', %s, %s);";

        List<String> resultList = Lists.newArrayList();
        List<String> list = Splitter.on(",\n").splitToList(columnDefine);
        list.forEach(ele -> {
            Matcher matcher = COLUMN_DEFINE_PATTERN.matcher(ele);
            if (matcher.find()) {
                String columnName = matcher.group(1);
                String fieldName = getFieldName(columnName, removePrefix);
                String propertyType = obtainPropertyType(matcher.group(2));
                String length = propertyType.equals("String") ? matcher.group(3) : "-1";
                String comment = matcher.group(4);
                String result = String.format(sqlTemplate, fieldName, comment, columnName, "", length, 1);
                resultList.add(result);
            }
        });

        return Joiner.on("\n").join(resultList);
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
    public static String convertColumnToEntity(String columnDefine, String removePrefix) {
        if (StringUtils.isBlank(columnDefine)) {
            return "";
        }
        List<String> resultList = Lists.newArrayList();
        List<String> list = Splitter.on(",\n").splitToList(columnDefine);
        list.forEach(ele -> {
            Matcher matcher = COLUMN_DEFINE_PATTERN.matcher(ele);
            if (matcher.find()) {
                String fieldName = getFieldName(matcher.group(1), removePrefix);
                String result = String.format("    /**\n     * %s\n     */\n    private %s %s;\n",
                        matcher.group(4), obtainPropertyType(matcher.group(2)), fieldName);
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

    public static String getFieldName(String columnName, String removePrefix) {
        String fieldName = convertUnderlineToCamel(columnName);
        if (fieldName.substring(0, removePrefix.length()).equals(removePrefix)) {
            fieldName = fieldName.substring(removePrefix.length());
        }

        return fieldName;
    }
}
