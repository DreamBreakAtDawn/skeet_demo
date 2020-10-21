package com.skeet.consul.provider.project.qtrade.ai.domain;

import com.google.common.collect.Lists;
import com.qtrade.basecode.BaseResponseResult;
import com.skeet.consul.provider.util.JsonUtils;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/10/20 11:31
 */
@Data
public class MarkProjectDTO {

    /**
     * 主键
     */
    private Integer tid;

    /**
     * 项目ID
     */
    private String projectId;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目描述
     */
    private String description;

    /**
     * 项目所有者
     */
    private String createUsername;

    /**
     * 导入时间
     */
    private Date importTime;

    /**
     * 项目类型
     */
    private String projectType;

    /**
     * 数据类型，如单轮/多轮
     */
    private String dataType;

    /**
     * 标注数据完成数
     */
    private Integer dataFinishCount;

    /**
     * 标注数据总数
     */
    private Integer dataTotalCount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 标签配置
     */
    private String labelConfig;

    /**
     * 完成度
     */
    private String finishRate;

    private List<MarkProjectDataDTO> projectDataDTOs;


    public static void main(String[] args) {
        MarkProjectDTO markProject = new MarkProjectDTO();
        markProject.setName("标位");
        markProject.setProjectId("MP202010210001");
        markProject.setProjectType("询价");
        markProject.setCreateUsername("张三");
        markProject.setImportTime(new Date());
        markProject.setDataFinishCount(225);
        markProject.setDataTotalCount(1000);
        markProject.setDescription("具体描述");
        MarkProjectDTO markProject_2 = new MarkProjectDTO();
        markProject_2.setName("标位2");
        markProject_2.setProjectId("MP202010210002");
        markProject_2.setProjectType("询价");
        markProject_2.setCreateUsername("张三");
        markProject_2.setImportTime(new Date());
        markProject_2.setDataFinishCount(225);
        markProject_2.setDataTotalCount(1000);
        markProject_2.setDescription("具体描述");
        System.out.println(JsonUtils.beanToJson(markProject_2));
        System.out.println(JsonUtils.beanToJson(BaseResponseResult.success(Lists.newArrayList(markProject, markProject_2))));

        MarkProjectDTO dto = new MarkProjectDTO();
        dto.setProjectId("MP202010210001");
        System.out.println(JsonUtils.beanToJson(dto));


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

        MarkProjectDTO dto_2 = new MarkProjectDTO();
        dto_2.setName("标位");
        dto_2.setProjectType("询价");
        dto_2.setDescription("具体描述");
        dto_2.setDataType("0");
        dto_2.setLabelConfig(JsonUtils.beanToJson(Lists.newArrayList(config_1, config_2)));
        dto_2.setProjectDataDTOs(Lists.newArrayList(
                MarkProjectDataDTO.builder().originalText("原始文本1").build(),
                MarkProjectDataDTO.builder().originalText("原始文本2").build()
        ));
        System.out.println(JsonUtils.beanToJson(dto_2));

    }
}
