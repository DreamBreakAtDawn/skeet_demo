package com.skeet.consul.provider.util;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2019/6/25 9:48
 */
public class MySqlStringUtil {

    /** 1-字段名称，2-字段类型，3-字段长度，4-字段描述 */
    private static final Pattern COLUMN_DEFINE_PATTERN = Pattern.compile("^\\s*(`(.*)` ([a-zA-Z]*)(\\(.*\\))? .*'(.*)')$");

    public static void main(String[] args) {
//        String s = buildQueryIn(Lists.newArrayList("123", "456"));
//        System.out.println(s);

        String columnDefine = "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',\n" +
                "  `open_id` char(63) DEFAULT NULL COMMENT '债销人 - 企点openid',\n" +
                "  `real_name` varchar(127) DEFAULT NULL COMMENT '债销人 - 企点号姓名',\n" +
                "  `org_id` char(63) DEFAULT NULL COMMENT '债销人 - 机构id',\n" +
                "  `org_name` varchar(127) NOT NULL COMMENT '债销人 - 机构名称',\n" +
                "  `bond_class` tinyint(4) DEFAULT NULL COMMENT '债券大类型：1：利率债   2：信用债 ',\n" +
                "  `opp_open_id` char(63) DEFAULT NULL COMMENT '交易对手 - 企点openid',\n" +
                "  `opp_org_id` char(63) DEFAULT NULL COMMENT '交易对手 - 机构id',\n" +
                "  `opp_org_name` varchar(127) DEFAULT NULL COMMENT '交易对手 - 机构名称',\n" +
                "  `opp_display_name` varchar(127) DEFAULT NULL COMMENT '交易对手 - 显示名称',\n" +
                "  `opp_remark` varchar(255) DEFAULT NULL COMMENT '交易对手 - 备注',\n" +
                "  `bond_id` bigint(20) DEFAULT NULL COMMENT '债券ID',\n" +
                "  `rate` decimal(10,4) DEFAULT NULL COMMENT '标位（%）',\n" +
                "  `rate_margin` decimal(10,4) DEFAULT NULL COMMENT '利差（%）',\n" +
                "  `price` decimal(16,4) DEFAULT NULL COMMENT '价格',\n" +
                "  `vol` decimal(10,2) DEFAULT NULL COMMENT '面值（亿）',\n" +
                "  `actual_rate` decimal(10,4) DEFAULT NULL COMMENT '实际中标利率（%）',\n" +
                "  `actual_price` decimal(16,4) DEFAULT NULL COMMENT '实际中标价格',\n" +
                "  `actual_vol` decimal(10,2) DEFAULT NULL COMMENT '实际分配面值（亿）',\n" +
                "  `not_over_vol` decimal(10,2) DEFAULT NULL COMMENT '不超过百分比（%），预留字段',\n" +
                "  `delivery_method` tinyint(4) DEFAULT NULL COMMENT '交割方式'";
//        System.out.println(convertColumnToEntity(columnDefine, "F"));
//        System.out.println(convertColumnToEntityWithColumnAnnotation(columnDefine, "F"));
        System.out.println(getInsertSqlForBondHeaderDictionary(columnDefine, "F"));
//        System.out.println(getAllColumn(columnDefine));
//        System.out.println(genResultMap(columnDefine));
//        System.out.println(genApiModelProperty(columnDefine, "F"));
//        System.out.println(genInsertContentList(columnDefine));
//        test(columnDefine);
    }

