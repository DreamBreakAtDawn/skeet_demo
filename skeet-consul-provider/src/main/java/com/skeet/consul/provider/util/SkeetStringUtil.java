package com.skeet.consul.provider.util;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Description
 * @Author chengsj
 * @Date 2021/2/2 20:03
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SkeetStringUtil {

    public static void main(String[] args) {
        genGetMethodForPrimaryBond();
        genSetMethodForPrimaryBond();
    }

    private static void genSetMethodForPrimaryBond() {
        String template = "\tpublic void set%s(BigDecimal %s) {\n" +
                "\t\tgenGuideInfo();\n" +
                "\t\tthis.guideInfo.set%s(%s);\n" +
                "\t}\n";
        Map<String , String> contentMap = Maps.newHashMap();
        contentMap.put("limitRate", "limitRate.toPlainString()");
        contentMap.put("limitPrice", "limitPrice.toPlainString()");
        contentMap.put("limitRateMargin", "limitRateMargin.toPlainString()");
        contentMap.put("weightingRate", "weightingRate.toPlainString()");
        contentMap.put("weightingPrice", "weightingPrice.toPlainString()");
        contentMap.put("limitMultiple", "limitMultiple.toPlainString()");
        contentMap.put("allMultiple", "allMultiple.toPlainString()");
        contentMap.put("isIntercept", "");
        contentMap.put("realInterceptTime", "");
        contentMap.put("benchmarkRate", "benchmarkRate.toPlainString()");
        for (Map.Entry<String, String> entry : contentMap.entrySet()) {
            String contentField = entry.getKey();
            String contentConvert = entry.getValue();
            String upperCase = SkeetStringUtil.firstToUpperCase(contentField);
            System.out.println(genByStringTemplate(template, upperCase, contentField, upperCase, contentConvert));
        }
    }

    private static void genGetMethodForPrimaryBond() {
        String template = "\tpublic BigDecimal get%s() {\n" +
                "\t\treturn Objects.nonNull(guideInfo) ? %s : null;\n" +
                "\t}\n";
        Map<String , String> contentMap = Maps.newHashMap();
        contentMap.put("limitRate", "Convert.toBigDecimal(guideInfo.getLimitRate())");
        contentMap.put("limitPrice", "Convert.toBigDecimal(guideInfo.getLimitPrice())");
        contentMap.put("limitRateMargin", "Convert.toBigDecimal(guideInfo.getLimitRateMargin())");
        contentMap.put("weightingRate", "Convert.toBigDecimal(guideInfo.getWeightingRate())");
        contentMap.put("weightingPrice", "Convert.toBigDecimal(guideInfo.getWeightingPrice())");
        contentMap.put("limitMultiple", "Convert.toBigDecimal(guideInfo.getLimitMultiple())");
        contentMap.put("allMultiple", "Convert.toBigDecimal(guideInfo.getAllMultiple())");
        contentMap.put("isIntercept", "guideInfo.getIsIntercept()");
        contentMap.put("realInterceptTime", "DateUtil.convert2Date(guideInfo.getRealInterceptTime(), DateUtil.DATE_FORMAT_INTERCEPT)");
        contentMap.put("benchmarkRate", "Convert.toBigDecimal(guideInfo.getBenchmarkRate())");
        for (Map.Entry<String, String> entry : contentMap.entrySet()) {
            String contentField = entry.getKey();
            String contentConvert = entry.getValue();
            String upperCase = SkeetStringUtil.firstToUpperCase(contentField);
            System.out.println(genByStringTemplate(template, upperCase, contentConvert));
        }
    }


    private static String genByStringTemplate(String template, String... content) {
        return String.format(template, content);
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
