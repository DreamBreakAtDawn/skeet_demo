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
//        genGetMethodForPrimaryBond();
//        genSetMethodForPrimaryBond();
//        genUpdateSqlForDictionaryLimit();
        genAlterTableSqlForBond();
    }

    private static void genAlterTableSqlForBond() {
        String sqlTemplate = "ALTER TABLE `db_primary_bond_sale`.`%s` MODIFY %s;";
        Map<String, String> map = buiAlterTableMapForBond();
        map.forEach((k, v) -> System.out.println(String.format(sqlTemplate, v, k)));
    }

    private static void genUpdateSqlForDictionaryLimit() {
        String sqlTemplate = "UPDATE `db_primary_bond_sale`.`t_org_ad_bond_header_data_dictionary` SET Fcolumn_limit = %s WHERE Fheader_name = '%s';";
        Map<String, Integer> map = buiDictionaryMap();
        map.forEach((k, v) -> System.out.println(String.format(sqlTemplate, v, k)));
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

    private static Map<String, Integer> buiDictionaryMap() {
        Map<String, Integer> map = Maps.newHashMap();
        map.put("bondCode", 20);
        map.put("bondSimpleName", 50);
        map.put("bondFullName", 100);
        map.put("issueAmount", 50);
        map.put("deadline", 50);
        map.put("bondType", 50);
        map.put("prdctnRange", 50);
        map.put("tenderStartDate", 50);
        map.put("realInterceptTime", 50);
        map.put("firstPubDataDate", 50);
        map.put("issueDate", 50);
        map.put("issueStartDate", 20);
        map.put("issueEndDate", 20);
        map.put("riseDate", 50);
        map.put("payDate", 50);
        map.put("payStartDate", 20);
        map.put("payEndDate", 20);
        map.put("ipoDate", 50);
        map.put("expireDate", 50);
        map.put("leadUnderwriter", 200);
        map.put("jointLeadUnderwriter", 200);
        map.put("underwritingSyndicate", 200);
        map.put("bookkeeper", 200);
        map.put("underwriteWay", 20);
        map.put("leadUnderwriterContact", 50);
        map.put("bookkeeperContact", 50);
        map.put("issuers", 200);
        map.put("compProperty", 50);
        map.put("area", 50);
        map.put("isListed", 50);
        map.put("regiterAmount", 50);
        map.put("assetSize", 50);
        map.put("debtRatio", 50);
        map.put("basicSituation", 500);
        map.put("rating", 64);
        map.put("bondRating", 50);
        map.put("issuerRating", 50);
        map.put("ratingAgency", 100);
        map.put("pubWay", 50);
        map.put("exchMarket", 100);
        map.put("isAddPub", 50);
        map.put("isSpanMarket", 20);
        map.put("isAdditional", 50);
        map.put("isCityInvestment", 50);
        map.put("isRight", 50);
        map.put("useOfFunds", 500);
        map.put("specialItem", 5000);
        map.put("pubHistory", 5000);
        map.put("rateType", 50);
        map.put("benchmarkRate", 20);
        map.put("intPayment", 50);
        map.put("interestFrequency", 20);
        map.put("repaymentWay", 20);
        map.put("tenderType", 30);
        map.put("bidTargetType", 50);
        map.put("isCompetitiveSale", 50);
        map.put("enhcMethod", 50);
        map.put("guarantor", 50);
        map.put("guaranteeSituation", 500);
        map.put("enhc", 500);
        map.put("pubRate", 64);
        map.put("issueCommissionRate", 50);
        map.put("rebate", 50);
        map.put("brokerage", 50);
        map.put("smallRange", 50);
        map.put("allMultiple", 10);
        map.put("limitMultiple", 10);
        map.put("limitRate", 10);
        map.put("weightingRate", 10);
        map.put("orgBranch", 50);
        map.put("orgRole", 50);
        map.put("selfBondSummaryContact", 50);
        map.put("selfBondSaleContact", 50);
        map.put("remark", 500);
        return map;
    }

    private static Map<String, String> buiAlterTableMapForBond() {
        Map<String, String> map = Maps.newHashMap();
        map.put("`Fissue_amount_low_size` varchar(20) DEFAULT NULL COMMENT '发行额下限(亿)'", "t_primary_bond_info");//
        map.put("`Fissue_amount_large_size` varchar(20) DEFAULT NULL COMMENT '发行额上限(亿)'", "t_primary_bond_info");//
        map.put("`Fexch_market` varchar(100) DEFAULT NULL COMMENT '发行场所'", "t_primary_bond_info");
        map.put("`Fis_city_investment` varchar(50) DEFAULT NULL COMMENT '是否城投'", "t_primary_bond_info");
        map.put("`Fis_right` varchar(50) DEFAULT NULL COMMENT '是否含权'", "t_primary_bond_info");
        map.put("`Fpub_history` varchar(5000) DEFAULT NULL COMMENT '历史发债情况'", "t_primary_bond_info");
        map.put("`Fint_payment` varchar(50) DEFAULT NULL COMMENT '付息方式'", "t_primary_bond_info");
        map.put("`Ffirst_pub_data_date` varchar(50) DEFAULT NULL COMMENT '公告日'", "t_primary_bond_info");
        map.put("`Fissue_date` varchar(50) DEFAULT NULL COMMENT '发行日'", "t_primary_bond_info");
        map.put("`Fissue_start_date` varchar(20) DEFAULT NULL COMMENT '发行起始日'", "t_primary_bond_info");//
        map.put("`Fissue_end_date` varchar(20) DEFAULT NULL COMMENT '发行截止日'", "t_primary_bond_info");//
        map.put("`Ftender_start_time` varchar(20) DEFAULT NULL COMMENT '簿记时间起始日'", "t_primary_bond_info");//
        map.put("`Ftender_end_time` varchar(20) DEFAULT NULL COMMENT '簿记时间截至日'", "t_primary_bond_info");//
        map.put("`Fbid_start_time` varchar(20) DEFAULT NULL COMMENT '招标开始时间'", "t_primary_bond_info");//
        map.put("`Fbid_end_time` varchar(20) DEFAULT NULL COMMENT '招标结束时间'", "t_primary_bond_info");//
        map.put("`Fprdctn_range_lower_side` varchar(20) DEFAULT NULL COMMENT '预测区间下限'", "t_primary_bond_info");//
        map.put("`Fprdctn_range_upper_side` varchar(20) DEFAULT NULL COMMENT '预测区间上限'", "t_primary_bond_info");//
        map.put("`Fguarantee_situation` varchar(500) DEFAULT NULL COMMENT '担保情况'", "t_primary_bond_info");
        map.put("`Fenhc` varchar(500) DEFAULT NULL COMMENT '增信'", "t_primary_bond_info");
        map.put("`Fpay_date` varchar(50) DEFAULT NULL COMMENT '缴款日'", "t_primary_bond_info");
        map.put("`Fpay_start_date` varchar(20) DEFAULT NULL COMMENT '缴款起始日'", "t_primary_bond_info");//
        map.put("`Fpay_end_date` varchar(20) DEFAULT NULL COMMENT '缴款到期日'", "t_primary_bond_info");//

        map.put("`Fbasic_situation` varchar(500) DEFAULT NULL COMMENT '基本情况'", "t_primary_issuer_info");

        map.put("`Frating_agency` varchar(100) DEFAULT NULL COMMENT '评级机构'", "t_primary_rating_info");

        map.put("`Fsmall_range_lower_Side` varchar(20) DEFAULT NULL COMMENT '指导区间下限'", "t_primary_region_guide_info");//
        map.put("`Fsmall_range_upper_Side` varchar(20) DEFAULT NULL COMMENT '指导区间上限'", "t_primary_region_guide_info");//
        return map;
    }

    private static void genGetMethodForPrimaryBond() {
        String template = "\tpublic BigDecimal get%s() {\n" +
                "\t\treturn Objects.nonNull(guideInfo) ? %s : null;\n" +
                "\t}\n";
        Map<String, String> contentMap = Maps.newHashMap();
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

    private static void genSetMethodForPrimaryBond() {
        String template = "\tpublic void set%s(BigDecimal %s) {\n" +
                "\t\tgenGuideInfo();\n" +
                "\t\tthis.guideInfo.set%s(%s);\n" +
                "\t}\n";
        Map<String, String> contentMap = Maps.newHashMap();
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
}