    private static String test(String columnDefine) {
        return doLogic(columnDefine, "\n", matcher -> {
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(3));
            System.out.println(matcher.group(4));
            System.out.println(matcher.group(5));
            return "";
        });
    }

    private static String genApiModelProperty(String columnDefine, String removePrefix) {
        String template = "    @ApiModelProperty(value = \"%s\")\n    private %s %s;\n";
        return doLogic(columnDefine, "\n", matcher -> {
            String column = matcher.group(2);
            String type = matcher.group(3);
            String desc = matcher.group(5);
            return String.format(template,
                    desc,
                    obtainPropertyType(type),
                    getFieldName(column, removePrefix));
        });
    }

    private static String genResultMap(String columnDefine) {
        String template = "<%s column=\"%s\" jdbcType=\"%s\" property=\"%s\" />";
        return doLogic(columnDefine, "\n", matcher -> {
            String column = matcher.group(2);
            String type = matcher.group(3);
            return String.format(template,
                    Arrays.asList("id", "Fid").contains(column) ? "id" : "result",
                    column,
                    type.toUpperCase(),
                    SkeetStringUtil.convertUnderlineToCamel(column));
        });
    }

    private static String getAllColumn(String columnDefine) {
        return doLogic(columnDefine, ",", matcher -> matcher.group(2));
    }

    private static String convertColumnToEntityWithColumnAnnotation(String columnDefine, String removePrefix) {
        String template = "    /**\n     * %s\n     */\n    @Column(name = \"%s\")\n    private %s %s;\n";
        return doLogic(columnDefine, "\n", matcher -> {
            String fieldName = getFieldName(matcher.group(2), removePrefix);
            return String.format(template,
                    matcher.group(5), matcher.group(2), obtainPropertyType(matcher.group(3)), fieldName);
        });
    }

    /**
     * 将表定义语句的字段转化成javabean的属性
     *
     * @param columnDefine
     * @return
     */
    public static String convertColumnToEntity(String columnDefine, String removePrefix) {
        String template = "    /**\n     * %s\n     */\n    private %s %s;\n";
        return doLogic(columnDefine, "\n", matcher -> {
            String fieldName = getFieldName(matcher.group(2), removePrefix);
            return String.format(template,
                    matcher.group(5), obtainPropertyType(matcher.group(3)), fieldName);
        });
    }

    private static String genInsertContentList(String columnDefine) {
        String template = "#{item.%s,jdbcType=%s}";
        return doLogic(columnDefine, ",", matcher -> {
            String column = matcher.group(2);
            String type = matcher.group(3);
            return String.format(template,
                    SkeetStringUtil.convertUnderlineToCamel(column),
                    type.toUpperCase());
        });
    }

    private static String getInsertSqlForBondHeaderDictionary(String columnDefine, String removePrefix) {

        String template = "INSERT INTO `db_primary_bond_sale`.`t_org_ad_bond_header_data_dictionary` (" +
                "`Fheader_name`, `Fchinese_header_name`, `Fcolumn_name`, `Fcolumn_source`, `column_limit`, `Fdata_status`) " +
                "VALUES ('%s', '%s', '%s', '%s', %s, %s);";
        return doLogic(columnDefine, "\n", matcher -> {
            String columnName = matcher.group(2);
            String fieldName = getFieldName(columnName, removePrefix);
            String length = getLength(matcher);
            String comment = matcher.group(5);
            return String.format(template, fieldName, comment, columnName, "", length, 1);
        });
    }

    private static String getLength(Matcher matcher) {
        String propertyType = obtainPropertyType(matcher.group(3));
        String lengthWrap = matcher.group(4);
        return propertyType.equals("String") ? lengthWrap.substring(1, lengthWrap.length() - 1) : "-1";
    }

    private static String convertColumnToMybatisResultMap(String columnDefine) {
        String template = "<result column=\"%s\" property=\"%s\"/>";
        return doLogic(columnDefine, "\n", matcher -> {
            String columnName = matcher.group(2);
            String propertyName = SkeetStringUtil.convertUnderlineToCamel(matcher.group(2));
            return String.format(template, columnName, propertyName);
        });
    }

    private static String doLogic(String columnDefine, String joinSymbol, Function<Matcher, String> function) {
        if (StringUtils.isBlank(columnDefine)) {
            return "";
        }
        List<String> resultList = Lists.newArrayList();
        List<String> list = Splitter.on(",\n").splitToList(columnDefine);
        list.forEach(ele -> {
            Matcher matcher = COLUMN_DEFINE_PATTERN.matcher(ele);
            if (matcher.find()) {
                String result = function.apply(matcher);
                resultList.add(result);
            }
        });

        return Joiner.on(joinSymbol).join(resultList);
    }

    public static String buildQueryIn(List<String> lst) {
        StringBuilder builder = new StringBuilder();
        lst.forEach(i -> builder.append(String.format("'%s', ", i)));
        builder.replace(builder.length() - 2, builder.length(), "");
        return builder.toString();
    }

    private static String obtainPropertyType(String type) {
        String result = "";

        switch (type.toLowerCase()) {
            case "timestamp":
            case "datetime":
                result = "Date";
                break;
            case "tinyint":
            case "smallint":
            case "mediumint":
            case "int":
                result = "Integer";
                break;
            case "bigint":
                result = "Long";
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
        String fieldName = SkeetStringUtil.convertUnderlineToCamel(columnName);
        if (fieldName.substring(0, removePrefix.length()).equals(removePrefix)) {
            fieldName = fieldName.substring(removePrefix.length());
        }

        return fieldName;
    }
}
