package com.skeet.consul.provider.project.qtrade.ai.domain;

import com.google.common.collect.Lists;
import com.qtrade.basecode.BaseResponseResult;
import com.skeet.consul.provider.util.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/10/20 11:31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkProjectDataDTO {

    /**
     * 主键
     */
    private Integer tid;

    /**
     * 项目ID
     */
    private String projectId;

    /**
     * 文本序号
     */
    private Integer textSequence;

    /**
     * 原始文本
     */
    private String originalText;

    /**
     * 实体
     */
    private String markEntity;

    /**
     * 意图
     */
    private String markIntention;

    /**
     * 文本类别
     */
    private String markType;

    /**
     * 结构化数据
     */
    private String structuredData;

    /**
     * 标准化数据
     */
    private String standardisedData;

    /**
     * 标注状态
     */
    private String status;

    /**
     * 标注人
     */
    private String markUsername;

    /**
     * 耗时，单位毫秒
     */
    private Integer timeConsuming;

    /**
     * 备注
     */
    private String remark;

    /**
     * 标签配置
     */
    private String labelConfig;

    private String projectName;

    public static void main(String[] args) {
        MarkProjectDataDTO markProjectDataDTO = new MarkProjectDataDTO();
        markProjectDataDTO.setProjectId("MP202010210001");
        markProjectDataDTO.setTextSequence(1);
        markProjectDataDTO.setProjectName("标位");
        markProjectDataDTO.setOriginalText("你好，2.78%投 2000w");

        MarkLabelConfigDTO config_1 = new MarkLabelConfigDTO();
        config_1.setColor("#FFFFFF");
        config_1.setElement("test");
        config_1.setShortcutKey("a");
        config_1.setType("投标");

        MarkLabelConfigDTO config_2 = new MarkLabelConfigDTO();
        config_2.setColor("#000000");
        config_2.setElement("test");
        config_2.setShortcutKey("b");
        config_2.setType("转债");
        markProjectDataDTO.setLabelConfig(JsonUtils.beanToJson(Lists.newArrayList(config_1, config_2)));
        System.out.println(JsonUtils.beanToJson(BaseResponseResult.success(markProjectDataDTO)));

        MarkProjectDataDTO markProjectDataDTO_2 = new MarkProjectDataDTO();
        markProjectDataDTO_2.setProjectId("MP202010210001");
        markProjectDataDTO_2.setTextSequence(1);
        markProjectDataDTO_2.setProjectName("标位");
        markProjectDataDTO_2.setOriginalText("你好，2.78%投 2000w");
        markProjectDataDTO_2.setMarkIntention("投标");
        markProjectDataDTO_2.setMarkType("成交");
        markProjectDataDTO_2.setMarkEntity(
                JsonUtils.beanToJson(Lists.newArrayList(
                        MarkProjectDataEntityDTO
                                .builder()
                                .startIndex(3)
                                .endIndex(6)
                                .color("#000000")
                                .element("test")
                                .shortcutKey("b")
                                .type("转债")
                                .build(),
                        MarkProjectDataEntityDTO
                                .builder()
                                .startIndex(9)
                                .endIndex(14)
                                .color("#FFFFFF")
                                .element("test")
                                .shortcutKey("c")
                                .type("基金")
                                .build()

                )));
        System.out.println(JsonUtils.beanToJson(markProjectDataDTO_2));

        MarkProjectDataDTO markProjectDataDTO_3 = new MarkProjectDataDTO();
        markProjectDataDTO_3.setProjectId("MP202010210001");
        markProjectDataDTO_3.setTextSequence(2);
        markProjectDataDTO_3.setProjectName("标位");
        markProjectDataDTO_3.setOriginalText("你好，5.55%再投 8000w");
        markProjectDataDTO_3.setLabelConfig(JsonUtils.beanToJson(Lists.newArrayList(config_1, config_2)));
        System.out.println(JsonUtils.beanToJson(BaseResponseResult.success(markProjectDataDTO_3)));
    }
}
